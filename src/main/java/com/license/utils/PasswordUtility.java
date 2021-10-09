package com.license.utils;

import java.util.UUID;

public class PasswordUtility {
    public String generatePassword() {
        String password = "";
        UUID uuid = UUID.randomUUID();
        password = uuid.toString().replace("-", "");
        return password;
    }

    public String splitter(String password) {
        String splitValue = "";
        StringBuilder passwordSB = new StringBuilder(password);
        int interval = 4;
        char separator = '-';
        int passwordLength = password.length();

        for (int i = 0; i < passwordLength / interval; i++) {
            passwordSB.insert(((i + 1) * interval) + i, separator);
        }

        String withDashes = passwordSB.toString();
        String lastCharacter = withDashes.substring(withDashes.length() - 1);
        if(lastCharacter.equals("-")){
            splitValue = withDashes.substring(0, withDashes.length() - 1);
        }

        return splitValue;
    }
}
