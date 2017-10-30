package com.example.orxan77.booksapp;

/**
 * Created by orxan77 on 10/16/17.
 */

public class Book {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mYear;
    private String mLanguage;
    private String mThumbnail;
    private String mPreviewLink;

    public Book(String Author, String title, String description, String year, String language, String thumbnail, String previewLink) {
        mPreviewLink = previewLink;
        mAuthor = Author;
        mTitle = title;
        mDescription = description;
        mYear = year;
        mLanguage = language;
        mThumbnail = thumbnail;
    }

    public String getPreviewLink() {
        return mPreviewLink;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getYear() {
        return mYear;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

}
