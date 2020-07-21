package com.jaybon.lifecycleex02;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private Button btnSubNum;
    private EditText etNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnSubNum = findViewById(R.id.btn_sub_num); // 버튼이름을 같게 해도 충돌안남
        etNum = findViewById(R.id.et_2);
        btnSubNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("number", etNum.getText().toString());
                setResult(10, intent);

                finish();
            }
        });
    }
}