package com.lbbento.androidarchitecture.discussions;

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

        void showDiscussions(List<Discussion> discussions);

        void showLoadingDiscussionsError();

        void showNoDiscussions();

        boolean isActive();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadDiscussions(boolean forceUpdate);

        void openDiscussionDetails(@NonNull Discussion requestedDiscussion);

    }
}