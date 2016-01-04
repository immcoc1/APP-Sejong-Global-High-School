package org.androidtown.sample130;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContentActivity extends AppCompatActivity {

    String fromWhere;
    Intent getIntent;
    String name;

    //------------DATABASE 상수-------------

    public static final String DATABASE_NAME = "LiteratureDatabase.db";
    public static final int DATABASE_VERSION = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent = getIntent();
        name = getIntent.getExtras().getString("loading code");

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
            mySetText(R.string.정읍사_main, R.string.정읍사_author, R.string.정읍사_genre, R.string.정읍사_epoch, R.string.정읍사_topic, R.string.정읍사_characteristic, R.string.정읍사_detail);
        } else if (name.equals("해가")){
            mySetText(R.string.해가_main, R.string.해가_author, R.string.해가_genre, R.string.해가_epoch, R.string.해가_topic, R.string.해가_characteristic, R.string.해가_detail);
        }else if(name.equals("황조가")){
            mySetText(R.string.황조가_main, R.string.황조가_author, R.string.황조가_genre, R.string.황조가_epoch, R.string.황조가_topic, R.string.황조가_characteristic, R.string.황조가_detail);
        }else if(name.equals("도솔가")){
            mySetText(R.string.도솔가_main, R.string.도솔가_author, R.string.도솔가_genre, R.string.도솔가_epoch, R.string.도솔가_topic, R.string.도솔가_characteristic, R.string.도솔가_detail);
        }else if(name.equals("도천수대비가")){
            mySetText(R.string.도천수대비가_main, R.string.도천수대비가_author, R.string.도천수대비가_genre, R.string.도천수대비가_epoch, R.string.도천수대비가_topic, R.string.도천수대비가_characteristic, R.string.도천수대비가_detail);
        }else if(name.equals("모죽지랑가")){
            mySetText(R.string.도천수대비가_main, R.string.도천수대비가_author, R.string.도천수대비가_genre, R.string.도천수대비가_epoch, R.string.도천수대비가_topic, R.string.도천수대비가_characteristic, R.string.도천수대비가_detail);
        }else {
            RelativeLayout notYet = (RelativeLayout) findViewById(R.id.content_notYet);
            notYet.setVisibility(View.VISIBLE);
        }
        MySQLiteOpenHelper helper;
        helper = new MySQLiteOpenHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();


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
        backward();
    }
    public void OnClickSatBtn(View v) {

        Toast.makeText(getApplicationContext(), "죄송합니다. 준비중입니다.", Toast.LENGTH_SHORT).show();
    }

    public void OnClickBookmarkBtn(View v) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(ContentActivity.this, R.style.AlertDialog_AppCompat_Light));

        // 여기서 부터는 알림창의 속성 설정

        builder.setTitle("북마크")        // 제목 설정

                .setMessage(name + "을(를) 내 작품에 등록하시겠습니까?")        // 메세지 설정

                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    // 확인 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton) {

                        ContentValues values = new ContentValues();
                        values.put("isBookmarked", 1);
                        MySQLiteOpenHelper helper;
                        helper = new MySQLiteOpenHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
                        SQLiteDatabase db = helper.getWritableDatabase();

                        db.update(MySQLiteOpenHelper.DATABASE_TABLE_NAME, values, "name='" + name + "'", null);

                        Toast.makeText(getApplicationContext(), name + "이(가) 내 작품에 등록되었습니다.", Toast.LENGTH_LONG).show();
                                       /* String sql = "select isBookmarked from "
                                                + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                                                + " where name = '"
                                                + temp_LongClick
                                                + "'";*/
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    // 취소 버튼 클릭시 설정

                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.cancel();

                    }

                });

        android.support.v7.app.AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
        //내 작품 목록으로 넣을 지 알림창 만들기
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
        backward();
    }

    public void backward(){
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
        else if (fromWhere.equals("Searchlist")){
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
