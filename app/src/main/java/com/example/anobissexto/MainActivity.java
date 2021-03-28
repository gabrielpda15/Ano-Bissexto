package com.example.anobissexto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtYear = findViewById(R.id.txtYear);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setEnabled(false);

        txtYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    btnSubmit.setEnabled(false);
                } else {
                    btnSubmit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShareActivity.class);

            intent.putExtra("year", tryParseInt(txtYear.getText().toString()));

            startActivity(intent);
        });
    }

    private int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}