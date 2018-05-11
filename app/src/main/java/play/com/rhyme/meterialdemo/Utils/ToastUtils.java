package play.com.rhyme.meterialdemo.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/2.
 * 描述: [].
 */
public class ToastUtils {
    private static Toast t;

    public static void showToast(Context context,String content){
        if (t==null){
            t=Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }else {
            t.setText(content);
        }
        t.show();
    }
}
