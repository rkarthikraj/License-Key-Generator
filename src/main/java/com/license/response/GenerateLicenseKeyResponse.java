package com.license.response;

public class GenerateLicenseKeyResponse extends Response {
    private String licenseKey = "";
    private String secretKey = "";

    public GenerateLicenseKeyResponse() {

    }

    public GenerateLicenseKeyResponse(String licenseKey) {
        this.licenseKey = licenseKey;
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
