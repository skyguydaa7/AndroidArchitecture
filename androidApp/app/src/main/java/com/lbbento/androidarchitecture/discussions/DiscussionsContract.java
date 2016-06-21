package com.lbbento.androidarchitecture.discussions;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lbbento.androidarchitecture.BasePresenter;
import com.lbbento.androidarchitecture.BaseView;
import com.lbbento.androidarchitecture.data.Discussion;

import java.util.List;

/**
 * Created by lbbento on 20/06/2016.
 * This specifies the contract between the view and the presenter.
 */

public interface DiscussionsContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showDiscussions(@NonNull Cursor discussions);


        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void loadDiscussions();

        void openDiscussionDetails(@NonNull Discussion discussion);

    }

    interface DiscussionItemListener {

        void onDiscussionClick(Discussion discussion);

    }
}