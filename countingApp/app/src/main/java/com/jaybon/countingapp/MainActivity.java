package com.jaybon.countingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";

    private EditText ptResult;
    private Button btn[] = new Button[16];
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initListener();



//        btnAdd.setOnClickListener((View view)->{
//
//            Log.d(TAG, "onCreate: btnAdd:"+view.getId());
//
//            count++;
//            tvCount.setText(count+"");
//        });
//
//        btnMinus.setOnClickListener((View view)->{
//            if(count > 0){
//                count--;
//                tvCount.setText(count+"");
//            }
//        });
    }

    private void init(){
        btn[0] = findViewById(R.id.num0);
        btn[1] = findViewById(R.id.num1);
        btn[2] = findViewById(R.id.num2);
        btn[3] = findViewById(R.id.num3);
        btn[4] = findViewById(R.id.num4);
        btn[5] = findViewById(R.id.num5);
        btn[6] = findViewById(R.id.num6);
        btn[7] = findViewById(R.id.num7);
        btn[8] = findViewById(R.id.num8);
        btn[9] = findViewById(R.id.num9);
        btn[10] = findViewById(R.id.btn_add);
        btn[11] = findViewById(R.id.btn_minus);
        btn[12] = findViewById(R.id.btn_multi);
        btn[13] = findViewById(R.id.btn_devide);
        btn[14] = findViewById(R.id.btn_clear);
        btn[15] = findViewById(R.id.btn_cal);
        ptResult = findViewById(R.id.pt_result);
    }

    private void initListener(){

        for (i=0; i<14; i++){
            btn[i].setOnClickListener((View view)->{
                Button btn = (Button)view;
                ptResult.append(btn.getText().toString());
            });
        }

        btn[14].setOnClickListener((View view)->{
            ptResult.setText("");
        });

        btn[15].setOnClickListener((View view)->{
            String result = ptResult.getText().toString();
            try {
                ptResult.setText(eval.cal(result));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        });

    }

}