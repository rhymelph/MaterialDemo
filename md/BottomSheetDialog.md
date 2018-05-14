## BottomSheetDialog

1. 介绍
> 从底部打开的对话框，可支持上划到全屏模式，下划取消对话框

2. 基本使用
```java
        BottomSheetDialog dialog=new BottomSheetDialog(context);
        dialog.setContentView(view);
        //这里有三个方法设置view
        //setContentView(View view);
        //setContentView(View view, ViewGroup.LayoutParams params)
        //setContentView(@LayoutRes int layoutResId)
        dialog.show();
        dialog.setCancelable(true);//是否可取消
        dialog.setCanceledOnTouchOutside(true);//是否可触摸外部取消
```

3. 注意事项
> dialog里的contentView如果是listview会导致下划不了，只能上划，
> 解决办法为自定义listview实现NestedScrollingChild2方法
> 或者使用RecycleView和NestedScrollView控件

4. 分析控件
> BottomSheetDialog继承自AppCompatDialog
> setContentView方法是将view添加到id为design_bottom_sheet下的FrameLayout控件
> BottomSheetDialog布局
```xml
  <FrameLayout id="container">
    <android.support.design.widget.CoordinatorLayout id="coordinator">
      <View id="touch_outside"/>
      <FrameLayout id="design_bottom_sheet"
      app:layout_behavior="@string/bottom_sheet_behavior"/>
    </android.support.design.widget.CoordinatorLayout>
  </FrameLayout>
```
> 之所以能实现上划动作是因为app:layout_behavior
