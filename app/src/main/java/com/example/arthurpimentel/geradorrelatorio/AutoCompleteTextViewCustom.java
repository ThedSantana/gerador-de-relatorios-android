package com.example.arthurpimentel.geradorrelatorio;

/**
 * Created by arthur.pimentel on 03/06/2016.
 */

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

/**
 * Created by kellylyra on 05/05/2016.
 */
public class AutoCompleteTextViewCustom extends AutoCompleteTextView {
    private static final String LOG_ARG = "SpinnerAutoComplete";
    private boolean mIsAberto = false;

    public AutoCompleteTextViewCustom(Context context) {
        super(context);
        inicializarComponentes();
    }

    public AutoCompleteTextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializarComponentes();
    }

    public AutoCompleteTextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        this.setHeight(60
        );

        this.setThreshold(1);

        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIsAberto = false;
            }
        });
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            performFiltering(getText(), 0);
        }
    }

    @Override
    public void performCompletion() {
        super.performCompletion();
        mIsAberto=true;
    }

    @Override
    public boolean performClick() {
        if (mIsAberto)
            dismissDropDown();
        else
            showDropDown();

        mIsAberto = !mIsAberto;

        return super.performClick();
    }


}
