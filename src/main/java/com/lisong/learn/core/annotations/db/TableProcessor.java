package com.lisong.learn.core.annotations.db;

import java.util.List;

public interface TableProcessor {

    void process(List<Class<?>> tables);
}