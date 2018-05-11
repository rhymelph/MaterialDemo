## TabLayout
> 使用该控件，可以与ViewPager联动，效果还是不错的！
## 1. 使用
```xml
    <android.support.design.widget.TabLayout
        android:id="@+id/tab_layout"
        app:tabGravity="fill"
        app:tabMode="scrollable"
        app:tabIndicatorColor="@color/colorPrimary"
        app:tabSelectedTextColor="@color/colorPrimary"
        app:tabTextColor="@android:color/black"
        app:tabBackground="@android:color/white"
        app:tabIndicatorHeight="2dp"
        app:tabContentStart="20dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    <android.support.v4.view.ViewPager
        android:id="@+id/tab_vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

## 2. 属性介绍
| 属性 | 介绍 |
| - | :-: |
| tabGravity | tab的位置：fill，center |
| tabMode | tab的模式：scrollable滚动，fixed固定 |
| tabIndicatorColor | tab指示器颜色 |
| tabSelectedTextColor | 选中的文字颜色 |
| tabTextColor | 普通的文字颜色 |
| tabBackground | tab背景 |
| tabIndicatorHeight | tab指示器高度 |
| tabContentStart | tab内容开始位置 |

### 3. 与ViewPager联动
1. 务必重写getPageTitle
```java
  private ViewPager tab_vp;
  private List<View> viewList;

  private void initView() {
        viewList=new ArrayList<>();
        viewList.add(getViews());
        viewList.add(getViews());
        viewList.add(getViews());
        viewList.add(getViews());
        viewList.add(getViews());
        viewList.add(getViews());
        tab_vp = (ViewPager) findViewById(R.id.tab_vp);
        tab_vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }
            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "手机";
                    case 1:
                        return "电脑";
                    default:
                        return "女装";
                }
            }
        });
    }
    
    private  int i=0;

    public View getViews() {
        TextView textView=new TextView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setText("Page."+i);
        textView.setLayoutParams(layoutParams);
        i++;
        return textView;
    }
```

2. 调用tabLayout的方法setupWithViewPager()
```java
    private TabLayout tab_layout;
    private void initView() {
    //......
    tab_layout = (TabLayout) findViewById(R.id.tab_layout);
    tab_layout.setupWithViewPager(tab_vp);
    }
```

3. 定制化
> 添加角标
> 实现思路：通过反射获取已经设置了的tabView
>           tabView是一个LinearLayout
>           通过getChildAt(0)获取第一个控件（该控件为ImageView）
>           将它移除改为添加一个FrameLayout布局然后重新添加
>           ，再添加一个角标TextView
```java
    private void initView() {
    //......
        List<Integer> badges = new ArrayList<>();
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        TabLayoutHelper.addBadge(tab_layout,badges);
    }
    
//TabLayoutHelper
private static class TabLayoutHelper{
        private static void addBadge( TabLayout tabLayout,List<Integer> badges){
            try {
                Field field=TabLayout.class.getDeclaredField("mTabs");
                field.setAccessible(true);
                ArrayList<TabLayout.Tab> tabArrayList= (ArrayList<TabLayout.Tab>) field.get(tabLayout);
                field.setAccessible(false);
                if (tabArrayList!=null){
                    int size=tabArrayList.size();
                    if (size!=badges.size()){
                        throw new IllegalArgumentException("badges number not equal bottomView number ");
                    }
                    for (int i=0;i<size;i++){
                        TabLayout.Tab tab=tabArrayList.get(i);
                        Field tabField= TabLayout.Tab.class.getDeclaredField("mView");
                        tabField.setAccessible(true);
                        LinearLayout tabView= (LinearLayout) tabField.get(tab);
                        tabField.setAccessible(false);

                        TextView textView;
                        if (!(tabView.getChildAt(0) instanceof ImageView)){
                            FrameLayout fl= (FrameLayout) tabView.getChildAt(0);
                            textView = (TextView) fl.getChildAt(1);
                            textView.setText(String.valueOf(badges.get(i)));
                        }else {
                            tab.setIcon(R.mipmap.ic_launcher);
                            ImageView iv= (ImageView) tabView.getChildAt(0);
                            tabView.removeView(iv);
                            FrameLayout fl=new FrameLayout(tabLayout.getContext());
                            tabView.addView(fl,0);
                            FrameLayout.LayoutParams params1=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params1.gravity=Gravity.CENTER;
                            fl.addView(iv,params1);

                            textView= new TextView(tabLayout.getContext());
                            textView.setTextSize(10);
                            FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.gravity=Gravity.END|Gravity.TOP;
                            textView.setBackgroundResource(R.drawable.red_circle_badge);
                            textView.setText(String.valueOf(badges.get(i)));
                            textView.setTextColor(Color.WHITE);
                            textView.setGravity(Gravity.CENTER);
                            fl.addView(textView,params);
                        }
                        if (badges.get(i) == 0) {
                            textView.setVisibility(View.INVISIBLE);
                            textView.setAnimation(AnimationUtils.loadAnimation(tabLayout.getContext(), R.anim.hide_badge_anim));
                        } else {
                            textView.setVisibility(View.VISIBLE);
                            textView.setAnimation(AnimationUtils.loadAnimation(tabLayout.getContext(), R.anim.show_badge_anim));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
```


