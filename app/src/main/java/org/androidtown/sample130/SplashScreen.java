package org.androidtown.sample130;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    String jakja=new String();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initializeTypeface();

        Cursor cursor, cursor2;

        TextView splash_screen_logo = (TextView)findViewById(R.id.splash_screen_logo);
        splash_screen_logo.setTypeface(Fonts.NanumGothic);

        MySQLiteOpenHelper helper;
        helper = new MySQLiteOpenHelper(getApplicationContext(), MainlistActivity.DATABASE_NAME, null, MainlistActivity.DATABASE_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();


        for (int i = 0; i < MainlistActivity.genre_main.length; i++) {

            String sql = "select name, author from "
                    + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                    + " where genre = '"
                    + MainlistActivity.genre_main[i]
                    + "'";
            String sql2="select name, author from "
                    + MySQLiteOpenHelper.DATABASE_TABLE_NAME;
            cursor = db.rawQuery(sql, null);
            cursor2= db.rawQuery(sql2,null);

            if(cursor2!=null){
                int count=cursor2.getCount();
                String[] temparray_name=new String[count];
                String[] temparray_author=new String[count];
                for(int j=0;j<count;j++){
                    cursor2.moveToNext();
                    temparray_name[j] = cursor2.getString(0);
                    temparray_author[j] = cursor2.getString(1);
                }
                SearchActivity.제목=temparray_name;
                SearchActivity.작가=temparray_author;
            }
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

                MainlistActivity.제목[i] = temparray_name;
                MainlistActivity.작가[i] = temparray_author;
            }
        }



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, Startmenu.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public static class Fonts {
        public static Typeface NanumGothic;
        public static Typeface NanumPen;
    }


    private void initializeTypeface() {
        Fonts.NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");
        Fonts.NanumPen = Typeface.createFromAsset(getAssets(), "fonts/NanumPen.ttf");
    }

}
