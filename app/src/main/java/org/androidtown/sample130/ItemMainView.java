package org.androidtown.sample130;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2015-08-21.
 */
public class ItemMainView extends LinearLayout {

    TextView list_item_name;
    TextView list_item_author;



    public ItemMainView(Context context) {
        super(context);
        init(context);
    }

    public ItemMainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

        list_item_name = (TextView) findViewById(R.id.list_item_name);
        list_item_author = (TextView) findViewById(R.id.list_item_author);

        list_item_author.setTypeface(SplashScreen.Fonts.NanumGothic);
        list_item_name.setTypeface(SplashScreen.Fonts.NanumGothic);
    }

    public void setName(String name) {
        list_item_name.setText(name);
    }

    public void setAuthor(String author) {
        list_item_author.setText(author);
    }
}
