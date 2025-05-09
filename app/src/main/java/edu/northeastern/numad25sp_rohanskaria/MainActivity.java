package edu.northeastern.numad25sp_rohanskaria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    private Button aboutMeButton;
    private Button calcButton;
    private Button linkCollectorButton;

    private Button primeSearchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aboutMeButton = findViewById(R.id.aboutMeButton);
        aboutMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAboutMe();
            }
        });

        calcButton = findViewById(R.id.quickCalcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCalculator();
            }
        });

        linkCollectorButton = findViewById(R.id.linkCollectorButton);
        linkCollectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLinkCollector();
            }
        });

        primeSearchButton = findViewById(R.id.primeSearchButton);
        primeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPrimeSearch();
            }
        });

        primeSearchButton = findViewById(R.id.locationButton);
        primeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLocationActivity();
            }
        });

    }

    private void launchAboutMe() {
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }

    private void launchCalculator() {
        Intent intent = new Intent(this, CalcActivity.class);
        startActivity(intent);
    }

    private void launchLinkCollector() {
        Intent intent = new Intent(this, LinkCollectorActivity.class);
        startActivity(intent);
    }

    private void launchPrimeSearch() {
        Intent intent = new Intent(this, PrimeActivity.class);
        startActivity(intent);
    }

    private void launchLocationActivity() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }
}