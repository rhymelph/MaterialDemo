package play.com.rhyme.meterialdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import play.com.rhyme.meterialdemo.adapter.ABLAdapter;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/4.
 * 描述: [].
 */
public class AppBarLayoutActivity extends AppCompatActivity {

    private Toolbar apl_tb;
    private RecyclerView apl_rv;

    private List<String> items;
    private AppBarLayout appbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbarlayout);
        initView();
    }

    private void initView() {
        apl_tb = (Toolbar) findViewById(R.id.apl_tb);
        apl_rv = (RecyclerView) findViewById(R.id.apl_rv);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        setSupportActionBar(apl_tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add("Item:" + i);
        }
        items.add(0,"snap>>>>>>>>>需要与scroll属性组合，没完全隐藏和显示，自动隐藏显示");
        items.add(1,"scroll>>>>>>>>>随着滚动而滚动");
        items.add(2,"enterAlways>>>>>>>>>需要与scroll属性组合，如果隐藏向下滑动就显示");
        items.add(3,"enterAlwaysCollapsed>>>>>>>>>需要与scroll和enterAlways属性组合，自动添加scroll|enterAlways属性，向下滑动先显示最小高度，到顶部再显示正常高度");
        items.add(4,"exitUntilCollapsed>>>>>>>>>需要与scroll属性组合，自动添加scroll属性，向上滑动保留最小高度");
        apl_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        apl_rv.setAdapter(new ABLAdapter(this,items));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_abl, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams)appbar.getChildAt(0) .getLayoutParams();
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.snap:
                Snackbar.make(apl_rv, "需要与Scroll属性组合，自动添加scroll属性，没完全隐藏和显示，自动隐藏显示", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP | AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.scroll:
                Snackbar.make(apl_rv, "随着滚动而滚动", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.enterAlways:
                Snackbar.make(apl_rv, "需要与Scroll属性组合，自动添加scroll属性，如果隐藏向下划就显示", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS | AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.enterAlwaysCollapsed:
                Snackbar.make(apl_rv, "需要与Scroll和enterAlways属性组合，自动添加scroll|enterAlways属性，先显示最小高度，到顶部在显示最后高度", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS|AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED|AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
            case R.id.exitUntilCollapsed:
                Snackbar.make(apl_rv, "需要与Scroll属性组合，自动添加scroll属性，保留最小高度", Snackbar.LENGTH_SHORT).show();
                params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED| AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
                break;
        }
        appbar.getChildAt(0).setLayoutParams(params);
        return super.onOptionsItemSelected(item);
    }

}
