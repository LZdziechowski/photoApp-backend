package com.photoapp.service;

public interface ThrowingConsumer<T, R,  E extends Exception> {
    R apply(T t) throws E;
}
