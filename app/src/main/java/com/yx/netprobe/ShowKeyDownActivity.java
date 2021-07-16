package com.yx.netprobe;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ShowKeyDownActivity extends AppCompatActivity {

    private TextView textView;
    private String desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_key_down);

        textView = findViewById(R.id.text_view);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        desc = String.format(Locale.ENGLISH, "physical key down is %d\n%s", keyCode, desc);
        textView.setText(desc);
        //返回true表示不再响应系统动作，返回false表示继续响应系统动作
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        desc = String.format(Locale.ENGLISH, "physical key up is %d\n%s", keyCode, desc);
        textView.setText(desc);
        //返回true表示不再响应系统动作，返回false表示继续响应系统动作
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        //return super.onKeyMultiple(keyCode, repeatCount, event);
        desc = String.format(Locale.ENGLISH, "physical key multiple is %d(%d)\n%s", keyCode, repeatCount, desc);
        textView.setText(desc);
        //返回true表示不再响应系统动作，返回false表示继续响应系统动作
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_ENTER) {
//
//        }
//        return super.onKeyLongPress(keyCode, event);
        desc = String.format(Locale.ENGLISH, "physical key long press is %d\n%s", keyCode, desc);
        textView.setText(desc);
        return false;
    }
}
