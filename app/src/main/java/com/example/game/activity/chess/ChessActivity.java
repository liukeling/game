package com.example.game.activity.chess;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.R;
import com.example.game.view.chess.ChessView;
import com.example.game.view.chess.contanst.GlobalConstant;

public class ChessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess_main);
        synchronized (GlobalConstant.handLock) {
            if(GlobalConstant.containerLaout == null) {
                GlobalConstant.containerLaout = findViewById(R.id.broadMain);
                GlobalConstant.containerLaout.addView(new ChessView(GlobalConstant.containerLaout.getContext(), 1), new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalConstant.containerLaout = null;
    }
}
