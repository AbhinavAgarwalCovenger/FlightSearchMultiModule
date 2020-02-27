package com.coviam.flightsearch.mongodb;

import com.coviam.flightsearch.document.DBDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSearchRepo extends MongoRepository<DBDocument,String> {
}
