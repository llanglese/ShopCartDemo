package com.project.shopcart.uitls;

import android.util.Log;

import static java.lang.Double.parseDouble;

/**
 * Created by Administrator on 2017/3/28.
 */

public class NumUtil {
    public static int getInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            Log.e("NumUtils", "字符串转数字失败!!!");
        }
        return 0;
    }

    public static double getDouble(String num) {
        try {
            Log.e("getDouble", ">>>" + parseDouble(num));
            return parseDouble(num);
        } catch (Exception e) {
            Log.e("NumUtils", "字符串转数字失败!!!");
        }
        return 0;
    }

    public static float getFloat(String num) {
        try {
            Log.e("getDouble", ">>>" + parseDouble(num));
            return Float.parseFloat(num);
        } catch (Exception e) {
            Log.e("NumUtils", "字符串转数字失败!!!");
        }
        return 0;
    }
}
