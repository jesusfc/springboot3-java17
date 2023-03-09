package com.jesusfc.springboot3java17.model.converter;

import org.springframework.lang.Nullable;

import java.util.List;

public interface ConverterAll<S, T> {
    @Nullable
    T convert(S source);

    @Nullable
    List<T> convertList(List<S> sourceList);

    @Nullable
    S convertSource(T target);

    @Nullable
    List<S> convertSourceList(List<T> targetList);
}
