package com.lbbento.androidarchitecture.data.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.source.DiscussionValues;
import com.lbbento.androidarchitecture.data.source.DiscussionsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionsLocalDataSource  implements DiscussionsDataSource {

    private static DiscussionsLocalDataSource INSTANCE;

    private ContentResolver mContentResolver;

    // Prevent direct instantiation.
    private DiscussionsLocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        mContentResolver = contentResolver;
    }

    public static DiscussionsLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new DiscussionsLocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    @Override
    public void getDiscussions(@NonNull GetDiscussionsCallback callback) {
        // no-op since the data is loader via Cursor Loader
    }

    @Override
    public void getDiscussion(@NonNull String discussionId, @NonNull GetDiscussionCallback callback) {
        // no-op since the data is loader via Cursor Loader
    }

    public void saveDiscussion(@NonNull Discussion mDiscussion) {
        checkNotNull(mDiscussion);

        ContentValues values = DiscussionValues.from(mDiscussion);
        mContentResolver.insert(DiscussionsPersistenceContract.DiscussionEntry.buildDiscussionsUri(), values);
    }

}
