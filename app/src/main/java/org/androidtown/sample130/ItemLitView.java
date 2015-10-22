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
public class ItemLitView extends LinearLayout {

    TextView list_item_untranslated;
    TextView list_item_translated;

    Typeface NanumGothic = Typeface.createFromAsset(getContext().getAssets(), "fonts/NanumBarunGothic.ttf");


    public ItemLitView(Context context) {
        super(context);
        init(context);
    }

    public ItemLitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

        list_item_untranslated = (TextView) findViewById(R.id.list_item_untranslated);
        list_item_translated = (TextView) findViewById(R.id.list_item_translated);

        list_item_untranslated.setTypeface(NanumGothic);
        list_item_translated.setTypeface(NanumGothic);
    }

    public void setUntranslated(String name) {
        list_item_untranslated.setText(name);
    }

    public void setTranslated(String author) {
        list_item_translated.setText(author);
    }
}
