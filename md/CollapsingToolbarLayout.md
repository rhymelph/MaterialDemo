## CollapsingToolbarLayout

1. 介绍
> 该控件作用于toolbar可实现滑动渐变隐藏标题栏中的图片

2. 使用（直接上代码）
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <!--AppBarLayout避免覆盖toolbar-->
    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="250dp">
        <!--CoordinatorLayout加强版Framentlayout
        scroll随着列表的滚动而滚动
        exitUntilCollapsed随着滚动完成之后保留在上面不划出屏幕-->
        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/coll_tb"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            app:contentScrim="?attr/colorPrimary"
            app:collapsedTitleGravity="bottom"
            app:expandedTitleGravity="bottom"
            app:titleEnabled="true"
            app:layout_collapseParallaxMultiplier="1"
            app:collapsedTitleTextAppearance="@style/TextAppearance.Design.Tab"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">
            <!--parallax表示摺疊過程中錯位-->
            <ImageView
                android:id="@+id/img_tool"
                android:background="@drawable/bg"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:layout_collapseMode="parallax"/>
            <!--pin表示摺疊過程中位置保存不變-->
            <android.support.v7.widget.Toolbar
                android:id="@+id/tool_con"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"/>
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>
    <!--内容部分NestedScrollView他和AppBarLayout同一等级可以是RecyclerView
    NestedScrollView相当于ScrollView,
    RecyclerView和NestedScrollView他们都实现了接口NestedScrollingChild-->
    <android.support.v7.widget.RecyclerView
        android:id="@+id/ctl_rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />
    <!--layout_anchor设置描点-->
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        app:useCompatPadding="true"
        app:fabSize="normal"
        app:backgroundTint="@android:color/white"
        app:rippleColor="@android:color/holo_red_light"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_anchorGravity="end|bottom"
        android:layout_margin="20dp"
        app:layout_anchor="@id/appbar"
        android:src="@drawable/favorite_selector"
        app:elevation="10dp"/>
</android.support.design.widget.CoordinatorLayout>

```

3. CollapsingToolbarLayout控件属性介绍

| 属性 | 介绍 |
| - | :-: |
| collapsedTitleGravity | 可选top和bottom指定折叠状态标题文字位置 | 
| collapsedTitleTextAppearance | 指定折叠状态标题文字样式 | 
| expandedTitleGravity | 指定展开状态标题文字位置 | 
| expandedTitleMargin | 指定展开状态标题文字的外边距 | 
| expandedTitleTextAppearance | 指定展开状态标题文字样式 | 
| contentScrim | 完全滚屏幕后的toolbar颜色 | 
| titleEnabled | 是否显示标题文字 | 
| layout_collapseParallaxMultiplier | 视差系数0.0-1.0 | 

4. CollapsingToolbarLayout中的子控件属性介绍

| 属性 | 介绍 |
| - | :-: |
| layout_collapseMode | pin 固定模式，parallax 视差模式 | 
