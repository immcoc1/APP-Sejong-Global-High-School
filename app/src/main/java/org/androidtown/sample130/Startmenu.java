package org.androidtown.sample130;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Startmenu extends AppCompatActivity {

    //public static final int MAINLIST_INT = 1001;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenu);


        Button mainlist_button = (Button) findViewById(R.id.mainlistEnter_button);
        Button mylist_button = (Button) findViewById(R.id.mylistEnter_button);
        Button sunneunglist_button = (Button) findViewById(R.id.ksatEnter_button);
        TextView startmenuLogo_textview = (TextView)findViewById(R.id.startmenuLogo_textview);

        mainlist_button.setTypeface(SplashScreen.Fonts.NanumPen);
        mylist_button.setTypeface(SplashScreen.Fonts.NanumPen);
        sunneunglist_button.setTypeface(SplashScreen.Fonts.NanumPen);
        startmenuLogo_textview.setTypeface(SplashScreen.Fonts.NanumPen);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample130, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void mainlist_button_onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), MainlistActivity.class);
        startActivity(intent);
        finish();

    }

    public void mylist_button_onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), MylistActivity.class);
        startActivity(intent);
        finish();

    }

    public void KSATlist_button_onClick(View v){
        Intent intent = new Intent(getApplicationContext(), KSATListActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("앱을 종료하시겠습니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

