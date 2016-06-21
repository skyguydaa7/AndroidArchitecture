package com.lbbento.androidarchitecture.data.source;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.lbbento.androidarchitecture.data.source.local.DiscussionsPersistenceContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lbbento on 21/06/2016.
 */

public class LoaderProvider {

    @NonNull
    private final Context mContext;

    public LoaderProvider(@NonNull Context context) {
        mContext = checkNotNull(context, "context cannot be null");
    }

    public Loader<Cursor> createDiscussionsLoader() {
        String selection = null;
        String[] selectionArgs = null;

        return new CursorLoader(
                mContext,
                DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUri(),
                DiscussionsPersistenceContract.DiscussionEntry.DISCUSSIONS_COLUMNS, selection, selectionArgs, null
        );

    }

    public Loader<Cursor> createDiscussionLoader(String discussionId) {
        return new CursorLoader(mContext, DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUriWith(discussionId),
                null,
                null,
                new String[]{String.valueOf(discussionId)}, null
        );
    }
}
