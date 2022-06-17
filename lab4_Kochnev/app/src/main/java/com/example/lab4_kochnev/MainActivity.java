package com.example.lab4_kochnev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final int CALL_PHONE_PERMISSION_CODE = 100;
    private static final String PHONE_TO_CALL = "+79670967121";

    ListView lifeCycleListView;
    TextView lifeCycleTextView;
    // Data to display in lifeCycleListView
    ArrayList<String> lifeCycles =  new ArrayList<String>();
    ArrayAdapter lifeCyclesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lifeCycleListView = findViewById(R.id.listview_1);
        lifeCycleTextView = findViewById(R.id.textView_life);

        lifeCycles.add("onCreate");

        lifeCyclesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                lifeCycles
        );

        lifeCycleListView.setAdapter(lifeCyclesAdapter);

    }

    @Override
    protected void onStart() {
        lifeCycles.add("onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        lifeCycles.add("onResume");

        String str = String.join(",", lifeCycles);
        lifeCycleTextView.setText(str);
        lifeCycleListView.setAdapter(lifeCyclesAdapter);

        super.onResume();
    }

    @Override
    protected void onPause() {
        lifeCycles.add("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifeCycles.add("onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        lifeCycles.add("onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        lifeCycles.add("onDestroy");
        super.onDestroy();
    }

    public void callPhone() {
        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + PHONE_TO_CALL));
        startActivity(dialIntent);
    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    { permission }, requestCode);
        }
        else {
            callPhone();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,"Разрешение предоставлено",Toast.LENGTH_SHORT).show();
                callPhone();
            }
            else {
                Toast.makeText(MainActivity.this,"Разрешение отклонено",Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void handleCallPhoneBtnClick(View view) {
        checkPermission(Manifest.permission.CALL_PHONE, CALL_PHONE_PERMISSION_CODE);
    }

    public void handleContactsClick(View view) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}