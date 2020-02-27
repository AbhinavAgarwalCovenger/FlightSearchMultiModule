package com.coviam.flightsearch.rest.web.helper.impl;

import com.coviam.flightsearch.rest.web.helper.ConverterService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Autowired
    private Mapper mapper;

    @Override
    public <M, T> T convert(M dto, Class<T> clasz) {
        return this.mapper.map(dto,clasz);
    }

    @Override
    public <M, T> List<T> convert(List<M> documents, Class<T> clasz) {
        List<T> responseList = new ArrayList<T>();
        for(M document: documents){
            T response = this.mapper.map(document,clasz);
            responseList.add(response);
        }
        return responseList;
    }
}
