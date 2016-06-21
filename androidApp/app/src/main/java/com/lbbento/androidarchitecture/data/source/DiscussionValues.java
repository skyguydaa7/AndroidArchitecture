package com.lbbento.androidarchitecture.data.source;

import android.content.ContentValues;

import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.source.local.DiscussionsPersistenceContract;

/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionValues {

    public static ContentValues from(Discussion discussion) {
        ContentValues values = new ContentValues();
        values.put(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID, discussion.getId());
        values.put(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_TITLE, discussion.getTitle());
        values.put(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_DESCRIPTION, discussion.getDescription());
        values.put(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_IMAGE_URL, discussion.getImageUrl());
        return values;
    }

}
