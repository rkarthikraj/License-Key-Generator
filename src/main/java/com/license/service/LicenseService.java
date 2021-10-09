package com.license.service;

import com.license.request.LicenseKeyRequest;
import com.license.request.LicenseRequest;
import com.license.response.LicenseKeyResponse;
import com.license.response.LicenseResponse;
import com.license.utils.DateTimeUtility;
import com.license.utils.LicenseConstants;
import com.license.utils.LicenseKeyGenerator;
import com.license.utils.PasswordUtility;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class LicenseService {
    public LicenseKeyResponse generateLicense(LicenseRequest licenseInfoRequest) {
        LicenseKeyResponse licenseKeyResponse = new LicenseKeyResponse();
        LicenseConstants licenseConstants = new LicenseConstants();
        DateTimeUtility dateTimeUtility = new DateTimeUtility();
        PasswordUtility passwordUtility = new PasswordUtility();
        LicenseKeyGenerator licenseKeyGenerator = new LicenseKeyGenerator();

        String productKey = "";
        String OUTPUT_FORMAT = "%-30s:%s";
        String organizationName = licenseInfoRequest.getOrgName();
        int numberOfLicense = licenseInfoRequest.getNumberOfLicense();
        String licenseEndDate = licenseInfoRequest.getLicenseEndDate();

        if (organizationName == null || organizationName.trim().isEmpty()) {
            licenseKeyResponse.setMessage("Organization name should not be empty");
            return licenseKeyResponse;
        } else if (numberOfLicense == 0) {
            licenseKeyResponse.setMessage("License number should not be zero or empty");
        } else if (licenseEndDate == null || licenseEndDate.trim().isEmpty()) {
            licenseKeyResponse.setMessage("License end date should not be empty");
        } else {
            String timestamp = null;
            try {
                timestamp = dateTimeUtility.generateTimestamp(licenseEndDate);
            } catch (ParseException e) {
                licenseKeyResponse.setMessage("Invalid date format. Date format should be in yyyy-MM-dd");
                return licenseKeyResponse;
            }

            String plainText = organizationName + "|" + numberOfLicense + "|" + timestamp;
            String password = passwordUtility.generatePassword();
            String encryptedTextBase64 = null;

            try {
                encryptedTextBase64 = licenseKeyGenerator.encrypt(plainText.getBytes(licenseConstants.UTF_8), password);
            } catch (Exception e) {
                licenseKeyResponse.setMessage("Something went wrong");
                return licenseKeyResponse;
            }

            String splitPassword = passwordUtility.splitter(password);
            String encryptedText = passwordUtility.splitter(encryptedTextBase64);
            productKey = splitPassword + "-" + encryptedText;

            licenseKeyResponse.setLicenseKey(productKey);
            licenseKeyResponse.setMessage("License key generated successfully");

            return licenseKeyResponse;
        }
        return licenseKeyResponse;
    }

    public LicenseResponse validateLicense(LicenseKeyRequest licenseKeyRequest) {
        LicenseResponse licenseResponse = new LicenseResponse();
        try {
            DateTimeUtility dateTimeUtility = new DateTimeUtility();
            PasswordUtility passwordUtility = new PasswordUtility();
            LicenseKeyGenerator licenseKeyGenerator = new LicenseKeyGenerator();

            String licenseKey = licenseKeyRequest.getLicenseKey();
            String[] licenseKeyArray = licenseKey.split("-");
            StringBuilder passwordSB = new StringBuilder();
            StringBuilder encryptedText = new StringBuilder();

            for (int i = 0; i < licenseKeyArray.length; ++i) {
                if (i < 8) {
                    passwordSB.append(licenseKeyArray[i]);
                } else if (i >= 8) {
                    encryptedText.append(licenseKeyArray[i]);
                }
            }

            String decryptedText = licenseKeyGenerator.decrypt(encryptedText.toString(), passwordSB.toString());
            String[] decryptedArr = decryptedText.split("\\|");

            String date = dateTimeUtility.generateDate(decryptedArr[2]);

            licenseResponse.setOrgName(decryptedArr[0]);
            licenseResponse.setNumberOfLicense(Integer.parseInt(decryptedArr[1]));
            licenseResponse.setLicenseEndDate(date);

            boolean isExpired = dateTimeUtility.checkExpiry(date);
            if (!isExpired) {
                licenseResponse.setMessage("Valid license");
            } else {
                licenseResponse.setMessage("License expired");
            }

            return licenseResponse;
        } catch (Exception e) {
            licenseResponse.setMessage("Invalid license");
            return licenseResponse;
        }
    }
}
