package play.com.rhyme.meterialdemo.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/3.
 * 描述: [].
 */
public class RhyBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    private int max_scroll;//最大移动

    private int max_width;//最大宽度
    private int min_width;//最小宽度

    private int tb_height;//toolbar的高度
    private int appbar_height;//appbar的高度

    private int marginRight;//距离右面的外边距

    private int startX=0;//开始X位置
    private int startY=0;//开始Y位置
    private int endX=0;//结束X位置
    private int endY=0;//结束Y位置
    public static final String TAG=RhyBehavior.class.getSimpleName();
    public RhyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics=context.getResources().getDisplayMetrics();
        min_width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,32,metrics);
        marginRight=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,16,metrics);

        tb_height= context.getResources().getDimensionPixelSize(android.support.design.R.dimen
                .abc_action_bar_default_height_material);

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        if (appbar_height==0){
            //appbar高
            appbar_height=dependency.getBottom();
        }
        if (max_scroll==0){
            //appbar高减去toolbar高
            max_scroll=appbar_height-tb_height;
        }
        if (max_width==0){
            //最大宽度
            max_width=Math.min(child.getHeight(),child.getWidth());
        }
        if (startX==0){
            //开始X坐标
            startX= (dependency.getWidth()-child.getWidth())/2;
        }
        if (startY==0){
            //开始Y坐标
            startY= (dependency.getHeight()-child.getHeight())/2;
        }
        if (endX==0){
            endX=dependency.getWidth()-marginRight-min_width;
        }
        if (endY==0){
            endY=(tb_height-min_width)/2;
        }
        float e=(appbar_height-dependency.getBottom())*1.0f/(max_scroll*1.0f);
        float moveY=startY-e*(startY-endY);
//        float moveX=e*(startX+max_width-marginRight-min_width);
        float moveX=startX-e*(startX-endX);
        float like=startX-endX;
        child.setY(moveY);
        if (like<0){//方向为向右
            if (moveX>endX){
                return true;
            }
        }else {//方向为向左
            if (moveX<endX){
                return true;
            }
        }
        child.setX(moveX);
        float nowidth=max_width-(max_width-min_width)*e;
        CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        params.width= (int) nowidth;
        params.height= (int) nowidth;
        child.setLayoutParams(params);
        return true;
    }
}
