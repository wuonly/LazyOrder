package com.cisoft.lazyorder.ui.account;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.cisoft.lazyorder.R;
import com.cisoft.lazyorder.core.common.CommonNetwork;
import com.cisoft.lazyorder.finals.ApiConstants;
import com.cisoft.lazyorder.ui.BaseActivity;
import com.cisoft.lazyorder.util.DialogFactory;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity {

    @BindView(id = R.id.et_mobile_number)
    private EditText mEtInputPhone;
    @BindView(id = R.id.et_input_code)
    private EditText etInputCode;
    @BindView(id = R.id.btn_obtain_code, click = true)
    private Button mBtnObtainCode;
    @BindView(id = R.id.btn_next_step, click = true)
    private Button mBtnNextStep;
    private Dialog mWaitTipDialog;

    private ObtainCodeBtnEnableTimer mObtainCodeBtnEnableTimer;
    private CommonNetwork mCommonNetwork;
    private SmsContent mSmsContent;

    //重新发送验证码的间隔时间（单位：秒）
    public static final int OBTAIN_CODE_INTERVAL = 30;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initData() {
        mSmsContent = new SmsContent(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, mSmsContent);
        mCommonNetwork = new CommonNetwork(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_step:
                verifyAuthCodeAndNextStep();
                break;
            case R.id.btn_obtain_code:
                obtainAuthCode();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void obtainAuthCode() {
        String inputPhone = mEtInputPhone.getText().toString();
        if (StringUtils.isEmpty(inputPhone)) {
            ViewInject.toast(getString(R.string.toast_phone_can_not_be_empty));
            return;
        }
        if(!StringUtils.isPhone(inputPhone)){
            ViewInject.toast(getString(R.string.toast_input_standard_phone));
            return;
        }
        mCommonNetwork.obtainSMSAuthCode(inputPhone, new CommonNetwork.OnSMSCodeSendCallback() {
            @Override
            public void onPreStart() {
                showWaitTip();
            }

            @Override
            public void onSuccess() {
                closeWaitTip();
                ViewInject.toast(getString(R.string.toast_success_to_send_sms_auth_code));

                //使获取验证码的按钮在规定时间内不可用
                if(mObtainCodeBtnEnableTimer != null){
                    mObtainCodeBtnEnableTimer.cancel();
                    mObtainCodeBtnEnableTimer.start();
                } else {
                    mObtainCodeBtnEnableTimer = new ObtainCodeBtnEnableTimer(OBTAIN_CODE_INTERVAL * 1000, 1000);
                    mObtainCodeBtnEnableTimer.start();
                }
            }

            @Override
            public void onFailure(int stateCode, String errorMsg) {
                closeWaitTip();
                ViewInject.toast(errorMsg);
            }
        });
    }

    /**
     * 验证验证码并进行下一步
     */
    private void verifyAuthCodeAndNextStep() {
        final String inputPhone = mEtInputPhone.getText().toString();
        String inputAuthCode = etInputCode.getText().toString();

        if (StringUtils.isEmpty(inputAuthCode)) {
            ViewInject.toast(getString(R.string.toast_sms_auth_code_can_not_be_empty));
            return;
        }
        if (StringUtils.isEmpty(inputPhone)) {
            ViewInject.toast(getString(R.string.toast_phone_can_not_be_empty));
            return;
        }
        if(!StringUtils.isPhone(inputPhone)){
            ViewInject.toast(getString(R.string.toast_phone_format_error));
            return;
        }
        mCommonNetwork.verifyPhoneBySMSAuthCode(inputPhone, inputAuthCode, new CommonNetwork.OnPhoneVerifyCallback() {
            @Override
            public void onPreStart() {
                showWaitTip();
            }

            @Override
            public void onSuccess() {
                closeWaitTip();
                Intent intent = new Intent(RegisterActivity.this, RegisterTwoActivity.class);
                intent.putExtra(ApiConstants.KEY_ACCOUNT_USER_PHONE, inputPhone);
                startActivity(intent);
                RegisterActivity.this.finish();
            }

            @Override
            public void onFailure(int stateCode, String errorMsg) {
                closeWaitTip();
                ViewInject.toast(errorMsg);
            }
        });
    }

    /**
     * 显示等待的提示对话框
     */
    private void showWaitTip() {
        if (mWaitTipDialog == null)
            mWaitTipDialog = DialogFactory.createWaitToastDialog(this,
                    getString(R.string.toast_wait));
        mWaitTipDialog.show();
    }

    /**
     * 关闭等待的提示对话框
     */
    private void closeWaitTip() {
        if (mWaitTipDialog != null && mWaitTipDialog.isShowing()) {
            mWaitTipDialog.dismiss();
        }
    }

    /*
    * 监听短信数据库
    */
    class SmsContent extends ContentObserver {

        private Cursor cursor = null;

        public SmsContent(Handler handler) {
            super(handler);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            cursor = managedQuery(Uri.parse("content://sms/inbox"), new String[] { "_id", "address", "read", "body" },
                    " read=? and body like ?", new String[] {"0", "%懒人点餐%"}, "_id desc");

            if (cursor != null && cursor.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put("read", "1");
                cursor.moveToNext();
                int smsbodyColumn = cursor.getColumnIndex("body");
                String smsBody = cursor.getString(smsbodyColumn);

                etInputCode.setText(getDynamicPassword(smsBody));
            }

            // 在用managedQuery的时候，不能主动调用close()方法， 否则在4.0+的系统上， 会发生崩溃
            if (Build.VERSION.SDK_INT < 14) {
                cursor.close();
            }
        }

        /**
         * 从字符串中截取连续6位数字组合 ([0-9]{" + 6 + "})截取六位数字 进行前后断言不能出现数字 用于从短信中获取动态密码
         *
         * @param str 短信内容
         * @return 截取得到的6位动态密码
         */
        public String getDynamicPassword(String str) {
            // 验证码的位数一般为六位
            Pattern continuousNumberPattern = Pattern.compile("(?<![0-9])([0-9]{"
                    + 6 + "})(?![0-9])");
            Matcher m = continuousNumberPattern.matcher(str);
            String dynamicPassword = "";
            while (m.find()) {
                System.out.print(m.group());
                dynamicPassword = m.group();
            }

            return dynamicPassword;
        }
    }

    /**
     * 获取短信验证码的按钮可用的倒计时内部类
     */
    class ObtainCodeBtnEnableTimer extends CountDownTimer {

        public ObtainCodeBtnEnableTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mBtnObtainCode.setEnabled(true);
            mBtnObtainCode.setText("重新获取");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnObtainCode.setEnabled(false);
            int leftWaitTime = (int)(millisUntilFinished / 1000);
            mBtnObtainCode.setText(leftWaitTime + "s 重新获取");
        }
    }
}
