package com.coviam.flightsearch.rest.web.helper;

import java.util.List;

public interface ConverterService {

    <M, T> T convert(M dto, Class<T> clasz);
    <M, T> List<T> convert(List<M> documents, Class<T> clasz);
}
