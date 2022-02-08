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
        
        String jwtSecret = "jwtSecret";
        String jwtExpiresSec = "jwtExpiresSec";
        String jwtSubject = "jwtSubject";
        String bcryptSaltRounds = "bcryptSaltRounds";

        setup(key);
        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);
        String encryptJwtSecret = jasyptEncrypt(jwtSecret);
        String encryptJwtExpiresSec = jasyptEncrypt(jwtExpiresSec);
        String encryptJwtSubject = jasyptEncrypt(jwtSubject);
        String encryptBcryptSaltRounds = jasyptEncrypt(bcryptSaltRounds);

        System.out.println("encryptUrl: " + encryptUrl);
        System.out.println("encryptUsername: " + encryptUsername);
        System.out.println("encryptPassword: " + encryptPassword);
        System.out.println("encryptJwtSecret: " + encryptJwtSecret);
        System.out.println("encryptJwtExpiresSec: " + encryptJwtExpiresSec);
        System.out.println("encryptJwtSubject: " + encryptJwtSubject);
        System.out.println("encryptBcryptSaltRounds: " + encryptBcryptSaltRounds);

        Assertions.assertThat(jasyptDecrypt(encryptUrl)).isEqualTo(url);
        Assertions.assertThat(jasyptDecrypt(encryptUsername)).isEqualTo(username);
        Assertions.assertThat(jasyptDecrypt(encryptPassword)).isEqualTo(password);
        Assertions.assertThat(jasyptDecrypt(encryptJwtSecret)).isEqualTo(jwtSecret);
        Assertions.assertThat(jasyptDecrypt(encryptJwtExpiresSec)).isEqualTo(jwtExpiresSec);
        Assertions.assertThat(jasyptDecrypt(encryptJwtSubject)).isEqualTo(jwtSubject);
        Assertions.assertThat(jasyptDecrypt(encryptBcryptSaltRounds)).isEqualTo(bcryptSaltRounds);
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