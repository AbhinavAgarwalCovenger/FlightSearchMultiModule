package com.coviam.flightsearch.service.impl;

import com.coviam.flightsearch.document.DBDocument;
import com.coviam.flightsearch.document.RequestData;
import com.coviam.flightsearch.document.ResponseData;
import com.coviam.flightsearch.mongodb.FlightSearchRepo;
import com.coviam.flightsearch.service.DBDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBDocServiceImpl implements DBDocService {
    @Autowired
    private FlightSearchRepo flightSearchRepo;

    @Override
    public void saveData(RequestData requestData, List<ResponseData> responseDataList) {

            DBDocument dbDocument = new DBDocument();
            dbDocument.setRequestData(requestData);
            dbDocument.setResponseData(responseDataList);
            flightSearchRepo.insert(dbDocument);
    }
}
