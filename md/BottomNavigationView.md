## BottomNavigationView

### 1.新建一个菜单
```xml
<!--res/menu/menu_bnv3-->
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/home"
        android:title="主页"
        android:icon="@drawable/ic_home_black"/>
    <item android:id="@+id/shop_cart"
        android:title="购物车"
        android:icon="@drawable/ic_add_shopping_cart_black"/>
    <item android:id="@+id/person"
        android:title="我"
        android:icon="@drawable/ic_person_outline_black"/>
</menu>
```

### 2.布局中使用
```xml
    <android.support.design.widget.BottomNavigationView
        android:id="@+id/normal_bnv3"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:menu="@menu/menu_bnv3"
        app:itemIconTint="@android:color/black"
        app:itemTextColor="@color/colorPrimaryDark"
        app:itemBackground="@android:color/background_light"
        app:elevation="5dp"
         />
```

### 3.属性介绍
| 属性 | 介绍 |
| - | :-: |
| menu | 指定的菜单 | 
| itemIconTint | 图标颜色 | 
| itemTextColor | 文字颜色 | 
| itemBackground | 背景颜色 | 
| elevation | 阴影高度 | 

### 4.点击事件
```java
        BottomNavigationView normal_bnv3 = (BottomNavigationView) findViewById(R.id.normal_bnv3);
        all_visible_bnv4.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                //todo 点击的id判断
                default:
                    break;
            }
            return true;
        });
```

### 5.定制化
1. item>3取消动画效果
> BottomNavigationView中最大的item为5，当item大于3时不会显示全item的文字，只显示选中的那一个，当选中其它时会有一个动画效果，一般来说，我们需要取消这个动画效果，具体取消利用反射修改**mShiftingMode**值
```java
private static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftingMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView bniv = (BottomNavigationItemView) menuView.getChildAt(i);
                    bniv.setShiftingMode(false);
                    bniv.setChecked(bniv.getItemData().isChecked());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
```

2. 添加角标
```java
private static class BottomNavigationViewHelper {
        ......
        public static void addBadgeView(BottomNavigationView view, List<Integer> badges) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            if (badges.size() != menuView.getChildCount()) {
                throw new IllegalArgumentException("badges number not equal bottomView number");
            }
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.END | Gravity.TOP;
            params.rightMargin = 8;
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView bniv = (BottomNavigationItemView) menuView.getChildAt(i);
                TextView textView = null;
                if (bniv.getChildCount() == 2) {
                    textView = new TextView(view.getContext());
                    textView.setTextSize(10);
                    textView.setBackgroundResource(R.drawable.red_circle_badge);
                    textView.setText(String.valueOf(badges.get(i)));
                    textView.setTextColor(Color.WHITE);
                    textView.setGravity(Gravity.CENTER);
                    bniv.addView(textView, params);
                } else {
                    textView = (TextView) bniv.getChildAt(2);
                    textView.setText(String.valueOf(badges.get(i)));
                }
                if (badges.get(i) == 0) {
                    textView.setVisibility(View.INVISIBLE);
                    textView.setAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.hide_badge_anim));
                } else {
                    textView.setVisibility(View.VISIBLE);
                    textView.setAnimation(AnimationUtils.loadAnimation(view.getContext(), R.anim.show_badge_anim));
                }
            }
        }
    }
```
