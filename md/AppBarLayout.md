## AppBarLayout

1. 介绍
> 实际上是一个垂直的LinearLayout

2.与CoordinatorLayout联动
> 布局详情

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent">
    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.v7.widget.Toolbar
            android:id="@+id/apl_tb"
            android:minHeight="20dp"
            android:theme="@style/Base.ThemeOverlay.AppCompat.Dark"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:layout_scrollFlags="snap|scroll"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize" />
    </android.support.design.widget.AppBarLayout>
    <android.support.v7.widget.RecyclerView
        android:id="@+id/apl_rv"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
    </android.support.v7.widget.RecyclerView>
</android.support.design.widget.CoordinatorLayout>
```
> app:layout_scrollFlags属性介绍

| 属性 | 介绍 |
| - | :-: |
| scroll | 随着滚动而滚动 |
| snap | 需要与**scroll**属性组合，没完全隐藏和显示，自动隐藏显示 |
| enterAlways | 需要与**scroll**属性组合，如果隐藏向下滑动就显示 |
| enterAlwaysCollapsed | 需要与**scroll**和**enterAlways**属性组合，向下滑动先显示最小高度，到顶部再显示正常高度 |
| exitUntilCollapsed | 需要与**scroll**属性组合，向上滑动保留最小高度 |
