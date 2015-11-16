package org.androidtown.sample130;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContentActivity extends AppCompatActivity {

    String fromWhere;
    Intent getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent = getIntent();
        String name = getIntent.getExtras().getString("loading code");

        setContentView(R.layout.activity_content);

        TextView logoName_textview = (TextView) findViewById(R.id.ksatlistLogo_textview);


        TextView detailTexttview = (TextView) findViewById(R.id.detail_textview);
        Button questionBtnButton = (Button) findViewById(R.id.questionBtn_content);

        logoName_textview.setText(name);

        //----------------------------고대가요---------------------------------------------

        if (name.equals("공무도하가")) {
            mySetText(R.string.공무도하가_main, R.string.공무도하가_author, R.string.공무도하가_genre, R.string.공무도하가_epoch, R.string.공무도하가_topic, R.string.공무도하가_characteristic, R.string.공무도하가_detail);
        } else if (name.equals("구지가")){
            mySetText(R.string.구지가_main, R.string.구지가_author, R.string.구지가_genre, R.string.구지가_epoch, R.string.구지가_topic, R.string.구지가_characteristic, R.string.구지가_detail);
        } else if (name.equals("정읍사")){
            mySetText(R.string.정읍사_main, R.string.정읍사_author, R.string.정읍사_genre, R.string.정읍사_epoch, R.string.정읍사_topic, R.string.정읍사_characteristic, R.string.구지가_detail);
        } else {
            RelativeLayout notYet = (RelativeLayout) findViewById(R.id.content_notYet);
            notYet.setVisibility(View.VISIBLE);
        }


        //----------------------------------------------------------------------

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

        getIntent = getIntent();
        fromWhere = getIntent.getExtras().getString("start from");

        if(fromWhere.equals("Mainlist")) {
            Intent intent = new Intent(getApplicationContext(), MainlistActivity.class);
            startActivity(intent);
            finish();
        } else if (fromWhere.equals("Mylist")){
            Intent intent = new Intent(getApplicationContext(), MylistActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void mySetText(int main, int author, int genre, int epoch, int topic, int characteristic) {

        TextView main_textview = (TextView) findViewById(R.id.main_content);
        TextView author_textview = (TextView) findViewById(R.id.author_content);
        TextView genre_textview = (TextView) findViewById(R.id.genre_content);
        TextView epoch_textview = (TextView) findViewById(R.id.epoch_content);
        TextView topic_textview = (TextView) findViewById(R.id.topic_content);
        TextView characteristic_textview = (TextView) findViewById(R.id.characteristic_content);
        TextView detail_textview = (TextView)findViewById(R.id.detail_textview);

        main_textview.setText(main);
        author_textview.setText(author);
        genre_textview.setText(genre);
        epoch_textview.setText(epoch);
        topic_textview.setText(topic);
        characteristic_textview.setText(characteristic);

        Typeface NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");

        main_textview.setTypeface(NanumGothic);
        author_textview.setTypeface(NanumGothic);
        genre_textview.setTypeface(NanumGothic);
        epoch_textview.setTypeface(NanumGothic);
        topic_textview.setTypeface(NanumGothic);
        characteristic_textview.setTypeface(NanumGothic);


    }

    private void mySetText(int main, int author, int genre, int epoch, int topic, int characteristic, int detail) {

        TextView main_textview = (TextView) findViewById(R.id.main_content);
        TextView author_textview = (TextView) findViewById(R.id.author_content);
        TextView genre_textview = (TextView) findViewById(R.id.genre_content);
        TextView epoch_textview = (TextView) findViewById(R.id.epoch_content);
        TextView topic_textview = (TextView) findViewById(R.id.topic_content);
        TextView characteristic_textview = (TextView) findViewById(R.id.characteristic_content);
        TextView detail_textview = (TextView)findViewById(R.id.detail_textview);

        main_textview.setText(main);
        author_textview.setText(author);
        genre_textview.setText(genre);
        epoch_textview.setText(epoch);
        topic_textview.setText(topic);
        characteristic_textview.setText(characteristic);
        detail_textview.setText(detail);

        Typeface NanumGothic = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");

        main_textview.setTypeface(NanumGothic);
        author_textview.setTypeface(NanumGothic);
        genre_textview.setTypeface(NanumGothic);
        epoch_textview.setTypeface(NanumGothic);
        topic_textview.setTypeface(NanumGothic);
        characteristic_textview.setTypeface(NanumGothic);
        detail_textview.setTypeface(NanumGothic);


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
