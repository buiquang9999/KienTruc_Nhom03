package com.excise.quotingservice.repository;

import com.excise.quotingservice.entity.Quoting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuotingRepository extends ReactiveMongoRepository<Quoting, UUID> {
}
