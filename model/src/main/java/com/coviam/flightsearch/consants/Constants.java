package com.coviam.flightsearch.consants;

public interface Constants {

    String API_KEY = "lsEfyhlUnzk3sTY0MMi27EgC7aG59fEA";
    String API_SECRET = "GjTEsqu5SCgAGcGA";
    String AUTH_URI = "https://test.api.amadeus.com/v1/security/oauth2/token";
    String FLIGHT_SEARCH_URI = "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode={source}"+
            "&destinationLocationCode={destination}&departureDate={date}&returnDate={return}&adults={adult}&max={max}";

}
