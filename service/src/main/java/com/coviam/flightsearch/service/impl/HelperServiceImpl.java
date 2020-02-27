package com.coviam.flightsearch.service.impl;

import com.coviam.flightsearch.consants.Constants;
import com.coviam.flightsearch.document.RequestData;
import com.coviam.flightsearch.dto.TokenDTO;
import com.coviam.flightsearch.service.HelperService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class HelperServiceImpl implements HelperService {

    @Override
    @Cacheable(value = "token", key = "#username")
    public String getToken(String username) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");
        map.add("client_id", Constants.API_KEY);
        map.add("client_secret",Constants.API_SECRET);

        String uri = Constants.AUTH_URI;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        TokenDTO tokenDTO = restTemplate.postForObject(uri,new HttpEntity<>(map,httpHeaders),TokenDTO.class);
        String token = "Bearer "+tokenDTO.getAccess_token();
        return token;
    }

    @Override
    public URI getUri(String url, RequestData requestData) {
        requestData.setMax("2");
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("source",requestData.getOriginLocationCode());
        uriVariables.put("destination",requestData.getDestinationLocationCode());
        uriVariables.put("date",requestData.getDepartureDate());
        uriVariables.put("return",requestData.getReturnDate());
        uriVariables.put("adult",requestData.getAdults());
        uriVariables.put("max",requestData.getMax());

        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(url);
        URI finalUri = uri.build(uriVariables);
        return finalUri;
    }
}
