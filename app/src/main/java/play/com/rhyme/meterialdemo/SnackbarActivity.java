package play.com.rhyme.meterialdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import play.com.rhyme.meterialdemo.Utils.ToastUtils;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/3.
 * 描述: [].
 */
public class SnackbarActivity extends BaseActivity {

    private ListView list;
    private Toolbar mSnbTl;


    private String[] items = new String[]{
            "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"
            , "普通模式"
            , "带按钮"
            , "带callback"
            , "自定义"};

    @Override
    protected void initView() {
        mSnbTl = (Toolbar) findViewById(R.id.snb_tl);
        mSnbTl.setTitle("Snackbar");
        setToolBar(mSnbTl);

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        list.setOnItemClickListener(((parent, view, position, id) -> {
            if (position % 4 == 3) {
                showNormalButtonStyle(items[position]);
            } else if (position % 4 == 2) {
                showNormalCallBack(items[position]);
            } else if (position % 4 == 1) {
                showNormalButton(items[position]);
            } else {
                showNormal(items[position]);
            }
        }));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_snb;
    }

    private void showNormal(String content) {
        Snackbar.make(list, content, Snackbar.LENGTH_SHORT).show();
    }

    private void showNormalButton(String content) {
        Snackbar.make(list, content, Snackbar.LENGTH_LONG)
                .setAction("确定", view -> {
                    ToastUtils.showToast(this, content);
                }).show();
    }

    private void showNormalButtonStyle(String content) {
        //按钮点击样式
        ColorStateList csl = getResources().getColorStateList(R.color.nav_menu_color);
        Snackbar snackbar = Snackbar.make(list, content, Snackbar.LENGTH_LONG)
                .setActionTextColor(csl)
                .setAction("确定", view -> {
                    ToastUtils.showToast(this, content);
                });
        View snackView = snackbar.getView();
        //获取snackbar text
        TextView snacktv = snackView.findViewById(R.id.snackbar_text);
        //获取snackbar button
        Button snackbtn = snackView.findViewById(R.id.snackbar_action);

        snackView.setBackgroundColor(Color.YELLOW);

        snacktv.setTextColor(Color.BLACK);
        snacktv.setGravity(Gravity.CENTER);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, 0, (int) snacktv.getTextSize(), (int) snacktv.getTextSize());
        snacktv.setCompoundDrawables(drawable, null, null, null);

        snackbar.show();
    }

    private void showNormalCallBack(String content) {
        final long[] times = {0};
        Snackbar.make(list, content, Snackbar.LENGTH_LONG)
                .addCallback(new Snackbar.Callback() {

                    @Override
                    public void onShown(Snackbar sb) {
                        times[0] = System.currentTimeMillis();
                        super.onShown(sb);
                    }

                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        Log.d("Snack", "显示了" + (System.currentTimeMillis() - times[0]) + "毫秒");
                        super.onDismissed(transientBottomBar, event);
                    }
                }).show();
    }

}
