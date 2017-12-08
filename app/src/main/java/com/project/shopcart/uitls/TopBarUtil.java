package com.project.shopcart.uitls;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/24.
 */

public class TopBarUtil {


    /**
     * 占位控件是否显示
     */
    public static void initSpace(Context context, int viewId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            (((Activity) context).findViewById(viewId)).setVisibility(View.GONE);
        }
    }

    /**
     * 占位控件是否显示
     */
    public static void initSpace(View view, int viewId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            if ((view.findViewById(viewId)) != null) {
                (view.findViewById(viewId)).setVisibility(View.GONE);
            }
        } else {
            if ((view.findViewById(viewId)) != null) {
                (view.findViewById(viewId)).setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 初始化标题
     */
    public static void initTitle(Context context, int titleId, String strTitle) {
        if (null != ((Activity) context).findViewById(titleId)) {
            ((Activity) context).findViewById(titleId).setVisibility(View.VISIBLE);
            ((TextView) (((Activity) context).findViewById(titleId))).setText(strTitle);
        }
    }

    /**
     * 初始化返回按钮
     */
    public static void initBtnBack(final Context context, int btnBackId) {
        if (null != ((Activity) context).findViewById(btnBackId)) {
            ((Activity) context).findViewById(btnBackId).setVisibility(
                    View.VISIBLE);
            ((Activity) context).findViewById(btnBackId).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            try {
                                if (((Activity) context).getCurrentFocus() != null)
                                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                            } catch (Exception e) {
                            }
                            ((Activity) context).finish();
                        }
                    });
        }
    }

    /**
     * 初始化返回按钮
     */
    public static void initBtnBack(final Context context, int btnBackId, View.OnClickListener listener) {
        if (null != ((Activity) context).findViewById(btnBackId)) {
            ((Activity) context).findViewById(btnBackId).setVisibility(
                    View.VISIBLE);
            ((Activity) context).findViewById(btnBackId).setOnClickListener(listener);
        }
    }

    /**
     * 初始化返回按钮
     */
    public static void initBtnBack(final Context context, int btnBackId,
                                   int ResourceId) {
        if (null != ((Activity) context).findViewById(btnBackId)) {
            ((Activity) context).findViewById(btnBackId).setVisibility(
                    View.VISIBLE);
            ((Activity) context).findViewById(btnBackId)
                    .setBackgroundResource(ResourceId);
            ((Activity) context).findViewById(btnBackId).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            try {
                                if (((Activity) context).getCurrentFocus() != null)
                                    ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                            } catch (Exception e) {

                            }
                            ((Activity) context).finish();
                        }
                    });
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(final Context context, int btnFunId, View.OnClickListener onClickListener) {
        if (null != ((Activity) context).findViewById(btnFunId)) {
            ((Activity) context).findViewById(btnFunId).setVisibility(
                    View.VISIBLE);
            ((Activity) context).findViewById(btnFunId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(final Context context, int btnFunId, String text, View.OnClickListener onClickListener) {
        if (null != ((Activity) context).findViewById(btnFunId)) {
            ((Activity) context).findViewById(btnFunId).setVisibility(View.VISIBLE);
            ((TextView) ((Activity) context).findViewById(btnFunId)).setText(text);
            ((Activity) context).findViewById(btnFunId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(final View view, int btnFunId, String text, View.OnClickListener onClickListener) {
        if (null != view.findViewById(btnFunId)) {
            view.findViewById(btnFunId).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(btnFunId)).setText(text);
            view.findViewById(btnFunId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(final Context context, int btnFunId,
                                  int ResourceId, View.OnClickListener onClickListener) {
        if (null != ((Activity) context).findViewById(btnFunId)) {
            ((Activity) context).findViewById(btnFunId).setVisibility(
                    View.VISIBLE);
            Drawable drawable = context.getResources().getDrawable(ResourceId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            ((TextView) ((Activity) context).findViewById(btnFunId)).setCompoundDrawables(null, null, drawable, null);//画在右边
            if (onClickListener != null) {
                ((Activity) context).findViewById(btnFunId).setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 初始化标题
     */
    public static void initTitle(View view, int titleId, String strTitle) {
        if (null != view.findViewById(titleId)) {
            view.findViewById(titleId).setVisibility(View.VISIBLE);
            ((TextView) view.findViewById(titleId)).setText(strTitle);
        }
    }

    /**
     * 初始化返回按钮
     */
    public static void initBtnBack(View view, int btnBackId, View.OnClickListener onClickListener) {
        if (null != view.findViewById(btnBackId)) {
            view.findViewById(btnBackId).setVisibility(
                    View.VISIBLE);
            view.findViewById(btnBackId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化返回按钮
     */
    public static void initBtnBack(Context context, View view, int btnBackId,
                                   int ResourceId, View.OnClickListener onClickListener) {
        if (null != view.findViewById(btnBackId)) {
            view.findViewById(btnBackId).setVisibility(
                    View.VISIBLE);
            Drawable drawable = context.getResources().getDrawable(ResourceId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            ((TextView) view.findViewById(btnBackId)).setCompoundDrawables(drawable, null, null, null);//画在右边
            view.findViewById(btnBackId)
                    .setBackgroundResource(ResourceId);
            view.findViewById(btnBackId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(View view, int btnFunId, View.OnClickListener onClickListener) {
        if (null != view.findViewById(btnFunId)) {
            view.findViewById(btnFunId).setVisibility(
                    View.VISIBLE);
            view.findViewById(btnFunId).setOnClickListener(onClickListener);
        }
    }

    /**
     * 初始化功能键
     */
    public static void initBtnFun(Context context, View view, int btnFunId,
                                  int ResourceId, View.OnClickListener onClickListener) {
        if (null != view.findViewById(btnFunId)) {
            view.findViewById(btnFunId).setVisibility(
                    View.VISIBLE);
            Drawable drawable = context.getResources().getDrawable(ResourceId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
            ((TextView) view.findViewById(btnFunId)).setCompoundDrawables(null, null, drawable, null);//画在右边
            if (onClickListener != null) {
                view.findViewById(btnFunId).setOnClickListener(onClickListener);
            }
        }
    }

    /**
     * 设置控件显示隐藏
     */
    public static void setVisible(Context context, int viewId, boolean visible) {
        View view = retrieveView(context, viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private static <T extends View> T retrieveView(Context context, int viewId) {
        View view = ((Activity) context).findViewById(viewId);
        return (T) view;
    }

    private static Context mContext;
    /**
     * 当前焦点文本框id
     */
    private static int localId = 0;

    /**
     * 绑定文本框点击事件
     */
    public static void setOnTouchListener(Context context, EditText[] editTexts) {
        mContext = context;
        if (editTexts != null) {
            for (EditText editText : editTexts) {
                if (editText != null) {
                    editText.setOnTouchListener(new textTouchLister());
                }
            }
        }
    }

    /**
     * 文本框监听函数
     *
     * @author wyz
     */
    public static class textTouchLister implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                InputMethodManager imm = (InputMethodManager) mContext
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

                EditText txt = (EditText) view;
                if (view.hasFocus() && localId == txt.getId()) {
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    localId = 0;
                } else {
                    // 显示软键盘
                    localId = txt.getId();
                }
            }
            if (localId == 0) {
                return true;
            }
            return false;
        }
    }
}
