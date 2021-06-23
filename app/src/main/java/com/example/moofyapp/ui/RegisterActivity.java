package com.example.moofyapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class RegisterActivity extends AppCompatActivity {
    private TextView tvOpenLogin;
    private EditText etRegisterName, etRegisterEmail, etRegisterPassword;
    private Button button;

    Context mContext;
    BaseApiServices mApiService;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etRegisterName = findViewById(R.id.editTextName);
        etRegisterEmail = findViewById(R.id.editTextEmail);
        etRegisterPassword = findViewById(R.id.editTextPassWord);
        button = (Button) findViewById(R.id.cir_Regis_Button);
        tvOpenLogin = findViewById(R.id.tv_Signin);

        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButton();
            }
        });

        tvOpenLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            }
        });

        changeStatusbarColor();
    }

    private void changeStatusbarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void onLoginButton () {
        mApiService.addRegisterData(
                etRegisterName.getText().toString(),
                etRegisterEmail.getText().toString(),
                etRegisterPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Berhasil mendaftar!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                                } else {
                                    String message = jsonRESULTS.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GAGAL");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_LONG).show();
                    }
                });
    }
}