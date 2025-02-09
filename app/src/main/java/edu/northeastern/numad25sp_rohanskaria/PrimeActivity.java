package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class PrimeActivity extends AppCompatActivity{
    private Button findPrimesButton;
    private Button terminateSearchButton;
    private CheckBox pacifierSwitchCheck;
    private TextView currNumberText;
    private TextView lastPrimeNumberText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);
        findPrimesButton = findViewById(R.id.findPrimesButton);
        terminateSearchButton = findViewById(R.id.terminateSearchButton);
        pacifierSwitchCheck = findViewById(R.id.pacifierSwitchCheck);
        currNumberText = findViewById(R.id.currNumberText);
        lastPrimeNumberText = findViewById(R.id.lastPrimeNumberText);
    }

}
