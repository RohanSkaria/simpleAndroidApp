package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
        if(isSearching) {
            return;
        }

        isSearching = true;
        currNum = 3;

        searchThread = new Thread(()-> {
           while (isSearching) {
               if (isPrime(currNum)) {
                   currPrime = currNum;
                   mainHandler.post(this::updateUI);
               }
               currNum +=2;
               mainHandler.post(this::updateUI);
           }
        });

        searchThread.start();
        updateButtonStates();
    }

    private void stopPrimeSearch() {
        if(!isSearching) {
            return;
        }

        isSearching = false;
        if (searchThread != null) {
            try {
                searchThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        updateButtonStates();
    }

}
