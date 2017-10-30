package com.example.orxan77.booksapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    private int EARTHQUAKE_LOADER_ID = 1;

    private String BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private BookAdapter mAdapter;
    private ProgressBar spinner;
    private TextView emptyView;
    private TextView try_to_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        mAdapter = new BookAdapter(MainActivity.this, new ArrayList<Book>());
        ListView bookListView = (ListView) findViewById(R.id.list);
        emptyView = (TextView) findViewById(R.id.empty);
        spinner = (ProgressBar) findViewById(R.id.spinner);
        try_to_search = (TextView) findViewById(R.id.try_to_search);
        bookListView.setEmptyView(emptyView);
        bookListView.setAdapter(mAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getPreviewLink());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });


        Button buttonBook = (Button) findViewById(R.id.button_book);
        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchBook = (EditText) findViewById(R.id.search_book);
                String search_value = searchBook.getText().toString();
                BOOKS_URL = BOOKS_URL.concat(search_value);
                if (isConnected) {
                    LoaderManager loaderManager = getLoaderManager();
                    if (!search_value.isEmpty())
                        EARTHQUAKE_LOADER_ID = EARTHQUAKE_LOADER_ID + 1;
                    loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, MainActivity.this);
                    spinner.setVisibility(View.VISIBLE);
                } else {
                    spinner.setVisibility(View.INVISIBLE);
                    emptyView.setText(R.string.no_connection);
                }

            }
        });


    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, BOOKS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        mAdapter.clear();
        emptyView.setText("No books found");
        if (data != null && !data.isEmpty())
            mAdapter.addAll(data);
        spinner.setVisibility(View.INVISIBLE);
        try_to_search.setVisibility(View.GONE);
        BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
