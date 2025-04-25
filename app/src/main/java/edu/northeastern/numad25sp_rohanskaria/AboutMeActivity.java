package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {
    private TextView nameText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        nameText = findViewById(R.id.textView);
        emailText = findViewById(R.id.editEmailAddress);
        emailText.setEnabled(false);

    }
}
