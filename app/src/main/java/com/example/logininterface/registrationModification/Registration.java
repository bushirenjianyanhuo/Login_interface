package com.example.logininterface.registrationModification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logininterface.R;
import com.example.logininterface.database.tool.MyLibrary;

public class Registration extends AppCompatActivity {

    private MyLibrary myLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myLibrary = new MyLibrary(this, "UserStore.db", null, 1);
    }


    public void logon(View view) {
        SQLiteDatabase db = myLibrary.getWritableDatabase();

        EditText editText = findViewById(R.id.accounts);
        EditText editText2 = findViewById(R.id.password);

        String number = editText.getText().toString();
        String password = editText2.getText().toString();

        if (CheckIsDataAlreadyInDBorNot(number)) {
            Toast.makeText(this, "该用户名已被注册，注册失败", Toast.LENGTH_SHORT).show();
        } else {

            if (register(number, password)) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();
    }

    /*
        向数据库插入数据
     */
    public boolean register(String accounts, String password) {
        SQLiteDatabase db = myLibrary.getWritableDatabase();
        String sql = "insert into userInformation(accounts, password)values(?,?)";
        Object obj[] = {accounts, password};
        db.execSQL(sql, obj);
        ContentValues values = new ContentValues();
        values.put("number", accounts);
        values.put("password", password);
        db.insert("userInformation", null, values);
        db.execSQL("insert into userInformation (accounts,password) values (?,?)", new String[]{accounts, password});
        db.close();
        return true;
    }

    /*
        检验用户名是否已存在
     */
    public boolean CheckIsDataAlreadyInDBorNot(String value) {
        SQLiteDatabase db = myLibrary.getWritableDatabase();
        String Query = "Select * from userInformation where accounts = ?";
        Cursor cursor = db.rawQuery(Query, new String[]{value});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        db.close();
        cursor.close();
        return false;
    }

}