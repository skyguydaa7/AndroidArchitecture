package com.lbbento.androidarchitecture.data;

import android.content.ContentResolver;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.lbbento.androidarchitecture.data.source.DiscussionsRepository;
import com.lbbento.androidarchitecture.data.source.local.DiscussionsLocalDataSource;
import com.lbbento.androidarchitecture.data.source.remote.DiscussionsRemoteDataSource;

/**
 * Created by lbbento on 21/06/2016.
 */

public class Injection {


    public static DiscussionsRepository provideDiscussionsRepository(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        return DiscussionsRepository.getInstance(DiscussionsRemoteDataSource.getInstance(),
                DiscussionsLocalDataSource.getInstance(context.getContentResolver()));
    }


}
