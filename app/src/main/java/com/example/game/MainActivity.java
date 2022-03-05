package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.activity.chess.ChessActivity;
import com.example.game.activity.test.TestActivity;
import com.example.game.view.chess.ChessView;
import com.example.game.view.chess.contanst.GlobalConstant;

public class MainActivity extends AppCompatActivity {
    Button begin,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        begin = findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ChessActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
        test = findViewById(R.id.btn_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TestActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
    }
}
