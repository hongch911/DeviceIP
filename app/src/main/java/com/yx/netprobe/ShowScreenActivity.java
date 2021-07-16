package com.yx.netprobe;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class ShowScreenActivity extends AppCompatActivity {

    private Button screenBtn;
    private Button valueAdapterBtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_screen);

        screenBtn = findViewById(R.id.get_screen_btn);
        valueAdapterBtn = findViewById(R.id.get_adapter_btn);
        textView = findViewById(R.id.text_view);

        screenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = getScreenSize();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("宽度x高度(像素):");
                stringBuilder.append(String.format(Locale.ENGLISH, "%dx%d", displayMetrics.widthPixels, displayMetrics.heightPixels));
                stringBuilder.append("\r\n");
                stringBuilder.append("屏幕密度:");
                stringBuilder.append(String.format(Locale.ENGLISH, "%f", displayMetrics.density));
                stringBuilder.append("\r\n");
                stringBuilder.append("屏幕密度dpi:");
                stringBuilder.append(String.format(Locale.ENGLISH, "%d", displayMetrics.densityDpi));
                stringBuilder.append("\r\n");
                stringBuilder.append("ldpi：约为 120dpi。mdpi：约为 160dpi。hdpi：约为 240dpi。xhdpi：约为 320dpi。xxhdpi：约为 480dpi。xxxhdpi：约为 640dpi。\n");
                stringBuilder.append("宽度x高度(dp):");
                stringBuilder.append(String.format(Locale.ENGLISH, "%fx%f", displayMetrics.widthPixels/displayMetrics.density, displayMetrics.heightPixels/displayMetrics.density));
                stringBuilder.append("\r\n");

                textView.setText(stringBuilder.toString());
            }
        });
        valueAdapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = getCurrentLocal();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("语言:");
                stringBuilder.append(locale.getLanguage());
                stringBuilder.append("\r\n");
                stringBuilder.append("国家码:");
                stringBuilder.append(locale.getCountry());
                stringBuilder.append("\r\n");

                textView.setText(stringBuilder.toString());
            }
        });
    }

    private DisplayMetrics getScreenSize() {
        WindowManager manager = getWindowManager();
        DisplayMetrics metrics =new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    private Locale getCurrentLocal() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }
        return locale;
    }
}
