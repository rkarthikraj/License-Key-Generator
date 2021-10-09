package com.license.request;

public class LicenseKeyRequest {
    private String licenseKey;

    public LicenseKeyRequest() {

    }

    public LicenseKeyRequest(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
