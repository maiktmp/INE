package com.tec.ine.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tec.ine.R;
import com.tec.ine.databinding.ActivityLoginBinding;
import com.tec.ine.databinding.ActivityMainBinding;
import com.tec.ine.interactors.FBInteractors;
import com.tec.ine.ui.main.MainActivity;

public class LoginActivity extends AppCompatActivity {


    private ActivityLoginBinding vBind;
    private FBInteractors fbInteractors;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fbInteractors = FBInteractors.getInstance();
        fbInteractors.getAuthUser(user -> {
            if (user == null) {
                vBind = DataBindingUtil.setContentView(this, R.layout.activity_login);
                setUpLoginBtn();
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }

    private void setUpLoginBtn() {
        vBind.btnLogin.setOnClickListener(v -> {
            vBind.tilPassword.setError(null);
            if (vBind.etPassword.getText().toString().equals("") || vBind.etUser.getText().toString().equals("")) {
                vBind.tilPassword.setError("Verifique sus credenciales de acceso");
                return;
            }
            fbInteractors.signUp(
                    this,
                    vBind.etUser.getText().toString(),
                    vBind.etPassword.getText().toString(),
                    user -> {
                        if (user == null) {
                            vBind.tilPassword.setError("Verifique sus credenciales de acceso");
                            return;
                        }
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
            );
        });
    }
}

