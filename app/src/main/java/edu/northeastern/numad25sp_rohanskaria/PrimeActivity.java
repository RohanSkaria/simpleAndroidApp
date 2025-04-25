package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class PrimeActivity extends AppCompatActivity{
    private Button findPrimesButton;
    private Button terminateSearchButton;
    private CheckBox pacifierSwitchCheck;
    private TextView currNumberText;
    private TextView lastPrimeNumberText;

    private Thread searchThread;
    private volatile boolean isSearching = false;

    private long currNum = 3;
    private long currPrime = 0;

    private Handler mainHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        findPrimesButton = findViewById(R.id.findPrimesButton);
        terminateSearchButton = findViewById(R.id.terminateSearchButton);
        pacifierSwitchCheck = findViewById(R.id.pacifierSwitchCheck);
        currNumberText = findViewById(R.id.currNumberText);
        lastPrimeNumberText = findViewById(R.id.lastPrimeNumberText);

        mainHandler = new Handler(Looper.getMainLooper());

        findPrimesButton.setOnClickListener(v-> startPrimeSearch());
        terminateSearchButton.setOnClickListener(v-> stopPrimeSearch());

        if (savedInstanceState != null) {
            currNum = savedInstanceState.getLong("currentNumber", 3);
            currPrime = savedInstanceState.getLong("latestPrime", 0);
            isSearching = savedInstanceState.getBoolean("isSearching", false);
            pacifierSwitchCheck.setChecked(savedInstanceState.getBoolean("pacifierState", false));
            updateUI();


            if (isSearching) {
                new Handler().post(() -> startPrimeSearch());
            }
        }

        updateButtonStates();
    }

    //Helper function to determine if a number is prime
    private boolean isPrime(long n) {
        if(n<=1) {
            return false;
        }
        for (long i = 2; i<n; i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }

    //helper function to display message to screen and respective text views
    private void updateUI() {
        currNumberText.setText("IS THIS PRIME:\n       " + currNum);
        lastPrimeNumberText.setText(" "+(currPrime > 0 ? currPrime: "N/A") + "\nIS PRIME");
    }

    //enables buttons on screen based on search state.
    private void updateButtonStates() {
        findPrimesButton.setEnabled(!isSearching);
        terminateSearchButton.setEnabled(isSearching);
    }
    private void startPrimeSearch() {
        if (isSearching) {
            return;
        }

        isSearching = true;

        searchThread = new Thread(() -> {
            long searchNum = currNum;

            while (isSearching) {
                if (isPrime(searchNum)) {
                    currPrime = searchNum;
                    currNum = searchNum;
                    mainHandler.post(this::updateUI);
                }
                searchNum += 2;
                currNum = searchNum;
                mainHandler.post(this::updateUI);
                Thread.yield();
            }
        });

        searchThread.start();
        updateButtonStates();
    }
    private void stopPrimeSearch() {
        isSearching = false;
        updateButtonStates();
    }


    // restores previous states when screen moves
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("currentNumber", currNum);
        outState.putLong("latestPrime", currPrime);
        outState.putBoolean("isSearching", isSearching);
        outState.putBoolean("pacifierState", pacifierSwitchCheck.isChecked());
    }

    //stops search when task is closed.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPrimeSearch();
    }

    @Override
    public void onBackPressed() {
        if (isSearching) {
            new AlertDialog.Builder(this)
                    .setTitle("Leave?")
                    .setMessage("Are you sure you want to stop searching?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        stopPrimeSearch();
                        super.onBackPressed();
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }

}
