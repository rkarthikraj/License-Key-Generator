package com.license.request;

public class ValidateLicenseKeyRequest extends Request {
    private String licenseKey;
    private String secretKey;

    public ValidateLicenseKeyRequest(String licenseKey, String secretKey) {
        this.licenseKey = licenseKey;
        this.secretKey = secretKey;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
