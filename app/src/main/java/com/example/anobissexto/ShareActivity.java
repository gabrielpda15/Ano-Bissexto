package com.example.anobissexto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

public class ShareActivity extends AppCompatActivity {

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        int year = getIntent().getIntExtra("year", 0);

        TextView txtResult = findViewById(R.id.txtResult);
        TextView txtName = findViewById(R.id.txtName);

        Button btnShare = findViewById(R.id.btnShare);

        btnShare.setEnabled(false);

        if (isLeapYear(year)) {
            result = getString(R.string.result_true).replace("{year}", Integer.toString(year));
        } else {
            result = getString(R.string.result_false).replace("{year}", Integer.toString(year));
        }

        txtResult.setText(result);

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    btnShare.setEnabled(false);
                } else {
                    btnShare.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnShare.setOnClickListener(view -> {
            String text = txtName.getText().toString() + ", ";
            text += result;

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
            sendIntent.setType("text/plain");

            startActivity(sendIntent);
        });
    }

    private boolean isLeapYear(int year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }
}