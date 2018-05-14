package play.com.rhyme.meterialdemo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/11.
 * 描述: [].
 */
public class TabLayoutViewActivity extends BaseActivity {

    private Toolbar tab_tl;
    private TabLayout tab_layout;
    private ViewPager tab_vp;
    private List<View> viewList;

    @Override
    protected void initView() {
        tab_tl = (Toolbar) findViewById(R.id.tab_tl);
        tab_tl.setTitle("TabLayout");
        setToolBar(tab_tl);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

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
        tab_layout.setupWithViewPager(tab_vp);
        List<Integer> badges = new ArrayList<>();
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        TabLayoutHelper.addBadge(tab_layout,badges);
        tab_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<Integer> badges = new ArrayList<>();
                badges.add((int) (Math.random() * 99));
                badges.add((int) (Math.random() * 99));
                badges.add((int) (Math.random() * 99));
                badges.add((int) (Math.random() * 99));
                badges.add((int) (Math.random() * 99));
                badges.add((int) (Math.random() * 99));
                TabLayoutHelper.addBadge(tab_layout,badges);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tlv;
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
}
