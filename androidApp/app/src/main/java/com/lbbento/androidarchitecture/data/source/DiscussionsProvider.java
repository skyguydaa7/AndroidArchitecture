package com.lbbento.androidarchitecture.data.source;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.lbbento.androidarchitecture.data.source.local.DiscussionsDbHelper;
import com.lbbento.androidarchitecture.data.source.local.DiscussionsPersistenceContract;

/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionsProvider extends ContentProvider {

    private static final int DISCUSSION = 100;
    private static final int DISCUSSION_ITEM = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DiscussionsDbHelper mDiscussionsDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DiscussionsPersistenceContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME, DISCUSSION);
        matcher.addURI(authority, DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME + "/*", DISCUSSION_ITEM);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDiscussionsDbHelper = new DiscussionsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DISCUSSION:
                return DiscussionsPersistenceContract.CONTENT_DISCUSSION_TYPE;
            case DISCUSSION_ITEM:
                return DiscussionsPersistenceContract.CONTENT_DISCUSSION_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case DISCUSSION:
                retCursor = mDiscussionsDbHelper.getReadableDatabase().query(
                        DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case DISCUSSION_ITEM:
                String[] where = {uri.getLastPathSegment()};
                retCursor = mDiscussionsDbHelper.getReadableDatabase().query(
                        DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME,
                        projection,
                        DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                        where,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDiscussionsDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case DISCUSSION:
                Cursor exists = db.query(
                        DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME,
                        new String[]{DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID},
                        DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                        new String[]{values.getAsString(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID)},
                        null,
                        null,
                        null
                );
                if (exists.moveToLast()) {
                    long _id = db.update(
                            DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME, values,
                            DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID + " = ?",
                            new String[]{values.getAsString(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID)}
                    );
                    if (_id > 0) {
                        returnUri = DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUriWith(_id);
                    } else {
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    }
                } else {
                    long _id = db.insert(DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME, null, values);
                    if (_id > 0) {
                        returnUri = DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUriWith(_id);
                    } else {
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    }
                }
                exists.close();
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDiscussionsDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case DISCUSSION:
                rowsDeleted = db.delete(
                        DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDiscussionsDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case DISCUSSION:
                rowsUpdated = db.update(DiscussionsPersistenceContract.DiscussionEntry.TABLE_NAME, values, selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
