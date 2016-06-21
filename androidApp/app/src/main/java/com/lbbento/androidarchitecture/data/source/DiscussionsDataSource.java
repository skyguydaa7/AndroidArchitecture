package com.lbbento.androidarchitecture.data.source;

import android.support.annotation.NonNull;

import com.lbbento.androidarchitecture.data.Discussion;

import java.util.List;

/**
 * Created by lbbento on 21/06/2016.
 */

public interface DiscussionsDataSource {


    interface GetDiscussionsCallback {

        void onDiscussionsLoaded(List<Discussion> discussions);

        void onDataNotAvailable();
    }

    interface GetDiscussionCallback {

        void onDiscussionLoaded(Discussion discussion);

        void onDataNotAvailable();
    }

    void getDiscussions(@NonNull GetDiscussionsCallback callback);

    void getDiscussion(@NonNull String discussionId, @NonNull GetDiscussionCallback callback);

    void saveDiscussion(@NonNull Discussion discussion);


}
