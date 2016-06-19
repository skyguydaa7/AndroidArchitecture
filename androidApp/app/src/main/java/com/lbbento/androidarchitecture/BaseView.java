package com.lbbento.androidarchitecture;

/**
 * Created by lbbento on 20/06/2016.
 * Base of every view in this App. Views must implement the Interface.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

}
