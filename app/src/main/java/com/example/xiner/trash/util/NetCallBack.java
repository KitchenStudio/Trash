package com.example.xiner.trash.util;

/**
 * Created by seal on 3/27/15.
 */
public interface  NetCallBack<E> {
    public void receivedResult(E result, NetError error);
}
