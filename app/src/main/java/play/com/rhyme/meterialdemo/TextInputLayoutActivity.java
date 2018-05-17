package play.com.rhyme.meterialdemo;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import play.com.rhyme.meterialdemo.Utils.ToastUtils;
import play.com.rhyme.meterialdemo.common.BaseActivity;

/**
 * 作者: rhyme(rhymelph@qq.com).
 * 日期: 2018/5/16.
 * 描述: .
 */
public class TextInputLayoutActivity extends BaseActivity {
    private Toolbar mTilTl;
    /**
     * 用户名
     */
    private TextInputEditText mTietUser;
    private TextInputLayout mTilUser;
    /**
     * 密码
     */
    private TextInputEditText mTietPass;
    private TextInputLayout mTilPass;

    @Override
    protected void initView() {
        mTilTl = (Toolbar) findViewById(R.id.til_tl);
        mTilTl.setTitle("TextInputLayout");
        setToolBar(mTilTl);

        mTietUser = (TextInputEditText) findViewById(R.id.tiet_user);
        mTilUser = (TextInputLayout) findViewById(R.id.til_user);
        mTietPass = (TextInputEditText) findViewById(R.id.tiet_pass);
        mTilPass = (TextInputLayout) findViewById(R.id.til_pass);
        mTietUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()<8){
                    mTilUser.setErrorEnabled(true);
                    mTilUser.setError("用户名不能小于8");
                }else {
                    mTilUser.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tlt;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_til,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.complete:
                submit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit(){
        String user_name=mTietUser.getText().toString().replaceAll(" ","");
        if (TextUtils.isEmpty(user_name)){
            mTilUser.setErrorEnabled(true);
            mTilPass.setErrorEnabled(false);
            mTilUser.setError("请输入用户名");
            mTietUser.requestFocus();
            return;
        }
        if (user_name.length()<8||user_name.length()>16){
            mTilUser.setErrorEnabled(true);
            mTilPass.setErrorEnabled(false);
            mTilUser.setError("用户名长度为8-16位");
            mTietUser.requestFocus();
            mTietUser.setSelection(user_name.length());
            return;
        }

        String password=mTietPass.getText().toString().replaceAll(" ","");
        if (TextUtils.isEmpty(password)){
            mTilUser.setErrorEnabled(false);
            mTilPass.setErrorEnabled(true);
            mTilPass.setError("请输入密码");
            mTietPass.requestFocus();
            return;
        }
        String passwordMatches="^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$";
        if (!password.matches(passwordMatches)){
            mTilUser.setErrorEnabled(false);
            mTietPass.requestFocus();
            mTilPass.setError("密码必须包含大小写字母，长度为8-16位");
            mTilPass.setErrorEnabled(true);
            mTietPass.setSelection(password.length());
            return;
        }

        mTilUser.setErrorEnabled(false);
        mTilPass.setErrorEnabled(false);

        ToastUtils.showToast(this,"成功！");

    }
}
