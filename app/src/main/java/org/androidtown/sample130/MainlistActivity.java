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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainlistActivity extends AppCompatActivity {

    //------------DATABASE 상수-------------

    public static final String DATABASE_NAME = "LiteratureDatabase.db";
    public static final int DATABASE_VERSION = 5;

    //------------logcat---------------------

    static final String LOG_TAG = "SlidingTabsBasicFragment";

    //-------------------리스트뷰 배열----------------------------------------------

    static final String[] genre_main = {"고대가요", "향가", "경기체가", "고려가요",
            "한시 - 상고시대", "한시 - 고려", "한시 - 조선 전기", "한시 - 조선 후기",
            "시조 - 고려", "시조 - 조선 전기", "시조 - 조선 후기",
            "연시조 - 조선 전기", "연시조 - 조선 후기", "사설시조",
            "가사 - 조선 전기", "가사 - 조선 후기",
            "악장"};
    static final String[] long_literature_list = {"한림별곡", "강호사시가", "고산구곡가", "도산십이곡",
            "관동별곡", "규원가", "면앙정가", "사미인곡"
            , "상춘곡", "속미인곡", "견회요", "농가월령가"
            , "누항사", "선상탄", "용부가", "일동장유가"
            , "화전가", "용비어천가"};

    static String[][] 제목 = new String[genre_main.length][];
    static String[][] 작가 = new String[genre_main.length][];

    String[] bookmark_data;

    Cursor cursor;

    ListView mainlist_listview;

    MyAdapterMain adapterMain;
    MyAdapterGenre adapterGenre;

    static public String Literature_Loading_Code = "";

    boolean isGenre = true;
    boolean isLongclicked = false;

    String temp_LongClick;









/*------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
 */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlist);

        TextView mainlistLogo_textview = (TextView)findViewById(R.id.mainlistLogo_textview);
        mainlistLogo_textview.setTypeface(SplashScreen.Fonts.NanumPen);


        MySQLiteOpenHelper helper;
        helper = new MySQLiteOpenHelper(getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();


//-------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------
        //---------------------------------------------------------------------------

        /*for (int i = 0; i < genre_main.length; i++) {

            String sql = "select name, author from "
                    + MySQLiteOpenHelper.DATABASE_TABLE_NAME
                    + " where genre = '"
                    + genre_main[i]
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
                제목[i] = temparray_name;
                작가[i] = temparray_author;
            }
        }*/


//--------------------------메인화면 장르리스트 화면에 넣기----------------------------------------

        myListViewAdapter_genre();

        mainlist_listview = (ListView) findViewById(R.id.mainlist_listview);

        mainlist_listview.setOnItemClickListener(onItemClickListener);

        mainlist_listview.setOnItemLongClickListener(onItemLongClickListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainlist, menu);
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

    public AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {

                    if (isGenre == true) {

                        // 리스트뷰의 아이템을 클릭하였고
                        // 만약 지금 보여주고 있는 목록이 '장르' 라면 실행

                        ItemGenre curItem = (ItemGenre) parent.getAdapter().getItem(position);
                        String nameOfList = curItem.getGenre();

                        // 클릭한 항목의 '장르'를 가져옴 (화면에 보이는 글임)

                        if (nameOfList == null) {

                            Toast.makeText(getApplicationContext(), "오류 발생", Toast.LENGTH_LONG);

                        } else {

                            for (int i = 0; i < 17; i++) {
                                if (nameOfList == genre_main[i]) {
                                    myListViewAdapter_main(i);
                                    break;
                                }
                            }

                        }

                    } else {

                        // 리스트뷰의 아이템을 클릭하였고
                        // 만약 지금 보여주고 있는 목록이 '메인'(작품 목록)이면 실행

                        ItemMain curItem = (ItemMain) parent.getAdapter().getItem(position);
                        String name = curItem.getName();

                        // 클릭한 항목의 '이름'을 가져옴 (화면에 보이는 글임)

                        if (name == "뒤로가기") {

                            myListViewAdapter_genre();
                            isGenre = true;

                            // 맨위의 '뒤로가기'를 누르면 장르를 보여주는 리스트뷰로 다시 변신

                        } else {

                            for (int i = 0; i < long_literature_list.length; i++) {
                                if (long_literature_list[i] == name) {
                                    Intent intent = new Intent(getApplicationContext(), ContentLongActivity.class);
                                    intent.putExtra("loading code", name);
                                    intent.putExtra("start from", "Mainlist");
                                    startActivity(intent);
                                    isGenre = true;
                                    finish();

                                    //만약 클릭한 작품이 '긴 작품'이면 ContentLongActivity 시작
                                }
                            }

                            Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                            intent.putExtra("loading code", name);
                            intent.putExtra("start from", "Mainlist");
                            startActivity(intent);
                            isGenre = true;
                            finish();

                            //그 외 일반작품들을 클릭하면 ContentActivit 시작
                        }

                    }
                }
            };

    public AdapterView.OnItemLongClickListener onItemLongClickListener =
            new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    if (isGenre == true) {

                        return false;
                        //만약 리스트뷰가 장르를 보여주고 있다면 그냥 아무것도 하지 않기

                    } else {

                        ItemMain curItem = (ItemMain) parent.getAdapter().getItem(position);
                        String name = curItem.getName();

                        if (name == "뒤로가기") {

                            return false;

                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainlistActivity.this, R.style.AlertDialog_AppCompat_Light));
                        temp_LongClick = name;

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

                                        db.update(MySQLiteOpenHelper.DATABASE_TABLE_NAME, values, "name='"+temp_LongClick+"'", null);

                                        Toast.makeText(getApplicationContext(), temp_LongClick + "이(가) 내 작품에 등록되었습니다.", Toast.LENGTH_LONG).show();
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

                        Toast.makeText(getApplicationContext(), "롱클릭됨", Toast.LENGTH_SHORT).show();

                        AlertDialog dialog = builder.create();    // 알림창 객체 생성
                        dialog.show();    // 알림창 띄우기
                        //내 작품 목록으로 넣을 지 알림창 만들기
                    }

                    return true;
                }
            };

    //----------------------------------장르 들어가면 각 제목 화면에 올리는 메소드----------------------------

    private void myListViewAdapter_main(int arrayNumber) {

        isGenre = false;
        adapterMain = new MyAdapterMain();
        mainlist_listview = (ListView) findViewById(R.id.mainlist_listview);

        for (int i = 0; i < 제목[arrayNumber].length; i++) {

            adapterMain.addItem(new ItemMain(제목[arrayNumber][i], 작가[arrayNumber][i]));

        }

        if (adapterMain != null) {

            mainlist_listview.setAdapter(adapterMain);

        } else {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }
    }

    //--------------------------------------장르 로딩하는 메소드-----------------------------------------------

    private void myListViewAdapter_genre() {

        adapterGenre = new MyAdapterGenre();
        mainlist_listview = (ListView) findViewById(R.id.mainlist_listview);

        for (int i = 0; i < genre_main.length; i++) {
            adapterGenre.addItem(new ItemGenre(genre_main[i]));
    }

        if (adapterGenre != null) {
            mainlist_listview.setAdapter(adapterGenre);

        } else {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }

    }

    //-----------------------------------뒤로가기 누르면 첫 화면으로 이동-----------------------------------------

    public void OnClickBackBtn(View v) {

        Intent intent = new Intent(getApplicationContext(),Startmenu.class);
        startActivity(intent);
        finish();

    }

    //----------------------------------리스트뷰어댑터--------------------------------------------------------

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

    //-------------------------------------------------------------------------------------------------

    class MyAdapterGenre extends BaseAdapter {

        ArrayList<ItemGenre> arrayList = new ArrayList<ItemGenre>();

        @Override
        public int getCount() {
            return arrayList.size();
        }


        public void addItem(ItemGenre item) {
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

            ItemGenre curItem = arrayList.get(position);
            ItemGenreView view = new ItemGenreView(getApplicationContext());

            view.setGenre(curItem.getGenre());


            return view;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Startmenu.class);
        startActivity(intent);
        finish();
    }

}
