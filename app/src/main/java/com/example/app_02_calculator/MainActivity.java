package com.example.app_02_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edit_text_millimeters;
    EditText edit_text_centimeters;
    EditText edit_text_decimeters;
    EditText edit_text_meters;
    TextView text_view_millimeters;
    TextView text_view_centimeters;
    TextView text_view_decimeters;
    TextView text_view_meters;
    Button button_calculate;
    Button button_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        getViews();
        setUpListeners();
        resetValues();
    }

    private void getViews() {
        edit_text_millimeters = findViewById(R.id.edit_text_millimeters);
        edit_text_centimeters = findViewById(R.id.edit_text_centimeters);
        edit_text_decimeters = findViewById(R.id.edit_text_decimeters);
        edit_text_meters = findViewById(R.id.edit_text_meters);
        text_view_millimeters = findViewById(R.id.text_view_millimeters);
        text_view_centimeters = findViewById(R.id.text_view_centimeters);
        text_view_decimeters = findViewById(R.id.text_view_decimeters);
        text_view_meters = findViewById(R.id.text_view_meters);
        button_calculate = findViewById(R.id.button_calculate);
        button_reset = findViewById(R.id.button_reset);
    }

    private void setUpListeners() {
        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateValues();
            }
        });
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });
    }

    private void calculateValues() {
        double millimeters = 0;
        double centimeters = 0;
        double decimeters = 0;
        double meters = 0;
        boolean isError = false;
        if (!edit_text_millimeters.getText().toString().isEmpty()) {
            millimeters = Double.parseDouble(edit_text_millimeters.getText().toString());
            centimeters = millimeters / 10;
            decimeters = centimeters / 10;
            meters = decimeters / 10;
        } else if (edit_text_centimeters.getText().length() > 0) {
            centimeters = Double.parseDouble(edit_text_centimeters.getText().toString());
            millimeters = centimeters * 10;
            decimeters = centimeters / 10;
            meters = decimeters / 10;
        } else if (edit_text_decimeters.getText().length() > 0) {
            decimeters = Double.parseDouble(edit_text_decimeters.getText().toString());
            centimeters = decimeters * 10;
            millimeters = centimeters * 10;
            meters = decimeters / 10;
        } else if (edit_text_meters.getText().length() > 0) {
            meters = Double.parseDouble(edit_text_meters.getText().toString());
            decimeters = meters * 10;
            centimeters = decimeters * 10;
            millimeters = centimeters * 10;
        } else {
            Toast.makeText(this, "Enter at least come value", Toast.LENGTH_SHORT).show();
            isError = true;
        }

        if (!isError) {
            millimeters = (double) Math.round(millimeters * 100) / 100;
            centimeters = (double) Math.round(centimeters * 100) / 100;
            decimeters = (double) Math.round(decimeters * 100) / 100;
            meters = (double) Math.round(meters * 100) / 100;

            updateValues(millimeters, centimeters, decimeters, meters);
        }

    }

    private void updateValues(double mills, double cent, double dec, double met) {
        edit_text_millimeters.setVisibility(View.GONE);
        edit_text_centimeters.setVisibility(View.GONE);
        edit_text_decimeters.setVisibility(View.GONE);
        edit_text_meters.setVisibility(View.GONE);

        text_view_millimeters.setVisibility(View.VISIBLE);
        text_view_centimeters.setVisibility(View.VISIBLE);
        text_view_decimeters.setVisibility(View.VISIBLE);
        text_view_meters.setVisibility(View.VISIBLE);

        text_view_millimeters.setText(String.valueOf(mills));
        text_view_centimeters.setText(String.valueOf(cent));
        text_view_decimeters.setText(String.valueOf(dec));
        text_view_meters.setText(String.valueOf(met));
    }

    private void resetValues() {
        text_view_millimeters.setVisibility(View.GONE);
        text_view_centimeters.setVisibility(View.GONE);
        text_view_decimeters.setVisibility(View.GONE);
        text_view_meters.setVisibility(View.GONE);

        edit_text_millimeters.setVisibility(View.VISIBLE);
        edit_text_centimeters.setVisibility(View.VISIBLE);
        edit_text_decimeters.setVisibility(View.VISIBLE);
        edit_text_meters.setVisibility(View.VISIBLE);
        edit_text_millimeters.setText("");
        edit_text_centimeters.setText("");
        edit_text_decimeters.setText("");
        edit_text_meters.setText("");
    }

}

