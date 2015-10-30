package org.androidtown.sample130;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mylist extends AppCompatActivity {

    ListView mylist_listview;

    Cursor cursor;

    String[] 제목;
    String[] 작가;

    String temp_LongClick;
    String test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylist);


        MySQLiteOpenHelper helper;
        helper = new MySQLiteOpenHelper(getApplicationContext(), Mainlist.DATABASE_NAME, null, Mainlist.DATABASE_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();


        String sql = "select name, author from "
                + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                + " where isBookmarked = 1";
        cursor = db.rawQuery(sql, null);


        if (cursor != null) {
            int count = cursor.getCount();
            String[] temparray_name = new String[count];
            String[] temparray_author = new String[count];

            for (int j = 0; j < count; j++) {
                cursor.moveToNext();
                temparray_name[j] = cursor.getString(0);
                temparray_author[j] = cursor.getString(1);
            }
            제목 = temparray_name;
            작가 = temparray_author;
            if (제목.length != 0) {
                TextView noListText1 = (TextView) findViewById(R.id.mylist_noListText1);
                TextView noListText2 = (TextView) findViewById(R.id.mylist_noListText2);
                ImageView noListImage = (ImageView) findViewById(R.id.mylist_noListImage);

                noListText1.setVisibility(View.GONE);
                noListText2.setVisibility(View.GONE);
                noListImage.setVisibility(View.GONE);

            } else {
                TextView noListText1 = (TextView) findViewById(R.id.mylist_noListText1);
                TextView noListText2 = (TextView) findViewById(R.id.mylist_noListText2);
                ImageView noListImage = (ImageView) findViewById(R.id.mylist_noListImage);

                noListText1.setVisibility(View.VISIBLE);
                noListText2.setVisibility(View.VISIBLE);
                noListImage.setVisibility(View.VISIBLE);
            }
        } else {

            Toast.makeText(getApplicationContext(), "null cursor", Toast.LENGTH_LONG).show();
        }

        for (int i = 0; i < 제목.length; i++) {
            test += 제목[i];
        }
        Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();


        myListViewAdapter_main();

        mylist_listview = (ListView) findViewById(R.id.mylist_listview);
        mylist_listview.setOnItemClickListener(onItemClickListener);
        mylist_listview.setOnItemLongClickListener(onItemLongClickListener);

    }

    public AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {

                    // 리스트뷰의 아이템을 클릭하였고
                    // 만약 지금 보여주고 있는 목록이 '메인'(작품 목록)이면 실행

                    ItemMain curItem = (ItemMain) parent.getAdapter().getItem(position);
                    String name = curItem.getName();

                    // 클릭한 항목의 '이름'을 가져옴 (화면에 보이는 글임)


                    for (int i = 0; i < Mainlist.long_literature_list.length; i++) {
                        if (Mainlist.long_literature_list[i] == name) {
                            Intent intent = new Intent(getApplicationContext(), ContentLongActivity.class);
                            intent.putExtra("loading code", name);
                            intent.putExtra("start from", "Mylist");
                            startActivity(intent);
                            finish();

                            //만약 클릭한 작품이 '긴 작품'이면 ContentLongActivity 시작
                        }
                    }

                    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                    intent.putExtra("loading code", name);
                    intent.putExtra("start from", "Mylist");
                    startActivity(intent);
                    finish();
                    //그 외 일반작품들을 클릭하면 ContentActivit 시작
                }


            };

    public AdapterView.OnItemLongClickListener onItemLongClickListener =
            new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                    ItemMain curItem = (ItemMain) parent.getAdapter().getItem(position);
                    String name = curItem.getName();

                    if (name == "뒤로가기") {

                        return false;

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Mylist.this, R.style.AlertDialog_AppCompat_Light));
                    temp_LongClick = name;

                    // 여기서 부터는 알림창의 속성 설정

                    builder.setTitle("북마크")        // 제목 설정

                            .setMessage(name + "Would you put this in the bookmark?")        // 메세지 설정

                            .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정

                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                // 확인 버튼 클릭시 설정

                                public void onClick(DialogInterface dialog, int whichButton) {

                                    ContentValues values = new ContentValues();
                                    values.put("isBookmarked", 0);
                                    MySQLiteOpenHelper helper;
                                    helper = new MySQLiteOpenHelper(getApplicationContext(), Mainlist.DATABASE_NAME, null, Mainlist.DATABASE_VERSION);
                                    SQLiteDatabase db = helper.getWritableDatabase();

                                    db.update(MySQLiteOpenHelper.DATABASE_TABLE_NAME, values, "name='" + temp_LongClick + "'", null);

                                    Toast.makeText(getApplicationContext(), temp_LongClick + "이(가) 내 작품에서 제외되었습니다.", Toast.LENGTH_LONG).show();
                                       /* String sql = "select isBookmarked from "
                                                + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                                                + " where name = '"
                                                + temp_LongClick
                                                + "'";*/

                                    String sql = "select name, author from "
                                            + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                                            + " where isBookmarked = 1";
                                    cursor = db.rawQuery(sql, null);

                                    if (cursor != null) {
                                        int count = cursor.getCount();
                                        String[] temparray_name = new String[count];
                                        String[] temparray_author = new String[count];

                                        for (int j = 1; j <= count; j++) {
                                            temparray_name[j] = cursor.getString(0);
                                            temparray_author[j] = cursor.getString(1);
                                            cursor.moveToNext();
                                        }
                                        제목 = temparray_name;
                                        작가 = temparray_author;
                                        if (제목.length != 0) {
                                            TextView noListText1 = (TextView) findViewById(R.id.mylist_noListText1);
                                            TextView noListText2 = (TextView) findViewById(R.id.mylist_noListText2);
                                            ImageView noListImage = (ImageView) findViewById(R.id.mylist_noListImage);

                                            noListText1.setVisibility(View.GONE);
                                            noListText2.setVisibility(View.GONE);
                                            noListImage.setVisibility(View.GONE);
                                        } else {
                                            TextView noListText1 = (TextView) findViewById(R.id.mylist_noListText1);
                                            TextView noListText2 = (TextView) findViewById(R.id.mylist_noListText2);
                                            ImageView noListImage = (ImageView) findViewById(R.id.mylist_noListImage);

                                            noListText1.setVisibility(View.VISIBLE);
                                            noListText2.setVisibility(View.VISIBLE);
                                            noListImage.setVisibility(View.VISIBLE);
                                        }
                                    }


                                    myListViewAdapter_main();

                                }
                            })

                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                // 취소 버튼 클릭시 설정

                                public void onClick(DialogInterface dialog, int whichButton) {

                                    dialog.cancel();

                                }

                            });

                    Toast.makeText(getApplicationContext(), "롱클릭됨", Toast.LENGTH_SHORT).show();

                    AlertDialog dialog = builder.create();    // 알림창 객체 생성
                    dialog.show();    // 알림창 띄우기
                    //내 작품 목록으로 넣을 지 알림창 만들기


                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mylist, menu);
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

    private void myListViewAdapter_main() {

        mylist_listview = (ListView) findViewById(R.id.mylist_listview);

        MyAdapterMain adapterMain = new MyAdapterMain();

        for (int i = 0; i < 제목.length; i++) {

            adapterMain.addItem(new ItemMain(제목[i], 작가[i]));

        }

        if (adapterMain != null) {

            mylist_listview.setAdapter(adapterMain);

        } else {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }
    }

    class MyAdapterMain extends BaseAdapter {

        ArrayList<ItemMain> arrayList = new ArrayList<ItemMain>();

        @Override
        public int getCount() {
            return arrayList.size();
        }


        public void addItem(ItemMain item) {
            arrayList.add(item);
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ItemMain curItem = arrayList.get(position);
            ItemMainView view = new ItemMainView(getApplicationContext());

            view.setAuthor(curItem.getAuthor());
            view.setName(curItem.getName());

            return view;
        }
    }
}


