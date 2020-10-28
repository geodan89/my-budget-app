package com.springexercise.mybudgetapp.web.controller;


//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncodingTests {

    static final String PASSWORD = "password";

//    @Test
//    void testNoOp() {
//        PasswordEncoder noOP = NoOpPasswordEncoder.getInstance();
//        System.out.println(noOP.encode(PASSWORD));
//    }
//
//    @Test
//    void testLdap() {
//        PasswordEncoder ldap = new LdapShaPasswordEncoder();
//        System.out.println(ldap.encode(PASSWORD));
//        System.out.println(ldap.encode(PASSWORD));
//
//        String encodedPwd = ldap.encode(PASSWORD);
//
//        Assertions.assertTrue(ldap.matches(PASSWORD, encodedPwd));
//        System.out.println(ldap.encode("tiger"));
//    }
//
//    @Test
//    void testSha256() {
//        PasswordEncoder sha256 = new StandardPasswordEncoder();
//        System.out.println(sha256.encode(PASSWORD));
//        System.out.println(sha256.encode(PASSWORD));
//        System.out.println(sha256.encode("user"));
//    }
//
//    @Test
//    void testBCrypt() {
//        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode("spring"));
//        System.out.println(bcrypt.encode("admin"));
//    }
//
//    @Test
//    void testCustomBCrypt() {
//        PasswordEncoder bcrypt = new BCryptPasswordEncoder(10);
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode(PASSWORD));
//        System.out.println(bcrypt.encode("tiger"));
//    }
//
//    @Test
//    void hashingExample() {
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
//
//        String saltedPassword = PASSWORD + UUID.randomUUID();
//        System.out.println(DigestUtils.md5DigestAsHex(saltedPassword.getBytes()));
//    }
}
