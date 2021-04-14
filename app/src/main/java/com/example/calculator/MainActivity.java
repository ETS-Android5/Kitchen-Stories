package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView display;

    // on click number
    private boolean isClicked = true;
    private boolean isPlusMinusClicked = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.txtview);


    }

    public void updateText(String addString){

        if(isClicked){

            String oldString = display.getText().toString();

            if(display.getText().toString().equals("0")){
                display.setText(addString);
            }
            else{

                if(isPlusMinusClicked){

                    int number = Integer.parseInt(display.getText().toString());

                    if(number > 0){
                        display.setText("-" + oldString);
                    }
//                    else{
//                        display.setText("-" + oldString);
//                    }

                    isPlusMinusClicked = false;
                }

                oldString = display.getText().toString();

                display.setText(String.format("%s%s", oldString, addString));

                if(display.getText().length() < 7){
                    display.setTextSize(100);
                }
                else{

                    if(display.getText().length() >= 7){
                        display.setTextSize(100/2);
                    }
                    if(display.getText().length() > 10){
                        display.setTextSize(100/3);
                    }
                    if(display.getText().length() > 14){
                        display.setText("OVER SIZE!");
                        display.setTextSize(60);
                    }
                }
            }
        }

    }

    public void equalsBtnOnClick(View view){

        isClicked = false;

        isPlusMinusClicked = false;

        String userExp = display.getText().toString();

        userExp = userExp.replace("÷","/");
        userExp = userExp.replace("x","*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);

    }

    public void plusMinusBtnOnClick(View view){

        isPlusMinusClicked = true;
        updateText("");

    }

    public void percentBtnOnClick(View view){

    }

    public void divideBtnOnClick(View view){
        isClicked = true;
        updateText("÷");

    }

    public void multiplyBtnOnClick(View view){
        isClicked = true;
        updateText("x");

    }

    public void subtractBtnOnClick(View view){
        isClicked = true;
        updateText("-");

    }

    public void addBtnOnClick(View view){
        isClicked = true;
        updateText("+");

    }



    public void pointBtnOnClick(View view){
        updateText(",");
    }

    public void zeroBtnOnClick(View view){
        updateText("0");
    }

    public void oneBtnOnClick(View view){
        updateText("1");
    }

    public void twoBtnOnClick(View view){
        updateText("2");
    }

    public void threeBtnOnClick(View view){
        updateText("3");
    }

    public void fourBtnOnClick(View view){
        updateText("4");
    }

    public void fiveBtnOnClick(View view){
        updateText("5");
    }

    public void sixBtnOnClick(View view){
        updateText("6");
    }

    public void sevenBtnOnClick(View view){
        updateText("7");
    }

    public void eightBtnOnClick(View view){
        updateText("8");
    }

    public void nineBtnOnClick(View view){
        updateText("9");
    }

    public void clearBtnOnClick(View view){
        isClicked = true;
        display.setTextSize(100);
        display.setText("0");
    }


}