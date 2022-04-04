package com.aem.hospice.Classes;

public interface LogInManager {
    static Boolean LogInValidate(String uid, String pass) {
        return null;
    }

    static String GenerateUid(String table, String uidcolname, int login) {
        return null;
    }

    static String GenerateUid(String table, String uidcolname, int login, String password) {
        return null;

    }

    static void ChangePassword(String uid, String oldpassword, String newpassword) {
    }

}
