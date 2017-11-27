package com.sandy.myframe.http.jsonhandle;

/**
 * Author: sandy
 * Desc:
 * FIXME:
 */
public interface JsonHandleable<T> {
    void handle(T... args);
}
