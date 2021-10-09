package com.license.controller;

import com.license.request.LicenseKeyRequest;
import com.license.request.LicenseRequest;
import com.license.response.LicenseKeyResponse;
import com.license.response.LicenseResponse;
import com.license.service.LicenseService;
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
    public LicenseKeyResponse generateLicense(@RequestBody LicenseRequest licenseInfoRequest) throws Exception {
        LicenseKeyResponse licenseKeyResponse = new LicenseKeyResponse();
        licenseKeyResponse = licenseService.generateLicense(licenseInfoRequest);
        return licenseKeyResponse;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/validate-license")
    public LicenseResponse validateLicense(@RequestBody LicenseKeyRequest licenseKeyRequest) throws Exception {
        LicenseResponse licenseResponse = new LicenseResponse();
        licenseResponse = licenseService.validateLicense(licenseKeyRequest);
        return licenseResponse;
    }
}

