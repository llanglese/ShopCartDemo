package com.project.shopcart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dev.pulltorefresh.PullToRefreshBase;
import com.dev.pulltorefresh.PullToRefreshScrollView;
import com.google.gson.Gson;
import com.project.shopcart.adapter.AdapterUtil;
import com.project.shopcart.adapter.baseadapter.QuickAdapter;
import com.project.shopcart.bean.CartLstBean;
import com.project.shopcart.bean.GoodsLstBean;
import com.project.shopcart.uitls.ArithUtil;
import com.project.shopcart.uitls.GetJsonUtil;
import com.project.shopcart.uitls.JsonObject;
import com.project.shopcart.uitls.ListBindUtil;
import com.project.shopcart.uitls.NonScrollListView;
import com.project.shopcart.uitls.SystemBarTintUtil;
import com.project.shopcart.uitls.TopBarUtil;
import com.project.shopcart.view.AlertStyleDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lv_display)
    NonScrollListView lvDisplay;
    @BindView(R.id.psv_display)
    PullToRefreshScrollView psvDisplay;
    @BindView(R.id.check_quanxuan)
    CheckBox checkQuanxuan;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;


    private String user_id = "";
    private List<CartLstBean> mList = new ArrayList<>();
    private QuickAdapter<CartLstBean> mAdapter;
    private int page = 1;

    Double money = 0.0;
    int num = 0;
    private Map<Integer, Boolean> map = new HashMap<>();

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 100://减  购物车商品数量修改
                    //  httpList();
                    CartLstBean cart = (CartLstBean) msg.obj;
                    httpChangeNum(cart.getId(), cart.getNum());
                    break;
                case 200://加  购物车商品数量修改
                    CartLstBean carta = (CartLstBean) msg.obj;
                    httpChangeNum(carta.getId(), carta.getNum());
                    break;
                case 300:
                    ChangeMoney();
                    break;
                case 400://购物车商品删除
                    final CartLstBean cartd = (CartLstBean) msg.obj;
                    new AlertStyleDialog(MainActivity.this, "", "确认删除该商品?", true, 0, new AlertStyleDialog.OnDialogButtonClickListener() {
                        @Override
                        public void onDialogButtonClick(int requestCode, boolean isPositive) {
                            if (isPositive) {
                                httpDelCart(cartd.getId());
                            }
                        }
                    }).show();

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //沉浸式状态栏修改写到BaseActivity中
        SystemBarTintUtil.setSystemBarTint(this, R.color.transparent);
        SystemBarTintUtil.setSystemBarUiIcon(this);
        ButterKnife.bind(this);
        initview();
        initdate();
        initLinstener();
    }

    private void initview() {
        TopBarUtil.initSpace(this, R.id.v_space);
        TopBarUtil.initTitle(this, R.id.tv_base_title, "购物车");

        psvDisplay.setMode(PullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        AdapterUtil adapterUtil = new AdapterUtil<>();
        mAdapter = adapterUtil.getCartLstAdapter(this, mList, mHandler, map);
        lvDisplay.setAdapter(mAdapter);

    }

    private void initdate() {
        httpList();
    }

    private void initLinstener() {
        psvDisplay.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                page = 1;
                map.clear();
                httpList();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //  page++;
                //  httpList();
            }
        });

        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //toActivity(new Intent(getActivity(), CrowdfundingDetailActivity.class).putExtra(INTENT_TITLE, mList.get(i).getTitle()).putExtra(INTENT_ID, mList.get(i).getId()));
            }
        });
        checkQuanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    for (int i = 0; i < mList.size(); i++) {
                        map.put(i, true);
                    }
                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        map.remove(i);
                    }
                }
                ChangeMoney();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void httpList() {
        if (0 == GetJsonUtil.getResponseCode(JsonObject.json)) {
            String json = GetJsonUtil.getResponseData(JsonObject.json);
            bindListData(JSON.parseArray(json, CartLstBean.class));
            mHandler.sendEmptyMessageDelayed(300, 0);
        } else {
            // showShortToast(GetJsonUtil.getResponseError(resultJson));
        }
        if (psvDisplay != null) {
            psvDisplay.onRefreshComplete();
        }

    }

    private void httpChangeNum(String id, String num) {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId().equals(id)) {
                mList.get(i).setNum(num);
            }
        }
        mHandler.sendEmptyMessageDelayed(300, 0);
    }

    private void httpDelCart(String id) {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getId().equals(id)) {
                mList.remove(i);
                mAdapter.remove(i);
            }
        }
        for (int i = 0; i < mList.size(); i++) {
            map.put(i, false);
        }
        mAdapter.notifyDataSetChanged();
        mHandler.sendEmptyMessageDelayed(300, 0);

    }

    private void bindListData(List<CartLstBean> list) {
        if (list == null || list.size() < 0) {
            return;
        }
        ListBindUtil<CartLstBean, AbsListView, QuickAdapter> listBindUtil = new ListBindUtil<>();
        listBindUtil.bindList(lvDisplay, mList, mAdapter, page, list);
    }

    private void ChangeMoney() {
        money = 0.0;
        num = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (map.get(i) == null ? false : map.get(i)) {
                money = ArithUtil.add(Double.parseDouble(ArithUtil.mul(Double.parseDouble(mList.get(i).getNum()), Double.parseDouble(mList.get(i).getM_price())) + ""), Double.parseDouble(money + ""));
                num++;
            }
        }
        tvSum.setText("¥" + money + "");
        tvNum.setText("(" + num + ")");
    }

    @OnClick(R.id.ll_pay)
    public void onViewClicked() {
        if (mList.size() > 0 && num > 0) {
            if (mList.get(0).getType().equals("1") || mList.get(0).getType().equals("2")) {//众筹商品  和  电商商品类型，跳转支付界面
                List<GoodsLstBean> beanList = new ArrayList<GoodsLstBean>();
                for (int i = 0; i < mList.size(); i++) {
                    if (map.get(i) == null ? false : map.get(i)) {
                        GoodsLstBean dataBean = new GoodsLstBean();
                        dataBean.setNum(mList.get(i).getNum());
                        dataBean.setName(mList.get(i).getGoods_name());
                        beanList.add(dataBean);
                    }
                }
                Toast.makeText(this, new Gson().toJson(beanList), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
