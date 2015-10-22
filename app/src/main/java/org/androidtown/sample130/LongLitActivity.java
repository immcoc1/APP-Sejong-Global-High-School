package org.androidtown.sample130;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class LongLitActivity extends AppCompatActivity {

    MyAdapterMain adapterMain;
    ListView longlit_listview;
    private ViewFlipper viewFlipper;
    private float lastX;
    int line_number;
    String[] untranslated;
    String[] translated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_long_lit);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("loading code");

        viewFlipper = (ViewFlipper) findViewById(R.id.longlit_viewflipper);

        if (name == "면앙정가") {

        }

        line_number = untranslated.length;

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

    private void myListViewAdapter_main(int page) {

        adapterMain = new MyAdapterMain();
        longlit_listview = (ListView) findViewById(R.id.longlit_listview);

        for (int i = (page - 1) * 10; i < untranslated.length && i < (page * 10); i++) {

            adapterMain.addItem(new ItemLit(untranslated[i], translated[i]));
        }

        if (adapterMain != null) {

            longlit_listview.setAdapter(adapterMain);

        } else {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }

        //각 페이지 당 10개의 문장 씩 원문과 번역문을 보여줌
    }


    class MyAdapterMain extends BaseAdapter {

        ArrayList<ItemLit> arrayList = new ArrayList<ItemLit>();

        @Override
        public int getCount() {
            return arrayList.size();
        }


        public void addItem(ItemLit item) {
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

            ItemLit curItem = arrayList.get(position);
            ItemLitView view = new ItemLitView(getApplicationContext());

            view.setUntranslated(curItem.getUntranslated());
            view.setTranslated(curItem.getTranslated());

            return view;
        }

        //위의 리스트뷰를 적용시키기 위한 어댑터
    }

    public boolean onTouchEvent(MotionEvent touchevent) {

        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;

            case MotionEvent.ACTION_UP:

                float currentX = touchevent.getX();


                // Handling left to right screen swap.

                if (lastX < currentX) {

                    // If there aren't any other children, just break.

                    if (viewFlipper.getDisplayedChild() == 0) {
                        break;
                    }

                    // Next screen comes in from left.

                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);

                    // Current screen goes out from right.
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
                    // Display next screen.
                    viewFlipper.showNext();

                }


                // Handling right to left screen swap.

                if (lastX > currentX) {
                    // If there is a child (to the left), kust break.

                    if (viewFlipper.getDisplayedChild() == line_number / 10)

                        break;


                    // Next screen comes in from right.

                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);

                    // Current screen goes out from left.

                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                    // Display previous screen.
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}


