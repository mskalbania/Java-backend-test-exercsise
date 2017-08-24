package com.javabackendtest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HoverRepository extends MongoRepository<RequestResponse, String> {
}
