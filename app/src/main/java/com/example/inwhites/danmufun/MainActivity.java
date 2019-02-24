package com.example.inwhites.danmufun;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int ADD_DANMU = 0x23;

    private RelativeLayout mParentGroup;

    private DanmuController mDanmuController;


    private SeekBar mAlphaSeekBar;//透明度控制
    private SeekBar mSizeSeekBar; //弹幕大小控制


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mParentGroup = findViewById(R.id.parent_group);

        mDanmuController = new DanmuController(getBaseContext());
        mParentGroup.addView(mDanmuController.getDanmuView());


        mSizeSeekBar = findViewById(R.id.size_progress);
        mAlphaSeekBar = findViewById(R.id.alpha_progress);

        mSizeSeekBar.setProgress(0);
        mAlphaSeekBar.setProgress(0);


        mSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDanmuController.setDanmuSize(i+12);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mAlphaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mDanmuController.setDanmuAlpha((float) (i * 1.0 / 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Message message = Message.obtain();
        message.what = ADD_DANMU;
        handler.sendMessageDelayed(message, 300);


    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == ADD_DANMU) {
                mDanmuController.addDanmaku(DanmuUtil.makeDanmu(getBaseContext()));

                Message message = Message.obtain();
                message.what = ADD_DANMU;
                handler.sendMessageDelayed(message, 500);

            }
        }
    };


    @Override
    public void onClick(View view) {

    }
}
