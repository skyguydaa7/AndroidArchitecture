package com.lbbento.androidarchitecture.data.source;

import android.content.Context;
import android.support.v4.content.CursorLoader;

import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.source.local.DiscussionsPersistenceContract;

/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionLoader extends CursorLoader {

    public DiscussionLoader(Context context, int id) {
        super(context, DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUri(),
                DiscussionsPersistenceContract.DiscussionEntry.DISCUSSIONS_COLUMNS,
                DiscussionsPersistenceContract.DiscussionEntry._ID + " = ? ",
                new String[]{String.valueOf(id)}, null);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
    }

}
