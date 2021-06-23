package com.example.moofyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_MOOFY_APP = "spMoofyApp";

    public static final String SP_ID = "spId";
    public static final String SP_NAME = "spName";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_ROLE_ID = "spRole_Id";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        sp = context.getSharedPreferences(SP_MOOFY_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSpId() {
        return sp.getString(SP_ID, "");
    }

    public String getSpName() {
        return sp.getString(SP_NAME, "");
    }

    public String getSpEmail() {
        return sp.getString(SP_EMAIL, "");
    }
    public String getSpPassword() {
        return sp.getString(SP_PASSWORD, "");
    }

    public String getSpRole_Id() {
        return sp.getString(SP_ROLE_ID, "");
    }

    public Boolean getSpSudahLogin() {
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }


}
