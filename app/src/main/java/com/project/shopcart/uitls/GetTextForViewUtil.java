package com.project.shopcart.uitls;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/4.
 */

public class GetTextForViewUtil {
    public static String getText(View v) {
        String text = "";
        if (v instanceof EditText) {
            text = ((EditText) v).getText().toString().trim();
        } else if (v instanceof TextView) {
            text = ((TextView) v).getText().toString().trim();
        } else if (v instanceof Button) {
            text = ((Button) v).getText().toString().trim();
        }
        return text;
    }

    public static String getTagText(View v) {
        return v.getTag() != null ? v.getTag() + "" : "";
    }

    public static boolean getTagBoolean(View v) {
        return v.getTag() != null ? (boolean) v.getTag() : false;
    }
}
