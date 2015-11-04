package org.androidtown.sample130;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Startmenu extends AppCompatActivity {

    //public static final int MAINLIST_INT = 1001;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenu);

        Typeface NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");

        Button mainlist_button = (Button) findViewById(R.id.mainlist_button);
        Button mylist_button = (Button) findViewById(R.id.mylist_button);
        Button sunneunglist_button = (Button) findViewById(R.id.KSATlist_button);

        mainlist_button.setTypeface(NanumGothic);
        mylist_button.setTypeface(NanumGothic);
        sunneunglist_button.setTypeface(NanumGothic);

        MySQLiteOpenHelper helper;
        helper = new MySQLiteOpenHelper(getApplicationContext(), Mainlist.DATABASE_NAME, null, Mainlist.DATABASE_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        for (int i = 0; i < Mainlist.genre_main.length; i++) {

            String sql = "select name, author from "
                    + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                    + " where genre = '"
                    + Mainlist.genre_main[i]
                    + "'";
            cursor = db.rawQuery(sql, null);

            if (cursor != null) {
                int count = cursor.getCount();
                String[] temparray_name = new String[count + 1];
                String[] temparray_author = new String[count + 1];
                temparray_name[0] = "뒤로가기";
                temparray_author[0] = "...";

                for (int j = 1; j <= count; j++) {
                    cursor.moveToNext();
                    temparray_name[j] = cursor.getString(0);
                    temparray_author[j] = cursor.getString(1);
                }
                Mainlist.제목[i] = temparray_name;
                Mainlist.작가[i] = temparray_author;
            }
        }

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

        Intent intent = new Intent(getApplicationContext(), Mainlist.class);
        startActivity(intent);
        finish();

    }

    public void mylist_button_onClick(View v) {

        Intent intent = new Intent(getApplicationContext(), Mylist.class);
        startActivity(intent);
        finish();

    }

    public void KSATlist_button_onClick(View v){
        Intent intent = new Intent(getApplicationContext(), KSATList.class);
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

