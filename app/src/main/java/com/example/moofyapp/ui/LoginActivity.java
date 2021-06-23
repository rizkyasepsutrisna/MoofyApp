package com.example.moofyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moofyapp.R;
import com.example.moofyapp.utils.SharedPrefManager;
import com.example.moofyapp.utils.api.BaseApiServices;
import com.example.moofyapp.utils.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;
    private Button button;
    private TextView tvRegister;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.cirLoginButton);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassWord);
        tvRegister = findViewById(R.id.tv_Signup);
        mContext = this;

        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });

        if (sharedPrefManager.getSpSudahLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        //Changing Status Bar Color Icon
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    public void openMainActivity() {
        mApiService.authapimoofy(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")){

                                    Toast.makeText(mContext, "Berhasil login", Toast.LENGTH_LONG).show();
                                    String id = jsonRESULTS.getJSONObject("data").getString("id");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, id);
                                    String name = jsonRESULTS.getJSONObject("data").getString("name");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAME, name);
                                    String password = jsonRESULTS.getJSONObject("data").getString("password");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_PASSWORD, password);
                                    String role_id = jsonRESULTS.getJSONObject("data").getString("role_id");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE_ID, role_id);

                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    startActivity(new Intent(mContext, MainActivity.class)
                                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();

                                    System.out.println(sharedPrefManager.SP_NAME);
                                } else {
                                    String message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}