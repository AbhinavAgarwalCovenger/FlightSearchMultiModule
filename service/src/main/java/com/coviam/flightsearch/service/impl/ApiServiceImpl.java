package com.coviam.flightsearch.service.impl;

import com.coviam.flightsearch.consants.Constants;
import com.coviam.flightsearch.document.*;
import com.coviam.flightsearch.dto.*;
import com.coviam.flightsearch.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private HelperService helperService;

    @Autowired
    private DBDocService dbDocService;

    @Override
    public ResponseEntity<ApiResponse> getFlightDetailsFromAmadeus(RequestData requestData) {

        String uri = Constants.FLIGHT_SEARCH_URI;
        String token = helperService.getToken("Abhinav");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);
        HttpEntity request = new HttpEntity(httpHeaders);
        URI url = helperService.getUri(uri,requestData);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResponse> apiResponse = restTemplate.exchange(url, HttpMethod.GET,request,ApiResponse.class);
        return apiResponse;
    }

    @Override
    public List<ResponseData> sendResponse(RequestData requestData) {

        ApiResponse apiResponse = getFlightDetailsFromAmadeus(requestData).getBody();

        List<ResponseData> responseDataList = new ArrayList<>();
        List<SearchData> searchDataList = apiResponse.getData();
        for (SearchData searchData:searchDataList){
            ResponseData responseData = new ResponseData();
            responseData.setGrandTotal(searchData.getPrice().getGrandTotal());

            List<Itineraries> itinerariesList = searchData.getItineraries();
            List<FlightDetails> flightDetailsList = new ArrayList<>();

            for (Itineraries itineraries : itinerariesList) {

                int size = itineraries.getSegments().size();
                FlightDetails flightDetails = new FlightDetails();

                flightDetails.setDuration(itineraries.getDuration());
                flightDetails.setSource(itineraries.getSegments().get(0)
                        .getDeparture().getIataCode());
                flightDetails.setDepartureTime(itineraries.getSegments().get(0)
                        .getDeparture().getAt());
                flightDetails.setDestination(itineraries.getSegments().get(size - 1)
                        .getArrival().getIataCode());
                flightDetails.setArrivalTime(itineraries.getSegments().get(size - 1)
                        .getArrival().getAt());
                flightDetailsList.add(flightDetails);
            }
            responseData.setFlightDetails(flightDetailsList);
            responseDataList.add(responseData);
        }
        dbDocService.saveData(requestData,responseDataList);
        return responseDataList;
    }
}
