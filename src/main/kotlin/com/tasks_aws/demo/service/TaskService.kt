package com.tasks_aws.demo.service

import com.tasks_aws.demo.model.Task
import com.tasks_aws.demo.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAllTasks(): List<Task> = taskRepository.findAll()
    fun getTasksByListId(listId: String): List<Task> = taskRepository.findByListId(listId)
}