package com.example.inwhites.danmufun;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.view.View;

import java.io.InputStream;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;


public class DanmuController {
    private DanmakuView mDanmukuView;
    private DanmakuContext mDanmuContext;
    private BaseDanmakuParser mParser;
    private Context mContext;
    private DrawHandler.Callback mCallback;


    public DanmuController(Context context) {
        this.mContext = context;


    }

    /**
     * @return
     */
    public View getDanmuView() {
        if (mDanmukuView == null) {
            mDanmukuView = new DanmakuView(mContext);
            initDanmu();
        }
        return mDanmukuView;
    }


    public void initDanmu() {
        mDanmuContext = DanmakuContext.create();
        mCallback = new DanmuCallBack();
        mDanmuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_SHADOW, 2)
                .setDuplicateMergingEnabled(false)
                .setSpecialDanmakuVisibility(false)
                .setDanmakuMargin(10)
                .setCacheStuffer(new SpannedCacheStuffer(), null);

        mParser = createParser(null);
        mDanmukuView.setCallback(mCallback);
        mDanmukuView.enableDanmakuDrawingCache(true);
        mDanmukuView.prepare(mParser, mDanmuContext);


    }


    public void addDanmaku(SpannableStringBuilder string) {


        BaseDanmaku danmaku = mDanmuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null) {
            return;
        }
        danmaku.textSize = ScreenUtils.sp2Px(16);
        danmaku.isLive = true;
        danmaku.setTime(mDanmukuView.getCurrentTime() + 1200);
        danmaku.padding = ScreenUtils.dip2Px(4);
        danmaku.textShadowColor = Color.BLACK;
        danmaku.text = string;
        danmaku.textColor = Color.WHITE;
        mDanmukuView.addDanmaku(danmaku);
    }


    public void startDanmu() {
        if (mDanmukuView != null) {
            mDanmukuView.start(0L);
        }
    }

    public void stopDanmu() {

        mDanmukuView.clearDanmakusOnScreen();
        mDanmukuView.removeAllLiveDanmakus();
        mDanmukuView.hideAndPauseDrawTask();
    }

    public static BaseDanmakuParser createParser(InputStream stream) {

        return new BaseDanmakuParser() {

            @Override
            protected Danmakus parse() {
                return new Danmakus();
            }
        };
    }


    private class DanmuCallBack implements DrawHandler.Callback {

        @Override
        public void prepared() {
            mDanmukuView.start();

        }

        @Override
        public void updateTimer(DanmakuTimer timer) {

        }

        @Override
        public void danmakuShown(BaseDanmaku danmaku) {

        }

        @Override
        public void drawingFinished() {

        }

    }


    /**
     * 弹幕透明度
     *
     * @param p
     */

    public void setDanmuAlpha(float p) {

        CustomEmojDanmuSpan.alpha = (int) (p * 255);
        if (mDanmuContext != null) {
            mDanmuContext.setDanmakuTransparency(p);
        }

    }


    public void setDanmuSize(float p) {

        if (mDanmuContext != null) {
            mDanmuContext.setScaleTextSize((float) (1.0 * p / 16));

        }
    }

}
