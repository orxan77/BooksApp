package com.example.orxan77.booksapp;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by orxan77 on 10/16/17.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null)
            return null;
        List<Book> books = QueryUtils.featchEarthquakeData(mUrl);
        return books;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
