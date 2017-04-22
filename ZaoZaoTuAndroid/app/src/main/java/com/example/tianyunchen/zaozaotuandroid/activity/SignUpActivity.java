package com.example.tianyunchen.zaozaotuandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tianyunchen.zaozaotuandroid.R;
import com.example.tianyunchen.zaozaotuandroid.dao.BaseDao;
import com.example.tianyunchen.zaozaotuandroid.dao.listener.DaoListener;
import com.example.tianyunchen.zaozaotuandroid.dao.RegisterDao;
import com.example.tianyunchen.zaozaotuandroid.http.HttpException;

import java.util.HashMap;

/**
 * Created by tianyunchen on 3/2/17.
 */

public class SignUpActivity extends BaseActivity implements DaoListener{
    private EditText edt_name,edt_mobile,edt_password,edt_sex;
    private Button btn_signUp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }

    @Override
    protected void initViews() {
        edt_name = (EditText)findViewById(R.id.edt_name);
        edt_mobile =(EditText)findViewById(R.id.edt_mobile);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_sex = (EditText)findViewById(R.id.edt_sex);
        btn_signUp= (Button)findViewById(R.id.btn_login);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> paramers = new HashMap<String,String>();
                paramers.put("name",edt_name.getText().toString());
                paramers.put("mobile",edt_mobile.getText().toString());
                paramers.put("sex",edt_sex.getText().toString());
                paramers.put("password",edt_password.getText().toString());
                RegisterDao registerDao = new RegisterDao();
                registerDao.setDaoLisenter(SignUpActivity.this);
                registerDao.getData(paramers);
            }
        });

    }

    @Override
    public void onSuccessful(BaseDao dao) {

    }

    @Override
    public void onFaild(HttpException exception) {

    }
}
