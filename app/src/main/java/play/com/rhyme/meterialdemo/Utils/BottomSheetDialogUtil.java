package play.com.rhyme.meterialdemo.Utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import play.com.rhyme.meterialdemo.R;
import play.com.rhyme.meterialdemo.adapter.ABLAdapter;
import play.com.rhyme.meterialdemo.view.MyBottomSheetDialog;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/11.
 * 描述: [].
 */
public class BottomSheetDialogUtil {

    public static View showPickImage(Context context){
        BottomSheetDialog dialog=new BottomSheetDialog(context);
        View view=View.inflate(context,
                R.layout.dialog_bsdu,null);
        dialog.setContentView(view);
        dialog.show();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        return view;
    }
    public static View showList(Context context){
        BottomSheetDialog dialog=new BottomSheetDialog(context);
        //使用listview需要自行实现nes接口，不让只能划上不能划下
        View view=View.inflate(context,
                R.layout.recyclerview,null);
        dialog.setContentView(view);
        dialog.show();
        RecyclerView lv=view.findViewById(R.id.apl_rv);
        List<String> items=new ArrayList<>();

        for (int i=0;i<50;i++){
            items.add("Item."+i);
        }
        lv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        lv.setAdapter(new ABLAdapter(context, items));
        return view;

    }
    public static View showNoSimple(Context context){
        MyBottomSheetDialog dialog=new MyBottomSheetDialog(context);
        View view=View.inflate(context,
                R.layout.recyclerview,null);
        dialog.setContentView(view);
        RecyclerView lv=view.findViewById(R.id.apl_rv);
        List<String> items=new ArrayList<>();

        for (int i=0;i<50;i++){
            items.add("Item."+i);
        }
        lv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        lv.setAdapter(new ABLAdapter(context, items));
        dialog.show();
        return view;

    }
}
