package com.license.response;

public class LicenseResponse {
    private String orgName;
    private int numberOfLicense;
    private String licenseEndDate;
    private String message;

    public LicenseResponse() {

    }

    public LicenseResponse(String orgName, int numberOfLicense, String licenseEndDate, String message) {
        this.orgName = orgName;
        this.numberOfLicense = numberOfLicense;
        this.licenseEndDate = licenseEndDate;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
