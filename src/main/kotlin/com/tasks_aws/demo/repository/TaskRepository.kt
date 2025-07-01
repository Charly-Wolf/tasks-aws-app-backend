package com.tasks_aws.demo.repository

import com.tasks_aws.demo.model.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<Task, String> {
    fun findByListId(listId: String): List<Task>
}