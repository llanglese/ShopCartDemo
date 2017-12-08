package com.project.shopcart.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.project.shopcart.R;
import com.project.shopcart.adapter.baseadapter.BaseAdapterHelper;
import com.project.shopcart.adapter.baseadapter.QuickAdapter;
import com.project.shopcart.bean.CartLstBean;
import com.project.shopcart.uitls.GetTextForViewUtil;
import com.project.shopcart.uitls.NumUtil;
import com.project.shopcart.uitls.glide.GlideUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Liby on 2017/10/14.
 */

public class AdapterUtil<T> {

    /**
     * 商品列表
     */
    public QuickAdapter<T> getCartLstAdapter(final Activity activity, List<T> list, final Handler handler, final Map<Integer, Boolean> map) {
        return new QuickAdapter<T>(activity, R.layout.item_cartlst, list) {
            @Override
            protected void convert(final BaseAdapterHelper helper, T item) {
                final CartLstBean o = (CartLstBean) item;
                helper.setText(R.id.cartlst_name, o.getGoods_name());
                helper.setText(R.id.cartlst_money, "¥" + o.getM_price());
                helper.setText(R.id.tv_has_num, o.getNum());
                if (o.getGuige().equals("")) {
                    helper.setText(R.id.cartlst_guige, "");
                } else {
                    helper.setText(R.id.cartlst_guige, "规格" + o.getSpecname());
                }

                ImageView img = (ImageView) helper.getView().findViewById(R.id.cartlst_img);
                GlideUtil.loadImageView(activity, R.mipmap.ic_launcher, o.getImages(), img);


                ImageView del = (ImageView) helper.getView().findViewById(R.id.cartlst_del);
                del.setTag(helper.getPosition());
                final TextView tvHasNum = (TextView) helper.getView().findViewById(R.id.tv_has_num);
                final TextView tvSubtract = (TextView) helper.getView().findViewById(R.id.tv_subtract);
                tvSubtract.setTag(helper.getPosition());
                final TextView tvAdd = (TextView) helper.getView().findViewById(R.id.tv_add);
                tvAdd.setTag(helper.getPosition());
                if (NumUtil.getInt(GetTextForViewUtil.getText(tvHasNum)) <= 1) {
                    tvHasNum.setText("1");
                    tvHasNum.setEnabled(false);
                    Drawable drawable = activity.getResources().getDrawable(R.mipmap.btn_subtract_normal);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    tvSubtract.setCompoundDrawables(null, null, drawable, null);//画在右边
                    tvSubtract.setPadding(0, 0, 14, 0);
                } else {
                    Drawable drawable = activity.getResources().getDrawable(R.mipmap.btn_subtract_pressed);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                    tvSubtract.setPadding(20, 0, 0, 0);
                    tvSubtract.setCompoundDrawables(drawable, null, null, null);//画在左边

                }
                CheckBox choose = (CheckBox) helper.getView().findViewById(R.id.check_quanxuan);
                choose.setTag(helper.getPosition());
                boolean isCheck = map.get(helper.getPosition()) == null ? false : map.get(helper.getPosition());
                choose.setChecked(isCheck);
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = (int) v.getTag();
                        boolean isCheck;
                        isCheck = map.get(pos) == null ? false : map.get(pos);//未缴费
                        if (!isCheck) {
                            map.put(pos, true);
                        } else {
                            map.remove(pos);
                        }
                        handler.sendEmptyMessageDelayed(300, 0);
                        notifyDataSetChanged();

                    }
                });
                tvSubtract.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Message msg = new Message();
                        if (Integer.parseInt(v.getTag() + "") == helper.getPosition()) {
                            tvHasNum.setText((NumUtil.getInt(GetTextForViewUtil.getText(tvHasNum)) - 1) + "");
                            if (NumUtil.getInt(GetTextForViewUtil.getText(tvHasNum)) < 1) {
                                msg.what = 400;
                                tvHasNum.setText("1");
                                tvHasNum.setEnabled(false);
                                Drawable drawable = activity.getResources().getDrawable(R.mipmap.btn_subtract_normal);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                                tvSubtract.setCompoundDrawables(null, null, drawable, null);//画在右边
                                tvSubtract.setPadding(0, 0, 14, 0);
                            } else {
                                Drawable drawable = activity.getResources().getDrawable(R.mipmap.btn_add_pressed);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                                tvAdd.setCompoundDrawables(drawable, null, null, null);//画在左边
                                msg.what = 100;
                            }
                        }
                        o.setNum(GetTextForViewUtil.getText(tvHasNum));

                        msg.obj = o;
                        handler.sendMessage(msg);
                    }
                });

                tvAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tvHasNum.setText((NumUtil.getInt(GetTextForViewUtil.getText(tvHasNum)) + 1) + "");

                        if (NumUtil.getInt(GetTextForViewUtil.getText(tvHasNum)) > 1) {
                            tvHasNum.setEnabled(true);
                            Drawable drawable = activity.getResources().getDrawable(R.mipmap.btn_subtract_pressed);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                            tvSubtract.setCompoundDrawables(null, null, drawable, null);//画在右边
                            tvSubtract.setPadding(0, 0, 14, 0);
                        }
                        o.setNum(GetTextForViewUtil.getText(tvHasNum));
                        Message msg = new Message();
                        msg.what = 200;
                        msg.obj = o;
                        handler.sendMessage(msg);

                    }
                });
                del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Integer.parseInt(view.getTag() + "") == helper.getPosition()) {
                            Message msg = new Message();
                            msg.what = 400;
                            msg.obj = o;
                            handler.sendMessage(msg);
                        }
                    }
                });
            }
        };
    }
}
