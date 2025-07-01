package com.tasks_aws.demo.repository

import com.tasks_aws.demo.model.TaskList
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskListRepository: MongoRepository<TaskList, String> {
    fun findByUserId(userId: String): List<TaskList>

}