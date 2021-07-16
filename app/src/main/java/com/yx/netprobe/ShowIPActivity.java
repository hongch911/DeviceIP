package com.yx.netprobe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowIPActivity extends AppCompatActivity {

    private Button javaBtn, jniBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ip);

        javaBtn = findViewById(R.id.java_ip_btn);
        jniBtn = findViewById(R.id.jni_ip_btn);
        textView = findViewById(R.id.text_view);

        javaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(DeviceUtils.getIPAddress());
            }
        });
        jniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(cpp_get_ip());
            }
        });
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        try {
            System.loadLibrary("native-lib");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public native String cpp_get_ip();
}
