package com.coviam.flightsearch.service;

import com.coviam.flightsearch.document.RequestData;
import com.coviam.flightsearch.document.ResponseData;

import java.util.List;

public interface DBDocService {
    void saveData(RequestData requestData, List<ResponseData> responseDataList);
}
