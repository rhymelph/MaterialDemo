## SnackBar

> 跟Toast差不多，只介绍不同点

1. 带按钮
```java
//view 随便一视图，不能为空，他会自动的查找是否有CoordinatorLayout，有就显示该布局的下面，没有就显示屏幕下方
//content 要展示的内容
//Snackbar.LENGTH_LONG 要展示多久
 Snackbar.make(view,content, Snackbar.LENGTH_LONG)
                .setAction("确定",view ->{
                    ToastUtils.showToast(this,content);
                }).show();
```

2. 显示和隐藏监听
```java
        final long[] times = {0};
        Snackbar.make(list,content, Snackbar.LENGTH_LONG)
                .addCallback(new Snackbar.Callback(){

                    @Override
                    public void onShown(Snackbar sb) {
                    //显示的时候调用
                        times[0] =System.currentTimeMillis();
                        super.onShown(sb);
                    }
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                    //关闭的时候调用
                        Log.d("Snack","显示了"+(System.currentTimeMillis()-times[0])+"毫秒");
                        super.onDismissed(transientBottomBar, event);
                    }
                }).show();
```

3. 自定义SnackBar样式

```java
        //按钮点击样式
        ColorStateList csl=getResources().getColorStateList(R.color.nav_menu_color);
        Snackbar snackbar=Snackbar.make(list,content, Snackbar.LENGTH_LONG)
                .setActionTextColor(csl)
                .setAction("确定",view ->{
                    ToastUtils.showToast(this,content);
                });
        View snackView=snackbar.getView();
        TextView snacktv=snackView.findViewById(R.id.snackbar_text);
        Button snackbtn=snackView.findViewById(R.id.snackbar_action);

        snackView.setBackgroundColor(Color.YELLOW);

        snacktv.setTextColor(Color.BLACK);
        snacktv.setGravity(Gravity.CENTER);
        Drawable drawable=getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,(int) snacktv.getTextSize(),(int) snacktv.getTextSize());
        snacktv.setCompoundDrawables(drawable,null,null,null);

        snackbar.show();
```