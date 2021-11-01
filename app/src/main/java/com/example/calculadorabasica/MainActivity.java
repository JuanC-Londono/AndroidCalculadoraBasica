package com.example.calculadorabasica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView screenC = ((TextView) findViewById(R.id.calculatorScreen));

        ArrayList<View> tocables;
        tocables = ((LinearLayout) findViewById(R.id.buttonContainerNumbers)).getTouchables();

        for (int i=0; i < tocables.size(); i++) {
            Button b = (Button) tocables.get(i);
            //Log.i("button", b.getText().toString());
            int finalI = i;



            if (b.getText().toString().equals("C")) {
                tocables.get(i).setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v){
                        screenC.setText("");
                    }
                });

            } else if (b.getText().toString().equals("CC")){
                tocables.get(i).setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v){
                        Button b = (Button) tocables.get(finalI);
                        String str = screenC.getText().toString();
                        if(str.length()>=1){
                            screenC.setText(str.substring(0, str.length() - 1));
                        }

                    }
                });
            } else if (b.getText().toString().equals("=")) {
                tocables.get(i).setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v){
                        Button b = (Button) tocables.get(finalI);
                        String result = Operacion(screenC.getText().toString());
                        screenC.setText(result);
                    }
                });
            } else if ((!b.getText().toString().equals("C"))  && !(b.getText().toString().equals("="))){
                tocables.get(i).setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v){
                        Button b = (Button) tocables.get(finalI);
                        screenC.setText(screenC.getText().toString()+ b.getText().toString());
                    }
                });
            }


        }


    }

    private String Operacion(String screenText) {
        String[] parts = screenText.split("\\+|\\-|\\/|X");
        if(parts.length==1){
            return screenText;
        }
        Float a, b, result;
        String res="No if";
        a = Float.valueOf(parts[0].toString());
        b = Float.valueOf(parts[1].toString());

        Log.i("operacion", "a: "+a.toString()+", b: "+b.toString());

        
        if(screenText.contains("+")){
            result = a+b;
            res = result.toString();
        } else if (screenText.contains("-")){
            result = a-b;
            res = result.toString();
        } else if (screenText.contains("X")){
            result = a*b;
            res = result.toString();
        } else if (screenText.contains("/")){
            if(b!=0){
                result = a/b;
                res = result.toString();
            } else {
                res = "Und";
            }

        }

        return res;

    }
}