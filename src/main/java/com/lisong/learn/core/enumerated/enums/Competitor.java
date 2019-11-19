package com.lisong.learn.core.enumerated.enums;

public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T t);
}