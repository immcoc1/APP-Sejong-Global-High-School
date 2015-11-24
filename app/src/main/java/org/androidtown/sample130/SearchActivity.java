package org.androidtown.sample130;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    static String[] 제목;
    static String[] 작가;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv = (ListView) findViewById(R.id.search_listview);
        inputSearch = (EditText) findViewById(R.id.searchtext);
        lv.setOnItemClickListener(onItemClickListener);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.list_item_name, 제목);
        lv.setAdapter(adapter);
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Startmenu.class);
        startActivity(intent);
        finish();
    }
    public AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l_position) {

                    // 리스트뷰의 아이템을 클릭하였고
                    // 만약 지금 보여주고 있는 목록이 '메인'(작품 목록)이면 실행

                    //ItemMain curItem = (ItemMain) parent.getAdapter().getItem(position);
                    //String name = curItem.getName();
                    String tempcurrent=(String)parent.getAdapter().getItem(position);
                    String gw = "(";

                    String current=tempcurrent.split(gw)[0];

                    // 클릭한 항목의 '이름'을 가져옴 (화면에 보이는 글임)


                    for (int i = 0; i < MainlistActivity.long_literature_list.length; i++) {
                        if (MainlistActivity.long_literature_list[i] == current) {
                            Intent intent = new Intent(getApplicationContext(), ContentLongActivity.class);
                            intent.putExtra("loading code", current);
                            intent.putExtra("start from", "Searchlist");
                            startActivity(intent);
                            finish();

                            //만약 클릭한 작품이 '긴 작품'이면 ContentLongActivity 시작
                        }
                    }

                    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                    intent.putExtra("loading code", current);
                    intent.putExtra("start from", "Searchlist");
                    startActivity(intent);
                    finish();
                    //그 외 일반작품들을 클릭하면 ContentActivit 시작
                }


            };

}


