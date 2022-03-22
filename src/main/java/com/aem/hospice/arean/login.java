//package com.aem.hospice.arean;
//
//import com.aem.hospice.mehadi.Patient;
//
//import java.security.NoSuchAlgorithmException;
//import java.security.PublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class login {
//    static Scanner sc = new Scanner(System.in);
//    public static void main(String[] args) throws Exception {
//        System.out.println("(L)ogIn or (S)ignUp");
//
//        String s = sc.nextLine();
//        switch (s.charAt(0)){
//            case 'L':
//                login();
//                break;
//            case 'S':
//                signup();
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    private static void signup() throws Exception {
//        Patient p1 = new Patient();
//        System.out.println("Signup Successful");
//    }
//}
