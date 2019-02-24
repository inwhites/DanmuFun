package com.example.inwhites.danmufun;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

public class DanmuUtil {


    public static SpannableStringBuilder makeDanmu(Context context) {

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("我是一个带自定义表情的弹幕哦～～～～～");
        int resId = Math.random() > 0.5 ? R.drawable.red_cat : R.drawable.black_cat;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        if (bitmap != null) {
            Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, ScreenUtils.sp2Px(20), ScreenUtils.sp2Px(20), true);
            CustomDanmuSpan span = new CustomDanmuSpan(context, scaleBitmap);
            spannableStringBuilder.setSpan(span, spannableStringBuilder.length() - 1, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }



        return spannableStringBuilder;
    }


}
