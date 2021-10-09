package com.license.controller;

import com.license.request.GenerateLicenseKeyRequest;
import com.license.request.ValidateLicenseKeyRequest;
import com.license.response.GenerateLicenseKeyResponse;
import com.license.response.ValidateLicenseKeyResponse;
import com.license.service.LicenseService;
import com.license.utils.LicenseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @RequestMapping(method = RequestMethod.POST, value = "/generate-license")
    public GenerateLicenseKeyResponse generateLicense(@RequestBody GenerateLicenseKeyRequest licenseInfoRequest) {
        GenerateLicenseKeyResponse generateLicenseKeyResponse = null;
        try {
            generateLicenseKeyResponse = licenseService.generateLicense(licenseInfoRequest);
        } catch (LicenseException e) {
            generateLicenseKeyResponse = new GenerateLicenseKeyResponse();
            generateLicenseKeyResponse.setErrorMessage(e.getMessage());
        }
        return generateLicenseKeyResponse;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/validate-license")
    public ValidateLicenseKeyResponse validateLicense(@RequestBody ValidateLicenseKeyRequest licenseKeyRequest) throws Exception {
        ValidateLicenseKeyResponse validateLicenseKeyResponse = null;
        try {
            validateLicenseKeyResponse = licenseService.validateLicense(licenseKeyRequest);
        } catch (Exception e) {
            validateLicenseKeyResponse = new ValidateLicenseKeyResponse();
            validateLicenseKeyResponse.setErrorMessage(e.getMessage());
        }
        return validateLicenseKeyResponse;
    }
}

