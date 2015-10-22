package org.androidtown.sample130;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2015-08-21.
 */
public class ItemGenreView extends LinearLayout {

    TextView list_item_genre;
    Typeface NanumGothic = Typeface.createFromAsset(getContext().getAssets(), "fonts/NanumBarunGothic.ttf");

    public ItemGenreView(Context context) {
        super(context);
        init(context);
    }

    public ItemGenreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item2, this, true);

        list_item_genre = (TextView) findViewById(R.id.list_item_genre);
        list_item_genre.setTypeface(NanumGothic);

    }

    public void setGenre(String genre) {
        list_item_genre.setText(genre);
    }

}
