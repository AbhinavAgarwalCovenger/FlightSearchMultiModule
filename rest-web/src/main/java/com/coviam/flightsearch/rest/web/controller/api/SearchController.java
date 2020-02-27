package com.coviam.flightsearch.rest.web.controller.api;

import com.coviam.flightsearch.dto.ResponseDataDTO;
import com.coviam.flightsearch.service.ApiService;
import com.coviam.flightsearch.document.RequestData;
import com.coviam.flightsearch.document.ResponseData;
import com.coviam.flightsearch.dto.RequestDataDTO;
import com.coviam.flightsearch.rest.web.helper.ConverterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ApiService apiService;

    @Autowired
    ConverterService converterService;

    @PostMapping("/flight")
    public ResponseEntity<List<ResponseDataDTO>> getFlights(@RequestBody RequestDataDTO requestDataDTO){
        RequestData requestData = converterService.convert(requestDataDTO, RequestData.class);
        List<ResponseData> responseDataList = apiService.sendResponse(requestData);
        List<ResponseDataDTO> responseDataDTOList = converterService.convert(responseDataList,ResponseDataDTO.class);
        return new ResponseEntity<>(responseDataDTOList,HttpStatus.OK);
    }
}
