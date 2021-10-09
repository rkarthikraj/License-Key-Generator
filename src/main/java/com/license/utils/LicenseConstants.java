package com.license.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class LicenseConstants {
    public static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    public static final int TAG_LENGTH_BIT = 128;
    public static final int IV_LENGTH_BYTE = 12;
    public static final int SALT_LENGTH_BYTE = 16;
    public static final Charset UTF_8 = StandardCharsets.UTF_8;
}
