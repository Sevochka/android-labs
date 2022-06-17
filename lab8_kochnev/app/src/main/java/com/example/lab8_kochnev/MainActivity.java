package com.example.lab8_kochnev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener{

    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 101;

    Button buttonDel, buttonClear, buttonShow, buttonAdd;
    EditText ET_name, ET_phone;
    CalendarView calendar;
    TextView text;
    DatabaseHelper DatabaseHelper;
    String currentDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonShow = (Button)findViewById(R.id.buttonShow);
        buttonShow.setOnClickListener(this);
        buttonDel = (Button)findViewById(R.id.buttonDel);
        buttonDel.setOnClickListener(this);
        buttonClear = (Button)findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(this);
        ET_name = (EditText)findViewById(R.id.ET_name);
        ET_phone = (EditText)findViewById(R.id.ET_phone);
        text = (TextView)findViewById(R.id.text);
        calendar = (CalendarView)findViewById(R.id.calendarView);
        DatabaseHelper = new DatabaseHelper(this);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                currentDate = Year+"/"+Month+"/"+curDate;
            }
        });
    }
    @Override
    public void onClick(View v) {
        String input_name = ET_name.getText().toString();
        String input_phone = ET_phone.getText().toString();
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        switch (v.getId()) {
            case R.id.buttonShow:
                text.setText("");
                String[] projection = {
                        DBContract.DBEntry.COLUMN_NAME_NAME,
                        DBContract.DBEntry.COLUMN_NAME_PHONE,
                        DBContract.DBEntry.COLUMN_NAME_DATE
                };
                Cursor cursor = db.query(
                        DBContract.DBEntry.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                int index_name =
                        cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_NAME);
                int index_phone =
                        cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_PHONE);
                int index_date =
                        cursor.getColumnIndex(DBContract.DBEntry.COLUMN_NAME_DATE);

                while (cursor.moveToNext()) {
                    String value_name = cursor.getString(index_name);
                    String value_phone = cursor.getString(index_phone);
                    String value_date = cursor.getString(index_date);
                    text.append(value_date + "\n" + value_name + "\n" +
                            value_phone + "\n" + "-----------------" + "\n");
                }
                cursor.close();
                break;
            case R.id.buttonAdd:
                values.put(DBContract.DBEntry.COLUMN_NAME_NAME,
                        input_name);
                values.put(DBContract.DBEntry.COLUMN_NAME_PHONE,
                        input_phone);
                values.put(DBContract.DBEntry.COLUMN_NAME_DATE,
                        currentDate);
                db.insert(DBContract.DBEntry.TABLE_NAME, null, values);
                buttonShow.callOnClick();
                break;
            case R.id.buttonDel:
                String selection = DBContract.DBEntry.COLUMN_NAME_NAME +
                        "=?";
                String[] selectionArgs = {input_name};
                db.delete(DBContract.DBEntry.TABLE_NAME, selection,
                        selectionArgs);
                buttonShow.callOnClick();
                break;
            case R.id.buttonClear:
                db.delete(DBContract.DBEntry.TABLE_NAME, null, null);
                buttonShow.callOnClick();
                break;
        }
        DatabaseHelper.close();
    }
}
