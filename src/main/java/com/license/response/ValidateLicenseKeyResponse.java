package com.license.response;

public class ValidateLicenseKeyResponse extends Response {
    private String orgName;
    private int numberOfLicense;
    private String licenseEndDate;

    public ValidateLicenseKeyResponse() {

    }

    public ValidateLicenseKeyResponse(String orgName, int numberOfLicense, String licenseEndDate) {
        this.orgName = orgName;
        this.numberOfLicense = numberOfLicense;
        this.licenseEndDate = licenseEndDate;
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

}
