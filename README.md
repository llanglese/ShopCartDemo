# ShopCartDemo

ListView中CheckBox购物车界面 如何避免复用

如何避免这两种控件的复用，1，把Checkbox加入到Map集合中，通过Map集合对控件进行操作，2，把每个条目布局中的商品数量加入到Bean类中。
checkbox加入到map中有何好处，首先，你购物车不可能是只是在adapter中记录选中消息，你activity中可能会存在全选操作，那么，你在Activity中如何快速修改全部的选中状态，那么就直接循环一下map集合，并刷新adapter,就可以完成。

条目布局中的数量加入到Bean类，传递到下一个订单支付界面，可以直接通过Bean类传递一个序列化类，就不会做太多的循环查询数据操作了。

1.给CheckBox创建一个Map集合，把商品数量放入Bean类中

   //创建Checkbox集合
    private Map<Integer, Boolean> map = new HashMap<>();
    
2.创建子线程，接收商品数量改变等操作
//创建子线程，根据适配器中加减点击按钮传递给主界面
 Handler mHandler = new Handler() {
        public void handleMessage( Message msg) {

            switch (msg.what) {
                case 100://减  购物车商品数量修改
                    //  httpList();
                    CartLstBean cart = (CartLstBean) msg.obj;
                    httpChangeNum(cart.getGoodid(), cart.getType(), cart.getShop_id(), cart.getId(), cart.getNum());
                    break;
                case 200://加  购物车商品数量修改
                    CartLstBean carta = (CartLstBean) msg.obj;
                    httpChangeNum(carta.getGoodid(), carta.getType(), carta.getShop_id(), carta.getId(), carta.getNum());
                    break;
                case 300:
                    ChangeMoney();
                    break;
                case 400://购物车商品删除
                    final CartLstBean cartd = (CartLstBean) msg.obj;
                    new AlertStyleDialog(getActivity(), "", "确认删除该商品?", true, 0, new AlertStyleDialog.OnDialogButtonClickListener() {
                        @Override
                        public void onDialogButtonClick(int requestCode, boolean isPositive) {
                            if (isPositive) {
                                httpDelCart(cartd.getGoodid(), cartd.getType(), cartd.getShop_id(), cartd.getId());
                            }
                        }
                    }).show();

                    break;
            }
        }
    };
   //初始化适配器，根据自己创建适配器进行相应修改
        AdapterUtil adapterUtil = new AdapterUtil<>();
        mAdapter = adapterUtil.getCartLstAdapter(getActivity(), mList, mHandler, map);
        lvDisplay.setAdapter(mAdapter);

3.接下来就是适配器中的相应操作，
 /**
     * 商品评论列表
     */
    public QuickAdapter<T> getCartLstAdapter(final Activity activity, List<T> list, final Handler handler, final Map<Integer, Boolean> map) {
        return new QuickAdapter<T>(activity, R.layout.item_cartlst, list) {
            @Override
            protected void convert(final BaseAdapterHelper helper, T item) {
                final CartLstBean o = (CartLstBean) item;
               //删除商品按钮
                ImageView del = (ImageView) helper.getView().findViewById(R.id.cartlst_del);
                del.setTag(helper.getPosition());//设置标签
                //数量
                final TextView tvHasNum = (TextView) helper.getView().findViewById(R.id.tv_has_num);  
                //减
                final TextView tvSubtract = (TextView) helper.getView().findViewById(R.id.tv_subtract);
                tvSubtract.setTag(helper.getPosition());//设置标签
                //加
                final TextView tvAdd = (TextView) helper.getView().findViewById(R.id.tv_add);
                tvAdd.setTag(helper.getPosition());
               //根据网络请求获取到商品数量，判断减号按钮是否显示亮色
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
                 //Checkbox按钮
                CheckBox choose = (CheckBox) helper.getView().findViewById(R.id.check_quanxuan);
                choose.setTag(helper.getPosition());//设置标签

                //根据传递进来的map集合判断当前position的item是否为选中
                boolean isCheck = map.get(helper.getPosition()) == null ? false : map.get(helper.getPosition());
                choose.setChecked(isCheck);
                //接下来就是Checkbox的选中事件了
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         //根据之前设置的标签，来获取到点击事件选中的是第几个item
                        int pos = (int) v.getTag();
                        boolean isCheck;
                        isCheck = map.get(pos) == null ? false : map.get(pos);//获取当前item的选中状态
                        if (!isCheck) {//如果是未选中，则在map集合中加入当前position中的item为选中
                            map.put(pos, true);
                        } else {//如果是已选中，则去除map集合中的当前位置选中状态
                            map.remove(pos);
                        }
                      //像view界面传递消息，修改金额已经刷新适配器
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
                       //设置金额传入bean类中
                        o.setNum(GetTextForViewUtil.getText(tvHasNum));

                        msg.obj = o;
                       //点击一次按钮，发送一次消息给view，并刷新金额
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



