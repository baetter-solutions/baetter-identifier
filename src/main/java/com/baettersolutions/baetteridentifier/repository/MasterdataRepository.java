package com.baettersolutions.baetteridentifier.repository;

import com.baettersolutions.baetteridentifier.database.Masterdata;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MasterdataRepository extends MongoRepository<Masterdata, String> {
    Masterdata findByAxnr(int axnr);
}
