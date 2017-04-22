package com.example.tianyunchen.zaozaotuandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.LoginDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;
import com.example.tianyunchen.zaozaotuandroid.untils.UserSharePreference;
import com.example.tianyunchen.zaozaotuandroid.view.MyToast;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements DaoListener,View.OnClickListener{
    private EditText mEditPhone,mEditPassword;
    private Button mButtonLogin;
    private LoginDao loginDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initDao();
    }

    @Override
    protected void initViews() {
        mEditPhone = (EditText)findViewById(R.id.edt_phone);
        mEditPassword = (EditText)findViewById(R.id.edt_password);
        mButtonLogin = (Button)findViewById(R.id.btn_login);
        mButtonLogin.setOnClickListener(this);
    }

    private void initDao(){
        loginDao = new LoginDao();
        loginDao.setDaoLisenter(this);

    }

    @Override
    public void onSuccessful(BaseDao dao) {
        if(loginDao.getStatus()==1){
            UserSharePreference.getInstance(this).setToken(loginDao.getToken())
                                                 .setFirstLaunch(true);
            Intent intent  = new Intent(this,MenuActivity.class);
            startActivity(intent);

        }else {
            MyToast.showToast(MainActivity.this,"登陆失败","手机或者密码错误");


        }
    }

    @Override
    public void onFaild(HttpException exception) {
        Log.d("Activity","fail");
        MyToast.showToast(MainActivity.this,"网络错误",exception.getMessage());


    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> paramers = new HashMap<String,String>();
        paramers.put("mobile",mEditPhone.getText().toString());
        paramers.put("password",mEditPassword.getText().toString());

            loginDao.getData(paramers);
    }
}
