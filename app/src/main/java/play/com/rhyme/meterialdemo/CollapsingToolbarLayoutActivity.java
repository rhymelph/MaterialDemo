package play.com.rhyme.meterialdemo;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import play.com.rhyme.meterialdemo.adapter.ABLAdapter;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/4.
 * 描述: [].
 */
public class CollapsingToolbarLayoutActivity extends BaseActivity implements View.OnClickListener {


    private ImageView img_tool;
    private Toolbar tool_con;
    private CollapsingToolbarLayout coll_tb;
    private AppBarLayout appbar;
    private FloatingActionButton fab;
    private RecyclerView ctl_rv;
    private CircleImageView close_img;
    private CircleImageView open_image;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ctl;
    }
    private List<String> items;

    @Override
    protected void initView() {
        img_tool = (ImageView) findViewById(R.id.img_tool);

        tool_con = (Toolbar) findViewById(R.id.tool_con);
        tool_con.setTitle("CollapsingToolbarLayout");
        setToolBar(tool_con);

        coll_tb = (CollapsingToolbarLayout) findViewById(R.id.coll_tb);

        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset+appBarLayout.getTotalScrollRange()==0){
                if (close_img.getVisibility()==View.INVISIBLE)
                    close_img.setVisibility(View.VISIBLE);
            }else {
                if (close_img.getVisibility()==View.VISIBLE)
                    close_img.setVisibility(View.INVISIBLE);
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ctl_rv = (RecyclerView) findViewById(R.id.ctl_rv);
        items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add("Item:" + i);
        }
        ctl_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ctl_rv.setAdapter(new ABLAdapter(this, items));

        close_img = (CircleImageView) findViewById(R.id.close_img);
        open_image = (CircleImageView) findViewById(R.id.open_image);
    }


    //点击心型
    private boolean status = true;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (status) {
                    fab.setImageResource(R.drawable.ic_favorite_while);
                    status = false;
                } else {
                    fab.setImageResource(R.drawable.ic_favorite_border_while);
                    status = true;
                }
                break;
        }
    }

}
