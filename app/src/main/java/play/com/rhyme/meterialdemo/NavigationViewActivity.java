package play.com.rhyme.meterialdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import play.com.rhyme.meterialdemo.Utils.ToastUtils;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/3.
 * 描述: [].
 */
public class NavigationViewActivity extends BaseActivity {
    public static final String TAG="NavigationViewActivity";
    private NavigationView nav;
    private Toolbar tl;
    private DrawerLayout dl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nav;
    }

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

    @Override
    protected void initView() {
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
        //在item后面添加消息
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
//        //通过按键用来隐藏菜单
//        nav.getMenu().findItem(R.id.userInfo).setAlphabeticShortcut();
        //设置选中颜色等，步奏：res下添加一个color文件，然后创建一个selector.xml
        ColorStateList csl=getResources().getColorStateList(R.color.nav_menu_color);
        nav.setItemTextColor(csl);
        nav.setItemIconTintList(csl);
    }

}
