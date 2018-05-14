package play.com.rhyme.meterialdemo.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/14.
 * 描述: .
 */
public abstract class BaseActivity extends AppCompatActivity{
    boolean is_user_back=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();

    }
    protected abstract void initView();

    protected void setToolBar(Toolbar toolBar){
        is_user_back=true;
        setSupportActionBar(toolBar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract int getLayoutId();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (is_user_back){
            switch (item.getItemId()){
                case android.R.id.home:
                    onBackPressed();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
