package com.lbbento.androidarchitecture.data.api;

import com.lbbento.androidarchitecture.data.Discussion;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lbbento on 22/06/2016.
 */

public interface DiscussionsAPIService {

    @GET("discussion")
    Observable<List<Discussion>> getDiscussions();

    @GET("discussion/{id}")
    Observable<Discussion> getDiscussion(@Path("id") String id);

}
