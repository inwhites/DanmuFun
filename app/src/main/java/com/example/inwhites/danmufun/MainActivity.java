package com.example.inwhites.danmufun;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RelativeLayout mParentGroup;


    private Button mStartBt;
    private Button mAddBt;
    private Button mAlphaBt;
    private DanmuProvider danmuProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mParentGroup = findViewById(R.id.parent_group);
        mStartBt = findViewById(R.id.start_bt);
        mAddBt = findViewById(R.id.add_bt);
        mAlphaBt = findViewById(R.id.alpha_bt);

        danmuProvider = new DanmuProvider(getBaseContext());
        mParentGroup.addView(danmuProvider.getDanmuView());


        mStartBt.setOnClickListener(this);
        mAddBt.setOnClickListener(this);
        mAlphaBt.setOnClickListener(this);


    }


    private final int ADD_DANMU = 0x23;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == ADD_DANMU) {
                danmuProvider.addDanmaku(DanmuUtil.makeDanmu(getBaseContext()));

                Message message = Message.obtain();
                message.what = ADD_DANMU;
                handler.sendMessageDelayed(message,300);

            }
        }
    };


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_bt:
                Message message = Message.obtain();
                message.what = ADD_DANMU;
                handler.sendMessageDelayed(message,300);

                break;
            case R.id.start_bt:


                danmuProvider.startDanmu();
                break;


            case R.id.alpha_bt:
                danmuProvider.setDanmuAlpha((float) Math.random());

                break;
        }


    }
}
