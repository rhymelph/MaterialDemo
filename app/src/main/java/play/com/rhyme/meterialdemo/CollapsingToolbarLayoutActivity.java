package play.com.rhyme.meterialdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import play.com.rhyme.meterialdemo.adapter.ABLAdapter;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/4.
 * 描述: [].
 */
public class CollapsingToolbarLayoutActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView img_tool;
    private Toolbar tool_con;
    private CollapsingToolbarLayout coll_tb;
    private AppBarLayout appbar;
    private FloatingActionButton fab;
    private RecyclerView ctl_rv;
    private CircleImageView close_img;
    private CircleImageView open_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctl);
        initView();
    }

    private List<String> items;

    private void initView() {
        img_tool = (ImageView) findViewById(R.id.img_tool);
        tool_con = (Toolbar) findViewById(R.id.tool_con);
        coll_tb = (CollapsingToolbarLayout) findViewById(R.id.coll_tb);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ctl_rv = (RecyclerView) findViewById(R.id.ctl_rv);
        fab.setOnClickListener(this);

        tool_con.setTitle("CollapsingToolbarLayout");
        setSupportActionBar(tool_con);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add("Item:" + i);
        }
        ctl_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ctl_rv.setAdapter(new ABLAdapter(this, items));

        appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset+appBarLayout.getTotalScrollRange()==0){
                if (close_img.getVisibility()==View.INVISIBLE)
                close_img.setVisibility(View.VISIBLE);
            }else {
                if (close_img.getVisibility()==View.VISIBLE)
                close_img.setVisibility(View.INVISIBLE);
            }
        });
        close_img = (CircleImageView) findViewById(R.id.close_img);
        open_image = (CircleImageView) findViewById(R.id.open_image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
