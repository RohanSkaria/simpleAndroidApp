package edu.northeastern.numad25sp_rohanskaria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView displayText;
    private StringBuilder input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        displayText = findViewById(R.id.calcDisplay);
        input = new StringBuilder();

        for (int i = 0; i<=9; i++) {
            int buttonId = getResources().getIdentifier("button" +i,"id",getPackageName());
            Button button = findViewById(buttonId);
            button.setOnClickListener(this);
        }

        findViewById(R.id.buttonPlus).setOnClickListener(this);
        findViewById(R.id.buttonMinus).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonDel).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "del":
                    if(input.length()>0) {
                        input.deleteCharAt(input.length()-1);
                        updateDisplay();
                    }
                    break;
                case "=":
                    evaluateInput();
                    break;
                default:
                    input.append(buttonText);
                    updateDisplay();
                    break;
            }
        }
    }
    private void evaluateInput() {
        String in = input.toString();

        try{
            String[] parts;
            if(in.contains("+")) {
                parts = in.split("\\+");
                int res = Integer.parseInt(parts[0].trim()) + Integer.parseInt(parts[1].trim());
                input = new StringBuilder(String.valueOf(res));
            } else if (in.contains("-")) {
                parts = in.split("-");
                int res = Integer.parseInt(parts[0].trim()) - Integer.parseInt(parts[1].trim());
                input = new StringBuilder(String.valueOf(res));
            }
            updateDisplay();
        } catch (Exception e) {
            input = new StringBuilder("Err");
            updateDisplay();
            input.setLength(0);
        }
    }
    private void updateDisplay() {
        if(input.length()==0) {
            displayText.setText("CALC");
        } else {
            displayText.setText(input.toString());
        }
    }
}
