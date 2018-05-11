package play.com.rhyme.meterialdemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/10.
 * 描述: [].
 */
public class BottomNavigationViewActivity extends AppCompatActivity {

    private BottomNavigationView all_visible_bnv4;
    private BottomNavigationView badges_visible_bnv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bnv);
        initView();
    }

    private void initView() {
        all_visible_bnv4 = (BottomNavigationView) findViewById(R.id.all_visible_bnv4);
        BottomNavigationViewHelper.disableShiftingMode(all_visible_bnv4);
        all_visible_bnv4.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                //todo 点击的id判断
                default:
                    break;
            }
            return true;
        });
        List<Integer> badges = new ArrayList<>();
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges.add((int) (Math.random() * 99));
        badges_visible_bnv4 = (BottomNavigationView) findViewById(R.id.badges_visible_bnv4);
        badges_visible_bnv4.setOnNavigationItemSelectedListener(item -> {
                    badges.set(0, (int) (Math.random() * 99));
                    badges.set(1, (int) (Math.random() * 99));
                    badges.set(2, (int) (Math.random() * 99));
                    badges.set(3, (int) (Math.random() * 99));
                    BottomNavigationViewHelper.addBadgeView(badges_visible_bnv4, badges);
                    return true;
                }
        );
        BottomNavigationViewHelper.disableShiftingMode(badges_visible_bnv4);
        BottomNavigationViewHelper.addBadgeView(badges_visible_bnv4, badges);

    }


    //使全项打开
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
}

