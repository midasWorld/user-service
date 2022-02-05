package com.midas.userservice.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {

    StandardPBEStringEncryptor encryptor;

    @Test
    public void jasypt_test(){
        String key = "key"; // key - (⭐ :️ should be secret!!!!)
        String url = "url"; // url - ⭐️
        String username = "username"; // username - ⭐️
        String password = "password"; // password - ⭐

        setup(key);
        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("encryptUrl: " + encryptUrl);
        System.out.println("encryptUsername: " + encryptUsername);
        System.out.println("encryptPassword: " + encryptPassword);

        Assertions.assertThat(jasyptDecrypt(encryptUrl)).isEqualTo(url);
        Assertions.assertThat(jasyptDecrypt(encryptUsername)).isEqualTo(username);
        Assertions.assertThat(jasyptDecrypt(encryptPassword)).isEqualTo(password);
    }

    private void setup(String key) {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
    }

    private String jasyptEncrypt(String str) {
        return encryptor.encrypt(str);
    }

    private String jasyptDecrypt(String str) {
        return encryptor.decrypt(str);
    }
}