package com.doitandroid.myvanillastructure;

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
    ImageView btn_back;
    ScrollView main_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        UtilsCollection utilsCollection = new UtilsCollection(this);
        utilsCollection.makeStatusBarColor("#cccccc");

        ////////////////////////////////////////////////////////////////////////////////////////////


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


        View btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    @Override
    public void onBackPressed() {


        MyDialog dialog = new MyDialog(this);
        dialog.setDialogListener(new MyDialogListener() {
            @Override
            public void onPositiveClicked(String string1, String string2) {
                Toast.makeText(getApplicationContext(), string1+ string2, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNegativeClicked() {
                Toast.makeText(getApplicationContext(), "negative", Toast.LENGTH_LONG).show();

            }
        });
        dialog.show();
        // this.finish();
        // super.onBackPressed();
        // backPressCloseHandler.onBackPressed();
        // this.finishAffinity();

    }

}
