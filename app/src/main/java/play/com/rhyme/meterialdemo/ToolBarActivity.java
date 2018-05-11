package play.com.rhyme.meterialdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;

import play.com.rhyme.meterialdemo.Utils.ToastUtils;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/2.
 * 描述: [].
 */
public class ToolBarActivity extends AppCompatActivity {
    private Toolbar tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        //获取switch，search和CastBtn
        //切换
        MenuItem  switchItem=menu.findItem(R.id.app_bar_switch);
        Switch mswtich=switchItem.getActionView().findViewById(R.id.switchForActionBar);
        mswtich.setOnCheckedChangeListener((buttonView,isCheck) ->{
            ToastUtils.showToast(this,"切换为"+isCheck);
        });
        //搜索
        SearchView msearch= (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        msearch.setOnSearchClickListener( v ->{
            ToastUtils.showToast(this,"点击了search");
        });
        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //当点击了输入法里的搜索会被调用
                ToastUtils.showToast(ToolBarActivity.this,"搜索提交"+query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //当文字改变时调用
                ToastUtils.showToast(ToolBarActivity.this,"搜索文字"+newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;

//            case R.id.app_bar_search:
//                ToastUtils.showToast(this,"点击了搜索");
//                break;
//            case R.id.app_bar_switch:
//                ToastUtils.showToast(this,"点击了切换");
//                break;
            case R.id.normal_item:
                ToastUtils.showToast(this,"点击了正常的item");
                break;
            case R.id.media_route_menu_item:
                ToastUtils.showToast(this,"点击了投射");
                break;
            case R.id.more_1:
                ToastUtils.showToast(this,"点击了更多");
                break;
            case R.id.more_11:
                ToastUtils.showToast(this,"点击了更多1");
                break;
            case R.id.more_12:
                ToastUtils.showToast(this,"点击了更多2");
                break;
            case R.id.more_2:
                ToastUtils.showToast(this,"点击了更多2");
                break;
            case R.id.more_21:
                ToastUtils.showToast(this,"点击了更多1");
                break;
            case R.id.more_22:
                ToastUtils.showToast(this,"点击了更多2");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        tl = (Toolbar) findViewById(R.id.tl);
        tl.setTitle("Toolbar");//标题
//        tl.setLogo(R.mipmap.ic_launcher);//图标
//        tl.setLogoDescription("图标");//logo介绍
//        tl.setSubtitle("small toolbar");//简介
//        tl.setSubtitleTextColor(Color.BLUE);
        setSupportActionBar(tl);
        //添加一个居中的标题
        TextView tv=new TextView(this);
        tv.setText("ToolBar");
        Toolbar.LayoutParams params=new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER_HORIZONTAL;
        tv.setLayoutParams(params);
//        tl.addView(tv);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
//            给左上角添加返回按钮
//            actionBar.setDisplayHomeAsUpEnabled(true);
////            使自定义的普通view能在title上显示,setCustom起作用
//            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setCustomView(tv);
            //决定左上角的图标是否可点击
//            actionBar.setHomeButtonEnabled(true);
//            //左上角图标是否显示，受setHomeButtonEnabled影响
//            actionBar.setDisplayShowHomeEnabled(true);
//            //对应title
//            actionBar.setDisplayShowTitleEnabled(false);
        }

    }
}
