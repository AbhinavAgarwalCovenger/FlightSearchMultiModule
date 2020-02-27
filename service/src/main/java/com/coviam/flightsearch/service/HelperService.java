package com.coviam.flightsearch.service;

import com.coviam.flightsearch.document.RequestData;

import java.net.URI;

public interface HelperService {
    String getToken(String username);
    URI getUri(String url, RequestData requestData);
}
