## NavigationView

### 1.添加圆形ImageView
 > 添加依赖
```java
implementation 'de.hdodenhof:circleimageview:2.2.0'
```

### 2.准备headerlayout和menu
```xml
<?xml version="1.0" encoding="utf-8"?>
<!--header_nav.xml-->
<android.support.constraint.ConstraintLayout
    android:background="@android:color/darker_gray"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="130dp">
    <de.hdodenhof.circleimageview.CircleImageView
        android:id="@+id/header_icon"
        android:layout_width="70dp"
        android:layout_height="70dp"
        android:src="@drawable/bg"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</android.support.constraint.ConstraintLayout>
```

```xml
<?xml version="1.0" encoding="utf-8"?>
<!--menu_nav.xml-->
<!--icon图标和title标题自己准备-->
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <group android:checkableBehavior="single">
        <item
            android:id="@+id/userInfo"
            android:checked="true"
            android:icon="@drawable/ic_person_black"
            android:title="@string/userInfo" />
        <item
            android:id="@+id/design"
            android:icon="@drawable/ic_color_lens_black"
            android:title="@string/color_theme" />
        <item android:title="item">
            <menu>
                <item
                    android:id="@+id/item_1"
                    android:icon="@drawable/ic_chevron_right_black"
                    android:title="item_1" />
                <item
                    android:id="@+id/item_2"
                    android:icon="@drawable/ic_chevron_right_black"
                    android:title="item_2" />
            </menu>
        </item>
    </group>
</menu>
```

### 3.使用控件NavigationView
```xml
<?xml version="1.0" encoding="utf-8"?>
<!--navigationview.xml-->
<android.support.design.widget.NavigationView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/nav"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:elevation="10dp"
    app:itemTextColor="@android:color/black"
    app:itemIconTint="@android:color/black"
    app:itemBackground="@android:color/darker_gray"
    app:itemTextAppearance="?android:textAppearanceMedium"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    app:headerLayout="@layout/header_nav"
    app:menu="@menu/menu_nav">
    <!--elevation阴影 -->
    <!--itemTextColor 文字颜色 -->
    <!--itemBackground 图标颜色 -->
    <!--itemBackground 背景颜色 -->
    <!--itemTextAppearance 字体外貌 -->
    <!--其中headerLayout和menu为上面对应的xml -->
</android.support.design.widget.NavigationView>
```

### 4. 与DrawerLayout联动
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tl"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize" />
        <android.support.v4.widget.DrawerLayout
            android:id="@+id/dl"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent" />
            <!--这里要注意navigation一定要在最顶部，否者点击事件响应不了-->
            <include android:layout_gravity="start"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                layout="@layout/navigationview"/>
        </android.support.v4.widget.DrawerLayout>
</LinearLayout>
```
### 5. 响应点击事件和Toolbar联动
```java
tl = (Toolbar) findViewById(R.id.tl);
        tl.setTitle("NavigationView");
        setSupportActionBar(tl);
        ActionBar bar=getSupportActionBar();
        if (bar!=null){
            //设置‘三’到ToolBar
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeButtonEnabled(true);
        }
        //DrawerLayout布局开关联动
        dl = (DrawerLayout) findViewById(R.id.dl);
        //这里的R.string.open为自己添加上去的，内容随便
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        //为ToolBar添加按钮变化‘三’变成‘<-’
        toggle.syncState();
        //将打开关闭和滑动监听给予toggle
        dl.addDrawerListener(toggle);

        nav = (NavigationView) findViewById(R.id.nav);
        //获取头部layout的imageview
        CircleImageView civ=nav.getHeaderView(0).findViewById(R.id.header_icon);
        civ.setBorderColor(Color.LTGRAY);
        civ.setBorderWidth(5);
        //为菜单项添加点击事件
        nav.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            switch (item.getItemId()){
                case R.id.userInfo:
                    ToastUtils.showToast(this,"个人信息");
                    break;
                case R.id.design:
                    ToastUtils.showToast(this,"主题颜色");
                    break;
                case R.id.item_1:
                    ToastUtils.showToast(this,"item_1");
                    break;
                case R.id.item_2:
                    ToastUtils.showToast(this,"item_2");
                    break;
            }
            return true;
        });
//      在item后面添加消息
        FrameLayout fl=new FrameLayout(this);
        TextView tv=new TextView(this);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundColor(Color.RED);
        tv.setText("3条通知");
        tv.setPadding(10,5,10,5);
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity=Gravity.CENTER;
        fl.addView(tv,params);
        nav.getMenu().findItem(R.id.userInfo).setActionView(fl);
//       //通过按键用来隐藏菜单
//        nav.getMenu().findItem(R.id.userInfo).setAlphabeticShortcut();
        //设置选中颜色等，步奏：res下添加一个color文件，然后创建一个selector.xml
        ColorStateList csl=getResources().getColorStateList(R.color.nav_menu_color);
        nav.setItemTextColor(csl);
        nav.setItemIconTintList(csl);
```

### 6. ToolBar上的‘三’点击事件
```java
  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (dl.isDrawerOpen(Gravity.START)){
                    dl.closeDrawer(Gravity.START);
                }else {
                    dl.openDrawer(Gravity.START);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
```
