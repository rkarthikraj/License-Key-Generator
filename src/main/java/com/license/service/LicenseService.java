package com.license.service;

import com.license.request.GenerateLicenseKeyRequest;
import com.license.request.ValidateLicenseKeyRequest;
import com.license.response.GenerateLicenseKeyResponse;
import com.license.response.ValidateLicenseKeyResponse;
import com.license.utils.*;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    public GenerateLicenseKeyResponse generateLicense(GenerateLicenseKeyRequest generateLicenseKeyRequest) throws LicenseException {
        GenerateLicenseKeyResponse generateLicenseKeyResponse = new GenerateLicenseKeyResponse();
        LicenseConstants licenseConstants = new LicenseConstants();
        DateTimeUtility dateTimeUtility = new DateTimeUtility();
        PasswordUtility passwordUtility = new PasswordUtility();
        LicenseKeyGenerator licenseKeyGenerator = new LicenseKeyGenerator();

        String organizationName = generateLicenseKeyRequest.getOrgName();
        String licenseEndDate = generateLicenseKeyRequest.getLicenseEndDate();
        int numberOfLicense = generateLicenseKeyRequest.getNumberOfLicense();
        String timestamp = dateTimeUtility.generateTimestamp(licenseEndDate);
        String secretKey = generateLicenseKeyRequest.getSecretKey();

        checkValidLicenseKeyGenerateRequestParams(organizationName, licenseEndDate, numberOfLicense, timestamp, secretKey);

        String plainText = organizationName + "|" + numberOfLicense + "|" + timestamp;
        String encryptedTextBase64 = licenseKeyGenerator.encrypt(plainText.getBytes(licenseConstants.UTF_8), secretKey.trim());
        if (encryptedTextBase64 == null) {
            throw new LicenseException("Something went wrong");
        }

        generateLicenseKeyResponse.setLicenseKey(encryptedTextBase64);
        generateLicenseKeyResponse.setSecretKey(secretKey.trim());
        generateLicenseKeyResponse.setSucessMessage("License key generated successfully");

        return generateLicenseKeyResponse;
    }

    public ValidateLicenseKeyResponse validateLicense(ValidateLicenseKeyRequest validateLicenseKeyRequest) throws LicenseException {
        ValidateLicenseKeyResponse validateLicenseKeyResponse = new ValidateLicenseKeyResponse();
        DateTimeUtility dateTimeUtility = new DateTimeUtility();
        LicenseKeyGenerator licenseKeyGenerator = new LicenseKeyGenerator();

        String licenseKey = validateLicenseKeyRequest.getLicenseKey();
        String secretKey = validateLicenseKeyRequest.getSecretKey();

        checkValidLicenseKeyValidateRequestPrams(licenseKey, secretKey);

        String decryptedText = licenseKeyGenerator.decrypt(licenseKey, secretKey.trim());
        if (decryptedText == null) {
            throw new LicenseException("Invalid license key or secret key");
        }

        String[] decryptedArr = decryptedText.split("\\|");
        String date = dateTimeUtility.generateDate(decryptedArr[2]);

        validateLicenseKeyResponse.setOrgName(decryptedArr[0]);
        validateLicenseKeyResponse.setNumberOfLicense(Integer.parseInt(decryptedArr[1]));
        validateLicenseKeyResponse.setLicenseEndDate(date);

        boolean isExpired = dateTimeUtility.checkExpiry(date);
        if (!isExpired) {
            validateLicenseKeyResponse.setSucessMessage("Valid license");
        } else {
            validateLicenseKeyResponse.setSucessMessage("License expired");
        }

        return validateLicenseKeyResponse;
    }


    public void checkValidLicenseKeyGenerateRequestParams(String organizationName, String licenseEndDate, int numberOfLicense, String timestamp, String secretKey) throws LicenseException {
        if (organizationName == null || organizationName.trim().isEmpty()) {
            throw new LicenseException("Organization name should not be empty");
        }
        if (numberOfLicense == 0) {
            throw new LicenseException("License number should not be zero or empty");
        }
        if (licenseEndDate == null || licenseEndDate.trim().isEmpty()) {
            throw new LicenseException("License end date should not be empty");
        }
        if (timestamp == null) {
            throw new LicenseException("Invalid date format. Date format should be in yyyy-MM-dd");
        }
        if (secretKey == null || secretKey.trim().isEmpty()) {
            throw new LicenseException("Secret key should not be empty");
        }
    }

    public void checkValidLicenseKeyValidateRequestPrams(String licenseKey, String secretKey) throws LicenseException {
        if (licenseKey == null || licenseKey.trim().isEmpty()) {
            throw new LicenseException("License key should not be empty");
        }
        if (secretKey == null || secretKey.trim().isEmpty()) {
            throw new LicenseException("Secret key should not be empty");
        }
    }
}
