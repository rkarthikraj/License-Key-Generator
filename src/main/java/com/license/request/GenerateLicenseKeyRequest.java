package com.license.request;

public class GenerateLicenseKeyRequest extends Request {
    private String orgName;
    private int numberOfLicense;
    private String licenseEndDate;
    private String secretKey;

    public GenerateLicenseKeyRequest(String orgName, int numberOfLicense, String licenseEndDate, String secretKey) {
        this.orgName = orgName;
        this.numberOfLicense = numberOfLicense;
        this.licenseEndDate = licenseEndDate;
        this.secretKey = secretKey;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getNumberOfLicense() {
        return numberOfLicense;
    }

    public void setNumberOfLicense(int numberOfLicense) {
        this.numberOfLicense = numberOfLicense;
    }

    public String getLicenseEndDate() {
        return licenseEndDate;
    }

    public void setLicenseEndDate(String licenseEndDate) {
        this.licenseEndDate = licenseEndDate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
