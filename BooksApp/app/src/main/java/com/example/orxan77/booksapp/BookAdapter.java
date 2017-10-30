package com.example.orxan77.booksapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by orxan77 on 10/29/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentItem = getItem(position);

        ImageView thumbnail = (ImageView) listView.findViewById(R.id.thumbnail);
        String url = currentItem.getThumbnail();

        if (url != null)
            Picasso.with(getContext()).load(currentItem.getThumbnail()).into(thumbnail);
        else
            thumbnail.setImageDrawable(null);

        TextView author = (TextView) listView.findViewById(R.id.author);
        author.setText(currentItem.getAuthor());

        TextView title = (TextView) listView.findViewById(R.id.title_of_book);
        title.setText(currentItem.getTitle());

        TextView description = (TextView) listView.findViewById(R.id.description);
        description.setText(currentItem.getDescription());

        TextView year = (TextView) listView.findViewById(R.id.year);
        year.setText(currentItem.getYear());

        TextView language = (TextView) listView.findViewById(R.id.language);
        language.setText(currentItem.getLanguage());

        return listView;
    }
}
