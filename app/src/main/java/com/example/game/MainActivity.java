package com.example.game;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;
import com.example.game.view.ChessView;
import com.example.game.view.ContainerLaout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        synchronized (GlobalConstant.handLock) {
            GlobalConstant.containerLaout = findViewById(R.id.broadMain);
            GlobalConstant.containerLaout.addView(new ChessView(GlobalConstant.containerLaout.getContext(),1), new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
    }
}
