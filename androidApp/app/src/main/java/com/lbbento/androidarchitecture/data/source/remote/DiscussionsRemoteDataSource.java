package com.lbbento.androidarchitecture.data.source.remote;

import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.lbbento.androidarchitecture.data.Discussion;
import com.lbbento.androidarchitecture.data.api.DiscussionsAPIService;
import com.lbbento.androidarchitecture.data.api.ServiceGenerator;
import com.lbbento.androidarchitecture.data.source.DiscussionsDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lbbento on 21/06/2016.
 */

public class DiscussionsRemoteDataSource implements DiscussionsDataSource {

    private static final int SERVICE_LATENCY_IN_MILLIS = 5000;
    private static DiscussionsRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private DiscussionsRemoteDataSource() {
    }

    public static DiscussionsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscussionsRemoteDataSource();
        }
        return INSTANCE;
    }


    public void getDiscussions(final DiscussionsDataSource.GetDiscussionsCallback callback) {



            //Testing
            //List<Discussion> discussions = new ArrayList<>();
            //discussions.add(new Discussion("DiscussionMock1", "Description1", "1", "http://lbbento.com/static/image1.jpg"));
            //discussions.add(new Discussion("DiscussionMock2", "Description2", "2", "http://lbbento.com/static/image1.jpg"));

            //TODO Get discussion from API
            try {
                CompositeSubscription subscriptions = new CompositeSubscription();

                //Creates service
                final DiscussionsAPIService mDiscussionsAPIService =
                        ServiceGenerator.createService(DiscussionsAPIService.class);


                //Creates Observables
                Observable<List<Discussion>> discussionsObservable = mDiscussionsAPIService
                        .getDiscussions()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());


                //Subscribe
                subscriptions.add(discussionsObservable.subscribe(new Subscriber<List<Discussion>>() {
                    @Override
                    public void onCompleted() {
                        // Loaded

                    }

                    @Override
                    public void onError(Throwable e) {
                        // Error
                        //TODO - should do something about the error - Log Using Timber
                        callback.onDiscussionsLoaded(null);
                    }

                    @Override
                    public void onNext(List<Discussion> discussions) {

                            callback.onDiscussionsLoaded(discussions);

                    }
                }));

            } catch (Exception e) {
                e.printStackTrace();
                callback.onDiscussionsLoaded(null);
            }


    }

    /**
     * Note: {@link DiscussionsDataSource.GetDiscussionCallback#onDataNotAvailable()} is never fired. In a real remote data
     * source implementation, this would be fired if the server can't be contacted or the server
     * returns an error.
     */
    public void getDiscussion(@NonNull String discussionId, DiscussionsDataSource.GetDiscussionCallback callback) {
        // Simulate network by delaying the execution.
        try {
            Thread.sleep(SERVICE_LATENCY_IN_MILLIS);

            //TODO - Get Discussion from API
            Discussion dis = new Discussion("1", "1", "1");



            callback.onDiscussionLoaded(dis);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveDiscussion(@NonNull Discussion discussion) {
        //TODO - Save discussion to API
    }

    //TODO - All the GET - POST - DELETE - PUT calls
}
