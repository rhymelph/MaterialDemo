package play.com.rhyme.meterialdemo;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import play.com.rhyme.meterialdemo.Utils.CircularRevealUtil;
import play.com.rhyme.meterialdemo.Utils.ToastUtils;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/14.
 * 描述: .
 */
public class CircularRevealActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mCrlTl;
    private View bg;
    private View tran_bg;
    /**
     * 打开动画
     */
    private TextView mCrlOpen;
    /**
     * 关闭动画
     */
    private TextView mCrlClose;
    private ConstraintLayout mRoot;

    @Override
    protected void initView() {
        mCrlTl = (Toolbar) findViewById(R.id.crl_tl);
        mCrlTl.setTitle("CircularReveal");
        setToolBar(mCrlTl);
        bg = (View) findViewById(R.id.bg);
        tran_bg = (View) findViewById(R.id.tran_bg);
        mCrlOpen = (TextView) findViewById(R.id.crl_open);
        mCrlClose = (TextView) findViewById(R.id.crl_close);
        mRoot = (ConstraintLayout) findViewById(R.id.root);
        mCrlOpen.setOnClickListener(this);
        mCrlClose.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cra;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.crl_open:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (tran_bg.getVisibility() == View.INVISIBLE) {
                        Animator animator=CircularRevealUtil.formatAnimator(v, tran_bg, true);
                        animator.addListener(new Animator.AnimatorListener() {
                            private int r,g,b;
                            private Random random=new Random();

                            @Override
                            public void onAnimationStart(Animator animation) {
                                r=random.nextInt(256);
                                g=random.nextInt(256);
                                b=random.nextInt(256);
                                tran_bg.setBackgroundColor(Color.rgb(r,g,b));

                                tran_bg.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                tran_bg.setVisibility(View.INVISIBLE);
                                bg.setBackgroundColor(Color.rgb(r,g,b));
                            }
                            @Override
                            public void onAnimationCancel(Animator animation) {
                            }
                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        });
                        animator.start();
                    }
                } else {
                    ToastUtils.showToast(this, "系统不支持该动画");
                }
                break;
            case R.id.crl_close:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = CircularRevealUtil.formatAnimator(v, tran_bg, false);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            tran_bg.setVisibility(View.VISIBLE);
                            bg.setBackgroundColor(Color.WHITE);
                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            tran_bg.setVisibility(View.INVISIBLE);
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }
                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    });
                    animator.start();
                } else {
                    ToastUtils.showToast(this, "系统不支持该动画");
                }
                break;

        }
    }
}
