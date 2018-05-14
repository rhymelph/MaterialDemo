package play.com.rhyme.meterialdemo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import play.com.rhyme.meterialdemo.Utils.BottomSheetDialogUtil;
import play.com.rhyme.meterialdemo.Utils.ToastUtils;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/14.
 * 描述: .
 */
public class BottomSheetDialogActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mBsdlTl;
    /**
     * 图片样式
     */
    private Button mBsdlBtn1;
    /**
     * 列表样式
     */
    private Button mBsdlBtn2;
    /**
     * 复杂模式
     */
    private Button mBsdlBtn3;

    @Override
    protected void initView() {
        mBsdlTl = (Toolbar) findViewById(R.id.bsdl_tl);
        mBsdlTl.setTitle("BottomSheetDialog");
        setToolBar(mBsdlTl);

        mBsdlBtn1 = (Button) findViewById(R.id.bsdl_btn1);
        mBsdlBtn1.setOnClickListener(this);
        mBsdlBtn2 = (Button) findViewById(R.id.bsdl_btn2);
        mBsdlBtn2.setOnClickListener(this);
        mBsdlBtn3 = (Button) findViewById(R.id.bsdl_btn3);
        mBsdlBtn3.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bsdl;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bsdl_btn1:
                View view = BottomSheetDialogUtil.showPickImage(this);
                ViewGroup viewGroup = (ViewGroup) view;
                Button btn1 = (Button) viewGroup.getChildAt(0);
                btn1.setOnClickListener(v1 -> ToastUtils.showToast(this, "拍照"));
                Button btn2 = (Button) viewGroup.getChildAt(1);
                btn2.setOnClickListener(v1 -> ToastUtils.showToast(this, "图库"));
                Button btn3 = (Button) viewGroup.getChildAt(2);
                btn3.setOnClickListener(v1 -> ToastUtils.showToast(this, "取消"));
                break;
            default:
                break;
            case R.id.bsdl_btn2:
                BottomSheetDialogUtil.showList(this);
                break;
            case R.id.bsdl_btn3:
                BottomSheetDialogUtil.showNoSimple(this);
                break;
        }
    }

}
