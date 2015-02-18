package com.belongo.services.registration.backend

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Simon on 18.02.2015.
 */
trait RegisteredUserRepository extends MongoRepository[RegisteredUser, String]