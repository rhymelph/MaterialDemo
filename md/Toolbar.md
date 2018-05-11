## Toolbar

### 1. 先将主题改为无标题
```xml
<resources>
    <!-- app-res-values-styles.xml -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
</resources>
```

### 2. 在我们的布局中添加ToolBar控件
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <!--app-res-layout-activity_main.xml-->
    <android.support.v7.widget.Toolbar
        android:id="@+id/tl"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        />
</LinearLayout>
```

### 3. 实例化ToolBar控件
```java
        tl = (Toolbar) findViewById(R.id.tl);
//        tl.setTitle("ToolBar");//标题
//        tl.setLogo(R.mipmap.ic_launcher);//图标
//        tl.setLogoDescription("图标");//logo介绍
//        tl.setSubtitle("small toolbar");//副标题
//        tl.setSubtitleTextColor(Color.BLUE);//副标题颜色
        setSupportActionBar(tl);//设置到该activity中
        //添加返回按钮
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //添加一个居中的标题
        TextView tv=new TextView(this);
        Toolbar.LayoutParams params=new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(params);
        tl.addView(tv);
    }
```

### 4. 添加返回按钮监听
> 通过重写onOptionsItemSelected()方法

```java
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
```

### 5. 自定义ToolBar样式
1. 添加一个自己的样式
```xml
 <!--app-res-values-styles-->
    <!--ToolBar本身的主题 -->
    <style name="MyToolBarTheme" parent="AppTheme">
        <!--修改标题设置的文字颜色-->
        <item name="android:textColorPrimary">@android:color/holo_blue_dark</item>
        <!--按钮选中的颜色-->
        <item name="colorAccent">@android:color/holo_blue_bright</item>
        <!--按钮正常状态的颜色-->
        <item name="android:colorControlNormal">@android:color/holo_blue_dark</item>
        <!--Button按钮正常的颜色-->
        <item name="android:colorButtonNormal">@android:color/holo_red_light</item>
        <!--输入框文字颜色-->
        <item name="android:editTextColor">@android:color/darker_gray</item>
    </style>
    <!--ToolBar点击下拉的主题 -->
    <style name="MyPopuTheme" parent="ThemeOverlay.AppCompat">
        <!--是否覆盖toolbar-->
        <item name="overlapAnchor">false</item>
        <!--字体颜色-->
        <item name="android:textColor">@android:color/darker_gray</item>
        <!--背景颜色-->
        <item name="android:colorBackground">@android:color/black</item>
    </style>
```
2. 把样式设置到ToolBar中
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <!--app-res-layout-activity_main.xml-->
    <android.support.v7.widget.Toolbar
        android:id="@+id/tl"
        app:popupTheme="@style/MyPopuTheme"
        android:theme="@style/MyToolBarTheme"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        />
</LinearLayout>
```