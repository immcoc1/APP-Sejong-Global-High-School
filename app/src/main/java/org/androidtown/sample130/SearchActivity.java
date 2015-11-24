package org.androidtown.sample130;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;

    static String[] 제목;
    static String[] 작가;
    MyAdapterMain adapterMain;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv = (ListView) findViewById(R.id.search_listview);
        inputSearch = (EditText) findViewById(R.id.searchtext);
        lv.setOnItemClickListener(onItemClickListener);

        // Adding items to listview
        //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.list_item_name, 제목);
        /*adapterMain = new MyAdapterMain();

        lv.setAdapter(adapterMain);*/

        myListViewAdapter_main();
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                SearchActivity.this.adapterMain.getFilter().filter(cs);
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
                    String name=(String)parent.getAdapter().getItem(position);
                    //String gw = "(";

                    //String current=tempcurrent.split(gw)[0];

                    // 클릭한 항목의 '이름'을 가져옴 (화면에 보이는 글임)


                    for (int i = 0; i < MainlistActivity.long_literature_list.length; i++) {
                        if (MainlistActivity.long_literature_list[i] == name) {
                            Intent intent = new Intent(getApplicationContext(), ContentLongActivity.class);
                            intent.putExtra("loading code", name);
                            intent.putExtra("start from", "Searchlist");
                            startActivity(intent);
                            finish();

                            //만약 클릭한 작품이 '긴 작품'이면 ContentLongActivity 시작
                        }
                    }

                    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                    intent.putExtra("loading code", name);
                    intent.putExtra("start from", "Searchlist");
                    startActivity(intent);
                    finish();
                    //그 외 일반작품들을 클릭하면 ContentActivit 시작
                }


            };

    private void myListViewAdapter_main() {

        adapterMain = new MyAdapterMain();

        for (int i = 0; i < 제목.length; i++) {

            adapterMain.addItem(new ItemMain(제목[i], 작가[i]));

        }

        if (adapterMain != null) {

            lv.setAdapter(adapterMain);

        } else {

        }
    }

    class MyAdapterMain extends BaseAdapter implements Filterable {

        ArrayList<ItemMain> arrayList = new ArrayList<ItemMain>();

        private List<ItemMain> mObjects;

        /**
         * Lock used to modify the content of {@link #mObjects}. Any write operation
         * performed on the array should be synchronized on this lock. This lock is also
         * used by the filter (see {@link #getFilter()} to make a synchronized copy of
         * the original array of data.
         */
        private final Object mLock = new Object();

        /**
         * The resource indicating what views to inflate to display the content of this
         * array adapter.
         */
        private int mResource;

        /**
         * The resource indicating what views to inflate to display the content of this
         * array adapter in a drop down widget.
         */
        private int mDropDownResource;

        /**
         * If the inflated resource is not a TextView, {@link #mFieldId} is used to find
         * a TextView inside the inflated views hierarchy. This field must contain the
         * identifier that matches the one defined in the resource file.
         */
        private int mFieldId = 0;

        /**
         * Indicates whether or not {@link #notifyDataSetChanged()} must be called whenever
         * {@link #mObjects} is modified.
         */
        private boolean mNotifyOnChange = true;

        private Context mContext;

        // A copy of the original mObjects array, initialized from and then used instead as soon as
        // the mFilter ArrayFilter is used. mObjects will then only contain the filtered values.
        private ArrayFilter mFilter;

        private LayoutInflater mInflater;

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


        @Override
        public Filter getFilter() {
            if (mFilter == null) {
                mFilter = new ArrayFilter();
                Log.e("Hmm","null, cleared");
            }
            Log.e("Hmm","well");
            return mFilter;
        }

        private class ArrayFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence prefix) {
                FilterResults results = new FilterResults();

                if (arrayList == null) {
                    synchronized (mLock) {
                        arrayList = new ArrayList<ItemMain>(mObjects);
                    }
                }

                if (prefix == null || prefix.length() == 0) {
                    ArrayList<ItemMain> list;
                    synchronized (mLock) {
                        list = new ArrayList<ItemMain>(arrayList);
                    }
                    results.values = list;
                    results.count = list.size();
                } else {
                    String prefixString = prefix.toString().toLowerCase();

                    ArrayList<ItemMain> values;
                    synchronized (mLock) {
                        values = new ArrayList<ItemMain>(arrayList);
                    }

                    final int count = values.size();
                    final ArrayList<ItemMain> newValues = new ArrayList<ItemMain>();

                    for (int i = 0; i < count; i++) {
                        final ItemMain value = values.get(i);
                        final String valueText = value.getName().toLowerCase();

                        // First match against the whole, non-splitted value
                        if (valueText.startsWith(prefixString)) {
                            newValues.add(value);
                            Log.e("Hmm","pass");
                        } else {
                            final String[] words = valueText.split(" ");
                            final int wordCount = words.length;
                            Log.e("Hmm","fail");

                            // Start at index 0, in case valueText starts with space(s)
                            for (int k = 0; k < wordCount; k++) {
                                if (words[k].startsWith(prefixString)) {
                                    newValues.add(value);
                                    break;
                                }
                            }
                        }
                    }

                    results.values = newValues;
                    results.count = newValues.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
                arrayList = (ArrayList<ItemMain>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        }
    }





}


