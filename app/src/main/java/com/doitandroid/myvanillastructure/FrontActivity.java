package com.doitandroid.myvanillastructure;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.doitandroid.myvanillastructure.utils.UtilsCollection;

public class FrontActivity extends AppCompatActivity {
    FrameLayout btn_login, btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        UtilsCollection utilsCollection = new UtilsCollection(this);
        utilsCollection.makeStatusBarColor("#cccccc");


        ////////////////////////////////////////////////////////////////////////////////////////////

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
/*                intent.addFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NO_HISTORY
                );*/
                startActivity(intent);
            }
        });


        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.addFlags(
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                startActivity(intent);
            }
        });


    }
}
