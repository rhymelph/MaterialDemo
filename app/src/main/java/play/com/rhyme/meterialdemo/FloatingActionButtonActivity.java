package play.com.rhyme.meterialdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/3.
 * 描述: [].
 */
public class FloatingActionButtonActivity extends BaseActivity implements View.OnClickListener {

    private ListView fab_list;
    private FloatingActionButton up_fab;

    private String[] items = new String[]{
            "点"
            , "击"
            , "悬"
            , "浮"
            , "按"
            , "钮"
            , "滑"
            , "动"
            , "到"
            , "顶"
            , "部"
            , "点"
            , "击"
            , "悬"
            , "浮"
            , "按"
            , "钮"
            , "滑"
            , "动"
            , "到"
            , "顶"
            , "部"
            , "点"
            , "击"
            , "悬"
            , "浮"
            , "按"
            , "钮"
            , "滑"
            , "动"
            , "到"
            , "顶"
            , "部"};
    private Toolbar mFabTl;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_fab;
    }

    @Override
    protected void initView() {
        mFabTl = (Toolbar) findViewById(R.id.fab_tl);
        mFabTl.setTitle("FloatingActionButton");
        setToolBar(mFabTl);

        fab_list = (ListView) findViewById(R.id.list);
        fab_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        up_fab = (FloatingActionButton) findViewById(R.id.up_fab);
        up_fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_fab:
                fab_list.smoothScrollToPosition(0);
                break;
        }
    }
}
