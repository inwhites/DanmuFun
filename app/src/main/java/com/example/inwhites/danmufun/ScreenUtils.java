package com.example.inwhites.danmufun;

import android.content.Context;

/**
 * Alias: inwhites
 * <p>
 * Date: 2019/1/6
 * <p>
 * Description:
 */
public class ScreenUtils {


    public static int dip2Px(float dip) {
        return DanmuApplication.context == null ? 0 : (int) (dip * DanmuApplication.context.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static int sp2Px(float sp) {
        return DanmuApplication.context == null ? 0 : (int) (sp * DanmuApplication.context.getResources().getDisplayMetrics().scaledDensity + 0.5F);
    }

}
