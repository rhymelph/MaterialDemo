package play.com.rhyme.meterialdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

import play.com.rhyme.meterialdemo.Utils.BottomSheetDialogUtil;
import play.com.rhyme.meterialdemo.Utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    String[] widgets = new String[]{"ToolBar"
            ,"NavigationView"
            ,"FloatingActionButton"
            ,"Snackbar"
            ,"CoordinatorLayout"
            ,"AppbarLayout"
            ,"CollapsingToolbarLayout"
            ,"BottomNavigationView"
            ,"TabLayout"
            ,"CircularReveal"
            ,"BottomSheetDialog"};
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private String path;



    private void initView() {
        list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,widgets);
        list.setAdapter(adapter);
        list.setOnItemClickListener((parent, view, position, id) ->{
            Intent intent=new Intent();
            switch (widgets[position]){
                case "ToolBar":
                    intent.setClass(this,ToolBarActivity.class);
                    break;
                case "NavigationView":
                    intent.setClass(this,NavigationViewActivity.class);
                    break;
                case "FloatingActionButton":
                    intent.setClass(this,FloatingActionButtonActivity.class);
                    break;
                case "Snackbar":
                    intent.setClass(this,SnackbarActivity.class);
                    break;
                case "CoordinatorLayout":
                    intent.setClass(this,CoordinatorLayoutActivity.class);
                    break;
                case "AppbarLayout":
                    intent.setClass(this,AppBarLayoutActivity.class);
                    break;
                case "CollapsingToolbarLayout":
                    intent.setClass(this,CollapsingToolbarLayoutActivity.class);
                    break;
                case "BottomNavigationView":
                    intent.setClass(this,BottomNavigationViewActivity.class);
                    break;
                case "TabLayout":
                    intent.setClass(this,TabLayoutViewActivity.class);
                    break;
                case "CircularReveal":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        intent.setClass(this,CircularRevealActivity.class);
                    }else{
                        ToastUtils.showToast(this,"当前系统不支持CircularReveal");
                        return;
                    }
                    break;
                case "BottomSheetDialog":
                    intent.setClass(this, BottomSheetDialogActivity.class);

//                        CircularRevealUtil.start();
                    break;

            }
            startActivity(intent);
        });
    }
}
