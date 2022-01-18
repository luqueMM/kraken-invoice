package bo.vulcan.kraken.invoice.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import bo.vulcan.kraken.invoice.data.model.db.Branch;
import bo.vulcan.kraken.invoice.data.model.db.Company;
import bo.vulcan.kraken.invoice.data.model.db.CompanyDefaultsDTO;
import bo.vulcan.kraken.invoice.data.model.db.Cufd;
import bo.vulcan.kraken.invoice.data.model.db.Cuis;
import bo.vulcan.kraken.invoice.data.model.db.DigitalSign;
import bo.vulcan.kraken.invoice.data.model.db.SalePoint;
import bo.vulcan.kraken.invoice.data.model.db.SystemStatus;
import bo.vulcan.kraken.invoice.data.model.db.UserFilter;
import bo.vulcan.kraken.invoice.di.scope.PreferenceInfo;

public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_CURRENT_USER = "PREF_KEY_CURRENT_USER";
    private static final String PREF_KEY_CURRENT_COMPANY = "PREF_KEY_CURRENT_COMPANY";
    private static final String PREF_KEY_CURRENT_BRANCH = "PREF_KEY_CURRENT_BRANCH";
    private static final String PREF_KEY_CURRENT_SALE_POINT = "PREF_KEY_CURRENT_SALE_POINT";
    private static final String PREF_KEY_CURRENT_COMPANY_DEFAULTS = "PREF_KEY_CURRENT_COMPANY_DEFAULTS";
    private static final String PREF_KEY_CURRENT_DIGITAL_SIGN = "PREF_KEY_CURRENT_DIGITAL_SIGN";
    private static final String PREF_KEY_CURRENT_CUFD = "PREF_KEY_CURRENT_CUFD";
    private static final String PREF_KEY_CURRENT_CUIS = "PREF_KEY_CURRENT_CUIS";
    private static final String PREF_KEY_CURRENT_SYSTEM_STATUS = "PREF_KEY_CURRENT_SYSTEM_STATUS";

    private final SharedPreferences mPrefs;
    private final Gson mGson;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName, Gson gson) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        mGson = gson;
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public UserFilter getUser() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_USER, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, UserFilter.class);
        } else {
            return null;
        }
    }

    @Override
    public void setUser(UserFilter value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER, jsonString).apply();
    }

    @Override
    public Company getCompany() {

        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_COMPANY, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, Company.class);
        } else {
            return null;
        }
    }

    @Override
    public void setCompany(Company value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_COMPANY, jsonString).apply();
    }

    @Override
    public Branch getBranch() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_BRANCH, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, Branch.class);
        } else {
            return null;
        }
    }

    @Override
    public void setBranch(Branch value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_BRANCH, jsonString).apply();
    }

    @Override
    public SalePoint getSalePoint() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_SALE_POINT, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, SalePoint.class);
        } else {
            return null;
        }
    }

    @Override
    public void setSalePoint(SalePoint value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_SALE_POINT, jsonString).apply();
    }

    @Override
    public DigitalSign getDigitalSign() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_DIGITAL_SIGN, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, DigitalSign.class);
        } else {
            return null;
        }
    }

    @Override
    public void setDigitalSign(DigitalSign value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_DIGITAL_SIGN, jsonString).apply();
    }

    @Override
    public Cufd getCufd() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_CUFD, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, Cufd.class);
        } else {
            return null;
        }
    }

    @Override
    public void setCufd(Cufd value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_CUFD, jsonString).apply();
    }

    @Override
    public Cuis getCuis() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_CUIS, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, Cuis.class);
        } else {
            return null;
        }
    }

    @Override
    public void setCuis(Cuis value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_CUIS, jsonString).apply();
    }

    @Override
    public CompanyDefaultsDTO getCompanyDefaults() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_COMPANY_DEFAULTS, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, CompanyDefaultsDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void setCompanyDefaults(CompanyDefaultsDTO value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_COMPANY_DEFAULTS, jsonString).apply();
    }

    @Override
    public SystemStatus getSystemStatus() {
        String jsonString = mPrefs.getString(PREF_KEY_CURRENT_SYSTEM_STATUS, null);
        if (jsonString != null) {
            return mGson.fromJson(jsonString, SystemStatus.class);
        } else {
            return null;
        }
    }

    @Override
    public void setSystemStatus(SystemStatus value) {
        String jsonString = mGson.toJson(value);
        mPrefs.edit().putString(PREF_KEY_CURRENT_SYSTEM_STATUS, jsonString).apply();

    }
}
