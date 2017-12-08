package com.project.shopcart.uitls;

import android.widget.AbsListView;


import com.project.shopcart.adapter.baseadapter.QuickAdapter;

import java.util.List;


/**
 * Created by Administrator on 2017/3/30.
 */

public class ListBindUtil<T, LV extends AbsListView, BA extends QuickAdapter> {
    public void bindList(LV lv, List<T> list, BA adapter, int page, List<T> dataList) {
        if (page == 1 && list.size() >= 0) {
            list.clear();
            adapter.clear();
        }
        if (adapter != null && dataList != null && dataList.size() > 0) {
            list.addAll(dataList);
            adapter.addAll(dataList);
        }
    }
}
