package com.tasks_aws.demo.repository

import com.tasks_aws.demo.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {

}