package play.com.rhyme.meterialdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import play.com.rhyme.meterialdemo.Utils.BottomSheetDialogUtil;
import play.com.rhyme.meterialdemo.Utils.CircularRevealUtil;

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
                        CircularRevealUtil.start(view);
                        return;
                    }
                    break;
                case "BottomSheetDialog":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        BottomSheetDialogUtil.show(this);
//                        CircularRevealUtil.start();
                        return;
                    }
                    break;

            }
            startActivity(intent);
        });
    }
}
