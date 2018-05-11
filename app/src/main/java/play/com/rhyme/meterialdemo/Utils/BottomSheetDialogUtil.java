package play.com.rhyme.meterialdemo.Utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import play.com.rhyme.meterialdemo.R;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/11.
 * 描述: [].
 */
public class BottomSheetDialogUtil {

    public static View show(Context context){
        BottomSheetDialog dialog=new BottomSheetDialog(context);
        View view=View.inflate(context,
                R.layout.dialog_bsdu,null);
        dialog.setContentView(view);
        dialog.show();
        return view;
    }
}
