package com.coviam.flightsearch.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class RequestData {

    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private String adults;
    private String max = "2";
}

