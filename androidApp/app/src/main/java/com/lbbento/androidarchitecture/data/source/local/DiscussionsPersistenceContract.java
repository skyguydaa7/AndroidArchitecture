package com.lbbento.androidarchitecture.data.source.local;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.lbbento.androidarchitecture.BuildConfig;

/**
 * Created by lbbento on 21/06/2016.
 *
 * The contract used for the db to save the discussions locally.
 */
public final class DiscussionsPersistenceContract {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;
    public static final String CONTENT_DISCUSSION_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + DiscussionEntry.TABLE_NAME;
    public static final String CONTENT_DISCUSSION_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + DiscussionEntry.TABLE_NAME;
    public static final String VND_ANDROID_CURSOR_ITEM_VND = "vnd.android.cursor.item/vnd." + CONTENT_AUTHORITY + ".";
    private static final String CONTENT_SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY);
    private static final String VND_ANDROID_CURSOR_DIR_VND = "vnd.android.cursor.dir/vnd." + CONTENT_AUTHORITY + ".";
    private static final String SEPARATOR = "/";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DiscussionsPersistenceContract() {
    }

    public static Uri getBaseDiscussionUri(String discussionId) {
        return Uri.parse(CONTENT_SCHEME + CONTENT_DISCUSSION_ITEM_TYPE + SEPARATOR + discussionId);
    }

    /* Inner class that defines the table contents */
    public static abstract class DiscussionEntry implements BaseColumns {

        public static final String TABLE_NAME = "discussion";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "imageurl";
        public static final Uri CONTENT_DISCUSSIONS_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();
        public static String[] DISCUSSIONS_COLUMNS = new String[]{
                DiscussionsPersistenceContract.DiscussionEntry._ID,
                DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID,
                DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_TITLE,
                DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_DESCRIPTION,
                DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_IMAGE_URL};

        public static Uri buildDiscussionsUriWith(long id) {
            return ContentUris.withAppendedId(CONTENT_DISCUSSIONS_URI, id);
        }

        public static Uri buildDiscussionsUriWith(String id) {
            Uri uri = CONTENT_DISCUSSIONS_URI.buildUpon().appendPath(id).build();
            return uri;
        }

        public static Uri buildDiscussionsUri() {
            return CONTENT_DISCUSSIONS_URI.buildUpon().build();
        }

    }

}