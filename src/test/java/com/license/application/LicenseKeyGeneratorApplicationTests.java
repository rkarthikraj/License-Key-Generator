package com.license.application;

import com.license.request.GenerateLicenseKeyRequest;
import com.license.response.GenerateLicenseKeyResponse;
import com.license.service.LicenseService;
import com.license.utils.LicenseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LicenseKeyGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testValidLicense() throws Exception {
        LicenseService service = new LicenseService();
        GenerateLicenseKeyRequest licenseRequest = new GenerateLicenseKeyRequest("TestOrg", 1, "2025-12-31", "abc123");
        GenerateLicenseKeyResponse keyResponse = service.generateLicense(licenseRequest);
        assert (200 == keyResponse.getStatus());
    }

    @Test
    public void testInvalidOrg() {
        LicenseService service = new LicenseService();
        GenerateLicenseKeyRequest licenseRequest = new GenerateLicenseKeyRequest("", 1, "2025-12-31", "abc123");
        try {
            GenerateLicenseKeyResponse keyResponse = service.generateLicense(licenseRequest);
        } catch (Exception e) {
            assert (e instanceof LicenseException);
            return;
        }
        assert (false);
    }
}
