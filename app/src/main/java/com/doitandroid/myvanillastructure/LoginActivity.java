package com.doitandroid.myvanillastructure;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.doitandroid.myvanillastructure.customview.ClearableEditText;
import com.doitandroid.myvanillastructure.customview.MyDialog;
import com.doitandroid.myvanillastructure.customview.MyDialogListener;
import com.doitandroid.myvanillastructure.utils.BackPressCloseHandler;
import com.doitandroid.myvanillastructure.utils.SoftKeyboard;
import com.doitandroid.myvanillastructure.utils.UtilsCollection;

public class LoginActivity extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;
    ClearableEditText login_account, login_password;
    FrameLayout btn_back, btn_complete;
    ScrollView main_layout;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        UtilsCollection utilsCollection = new UtilsCollection(this);
        utilsCollection.makeStatusBarColor("#cccccc");

        ////////////////////////////////////////////////////////////////////////////////////////////

        activity = this;
        main_layout = findViewById(R.id.login_layout);
        ViewTreeObserver observer = main_layout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int viewHeight = main_layout.getMeasuredHeight();
                int contentHeight = main_layout.getChildAt(0).getHeight();
                if(viewHeight - contentHeight < 0) {

                    View lastChild = main_layout.getChildAt(main_layout.getChildCount() - 1);
                    int bottom = lastChild.getBottom() + main_layout.getPaddingBottom();
                    int sy = main_layout.getScrollY();
                    int sh = main_layout.getHeight();
                    int delta = bottom - (sy + sh);

                    main_layout.smoothScrollBy(0, delta);
                    // scrollable
                }
            }
        });


        login_account = findViewById(R.id.login_account);
        login_account.setHint("email or username");

        login_password = findViewById(R.id.login_password);
        login_password.setHint("password");
        login_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btn_back = findViewById(R.id.login_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        btn_back = findViewById(R.id.login_complete);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("init_app", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("auto_login", 1);
                editor.commit();

                Intent intent = new Intent(activity, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            /*intent.addFlags(
                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK
            );*/


                startActivity(intent);
            }
        });

        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {
        String login_account_trim = login_account.getText().toString().trim();
        String login_password_raw = login_password.getText().toString();
        if (!(login_account_trim.equals("") && login_password_raw.equals(""))){
            MyDialog dialog = new MyDialog(this, "Go back", "Really?", "okay--", "noooo");
            dialog.setDialogListener(new MyDialogListener() {
                @Override
                public void onPositiveClicked() {
                    activity.finish();
                }

                @Override
                public void onNegativeClicked() {

                }
            });
            dialog.show();
        } else {
            activity.finish();
        }


        // this.finish();
        // super.onBackPressed();
        // backPressCloseHandler.onBackPressed();
        // this.finishAffinity();

    }

}
