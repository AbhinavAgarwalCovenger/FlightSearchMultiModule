package com.coviam.flightsearch.service;

import com.coviam.flightsearch.document.RequestData;
import com.coviam.flightsearch.document.ResponseData;
import com.coviam.flightsearch.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiService {
    List<ResponseData> sendResponse(RequestData requestData);
    ResponseEntity<ApiResponse> getFlightDetailsFromAmadeus(RequestData requestData);
}
