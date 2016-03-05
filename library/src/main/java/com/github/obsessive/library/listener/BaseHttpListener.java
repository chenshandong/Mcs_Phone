package com.github.obsessive.library.listener;

/**
 * Created by Administrator on 2015/10/10.
 */
public interface BaseHttpListener<T> {

    /**
     * when data call back success
     *
     * @param event_tag
     * @param data
     */
    void onSuccess(int event_tag, T data);

    /**
     * when data call back error
     *
     * @param msg
     */
    void onError(String msg);

    /**
     * when data call back occurred exception
     *
     * @param msg
     */
    void onException(String msg);
}
