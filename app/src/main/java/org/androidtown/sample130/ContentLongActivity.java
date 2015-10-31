package org.androidtown.sample130;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContentLongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("loading code");

        setContentView(R.layout.activity_content_long);

        TextView logoName_textview = (TextView) findViewById(R.id.mainlist_logo_name);

        Button questionBtn_button = (Button) findViewById(R.id.questionBtn_content);

        logoName_textview.setText(name);

        //----------------------------고대가요---------------------------------------------

        //----------------------------------------------------------------------

        if (name.equals("면앙정가")) {
            mySetText( R.string.면앙정가_author, R.string.면앙정가_genre, R.string.면앙정가_epoch, R.string.면앙정가_topic, R.string.면앙정가_characteristic);
        } else {

        }
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

    public void OnClickBackBtn(View v) {
        finish();
    }

    private void mySetText(int author, int genre, int epoch, int topic, int characteristic) {

        TextView author_textview = (TextView) findViewById(R.id.author_content);
        TextView genre_textview = (TextView) findViewById(R.id.genre_content);
        TextView epoch_textview = (TextView) findViewById(R.id.epoch_content);
        TextView topic_textview = (TextView) findViewById(R.id.topic_content);
        TextView characteristic_textview = (TextView) findViewById(R.id.characteristic_content);

        author_textview.setText(author);
        genre_textview.setText(genre);
        epoch_textview.setText(epoch);
        topic_textview.setText(topic);
        characteristic_textview.setText(characteristic);

        Typeface NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");

        author_textview.setTypeface(NanumGothic);
        genre_textview.setTypeface(NanumGothic);
        epoch_textview.setTypeface(NanumGothic);
        topic_textview.setTypeface(NanumGothic);
        characteristic_textview.setTypeface(NanumGothic);

    }

    private void mySetText(int author, int genre, int epoch, int topic, int characteristic, int detail) {

        TextView author_textview = (TextView) findViewById(R.id.author_content);
        TextView genre_textview = (TextView) findViewById(R.id.genre_content);
        TextView epoch_textview = (TextView) findViewById(R.id.epoch_content);
        TextView topic_textview = (TextView) findViewById(R.id.topic_content);
        TextView characteristic_textview = (TextView) findViewById(R.id.characteristic_content);
        TextView detail_textview = (TextView) findViewById(R.id.detail_content);

        author_textview.setText(author);
        genre_textview.setText(genre);
        epoch_textview.setText(epoch);
        topic_textview.setText(topic);
        characteristic_textview.setText(characteristic);
        detail_textview.setText(detail);

        Typeface NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");

        author_textview.setTypeface(NanumGothic);
        genre_textview.setTypeface(NanumGothic);
        epoch_textview.setTypeface(NanumGothic);
        topic_textview.setTypeface(NanumGothic);
        characteristic_textview.setTypeface(NanumGothic);
        detail_textview.setTypeface(NanumGothic);

    }

    public void OnClickMainLiterature(View v){
        Intent intent = getIntent();
        String name = intent.getExtras().getString("loading code");

        Intent intent2 = new Intent(getApplicationContext(), LongLitActivity.class);
        intent2.putExtra("loading code", name);
        startActivity(intent2);
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
