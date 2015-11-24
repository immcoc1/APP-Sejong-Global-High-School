package org.androidtown.sample130;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class KSATListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ksatlist);

        TextView ksatlistLogo_textview = (TextView)findViewById(R.id.ksatlistLogo_textview);
        ksatlistLogo_textview.setTypeface(SplashScreen.Fonts.NanumPen);

    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Startmenu.class);
        startActivity(intent);
        finish();
    }

    public void OnClickBackBtn(View v) {

        Intent intent = new Intent(getApplicationContext(),Startmenu.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
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

}
