package com.doitandroid.myvanillastructure.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.doitandroid.myvanillastructure.R;

public class MyDialog extends Dialog {

    private static final int LAYOUT = R.layout.dialog_2btns;

    private Context context;

    private TextView textView_title, textView_content, textView_positive, textView_negative;

    private String option;

    private MyDialogListener dialogListener;

    public MyDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public MyDialog(@NonNull Context context, String option) {
        super(context);
        this.context = context;
        this.option = option;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        textView_title = findViewById(R.id.dialog_title);
        textView_content = findViewById(R.id.dialog_content);
        textView_positive = findViewById(R.id.dialog_positive);
        textView_negative = findViewById(R.id.dialog_negative);

        if(option != null){

        }

        textView_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "some word";
                String string_s = "some word_s";
                // 여기서 다이얼로그 내 뷰의 값을 가져오면 된다.
                dialogListener.onPositiveClicked(string, string_s);
                dismiss();
            }
        });

        textView_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onNegativeClicked();
                cancel();
            }
        });
    }


    public void setDialogListener(MyDialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}