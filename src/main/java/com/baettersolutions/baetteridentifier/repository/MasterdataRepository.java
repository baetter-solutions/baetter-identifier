package com.baettersolutions.baetteridentifier.repository;

import com.baettersolutions.baetteridentifier.masterdata.MasterdataVariables;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MasterdataRepository extends MongoRepository<MasterdataVariables, String> {
    MasterdataVariables findByAxnr(int axnr);
}
