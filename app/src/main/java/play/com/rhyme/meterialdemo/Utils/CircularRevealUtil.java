package play.com.rhyme.meterialdemo.Utils;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/11.
 * 描述: [].
 */
public class CircularRevealUtil {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void start(View lv){
        int cr=lv.getWidth()/2>lv.getHeight()/2?lv.getWidth()/2:lv.getHeight()/2;
        Animator animator= ViewAnimationUtils.createCircularReveal(lv,lv.getWidth()/2,lv.getHeight()/2,0,cr);
        animator.setDuration(1000);
        animator.start();
    }
}
