package com.license.response;

public class LicenseKeyResponse {
    private String licenseKey = "";
    private String message;

    public LicenseKeyResponse() {

    }

    public LicenseKeyResponse(String licenseKey, String message) {
        this.licenseKey = licenseKey;
        this.message = message;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
