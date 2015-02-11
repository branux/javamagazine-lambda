package com.alienlabz.javamagazine.facade;

import com.alienlabz.javamagazine.entidade.Cerveja;

@FunctionalInterface
public interface Conversor<T> {

    Cerveja converter(T t);

}
