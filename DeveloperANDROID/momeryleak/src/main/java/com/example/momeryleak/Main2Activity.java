package com.example.momeryleak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
  SingletonManager singletonManager;
  Button btnGoOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnGoOne=(Button)findViewById(R.id.button);
        btnGoOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                finish();
            }
        });
        singletonManager=SingletonManager.getInstance(Main2Activity.this);
    }
}
