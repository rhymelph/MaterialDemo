package play.com.rhyme.meterialdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/3.
 * 描述: [].
 */
public class CoordinatorLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private ListView list;
    private FloatingActionButton up_fab;
    private String[] items=new String[]{
            "SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"
            ,"SnackBar顶起悬浮按钮"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_cod);
        initView();
    }

    private void initView() {
        list = (ListView) findViewById(R.id.list);
        up_fab = (FloatingActionButton) findViewById(R.id.up_fab);
        up_fab.setOnClickListener(this);
        list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items));
        list.setOnItemClickListener(((parent, view, position, id) -> showNormal(items[position])));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up_fab:
                list.smoothScrollToPosition(0);
                break;
        }
    }
    private void showNormal(String content){
        Snackbar.make(list,content,Snackbar.LENGTH_SHORT).show();
    }
}
