package com.example.lab_2_kochnev;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout rootLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView my_text = (TextView)findViewById(R.id.my_text);
        my_text.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/PenguinAttackCyrillic.otf"));

        rootLinearLayout = (LinearLayout)findViewById(R.id.root);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void createAlertDialog(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_content)

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public void createTextView(){
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText(R.string.text_view_text);

        textView1.setTypeface(Typeface.createFromAsset(
                getAssets(), "fonts/PenguinAttackCyrillic.otf"));

        textView1.setBackgroundColor(0xff66ff66); // hex color 0xAARRGGBB
        textView1.setPadding(20, 20, 20, 20);// in pixels (left, top, right, bottom)
        rootLinearLayout.addView(textView1);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final LinearLayout mylayout =
                (LinearLayout)findViewById(R.id.root);
        switch (item.getItemId()) {
            case R.id.red:

                mylayout.setBackgroundColor(getResources().getColor(R.color.red));
                return true;
            case R.id.green:

                mylayout.setBackgroundColor(getResources().getColor(R.color.green));
                return true;
            case R.id.blue:

                mylayout.setBackgroundColor(getResources().getColor(R.color.blue));
                return true;
            case R.id.exit:
                finish();
                return true;

            case R.id.create_text:
                createTextView();
                return true;

            case R.id.alert:
                createAlertDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
