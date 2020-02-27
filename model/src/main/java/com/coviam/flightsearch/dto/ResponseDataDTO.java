package com.coviam.flightsearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDataDTO {
    private String grandTotal;
    private List<FLightDetailsDTO> flightDetails;
}
