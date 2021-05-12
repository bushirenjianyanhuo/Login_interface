package com.example.logininterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logininterface.database.tool.MyLibrary;
import com.example.logininterface.registrationModification.Modification;
import com.example.logininterface.registrationModification.Registration;

public class LoginInterface extends AppCompatActivity {

    private MyLibrary deHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);

        deHelper = new MyLibrary(this, "UserStore.db", null, 1);
        TextView textView = findViewById(R.id.Unable);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginInterface.this, Registration.class);
                startActivity(intent);
            }
        });

        TextView textView1 = findViewById(R.id.register);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginInterface.this, Modification.class);
                startActivity(intent);
            }
        });
    }

    public void loginClicked(View view) {
        EditText account = findViewById(R.id.account_number);
        EditText password = findViewById(R.id.secret_code);
        String acCount = account.getText().toString();
        String passWord = password.getText().toString();

        if (login(acCount, passWord)) {

        } else if (login(null, null)){
            Toast.makeText(this, "请输入帐号密码", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean login(String acCount, String passWord) {
        SQLiteDatabase sqLiteOpenHelper = deHelper.getWritableDatabase();
        String sql = "select * from userInformation where accounts = ? and password = ? ";
        Cursor cursor = sqLiteOpenHelper.rawQuery(sql, new String[] {acCount, passWord});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}