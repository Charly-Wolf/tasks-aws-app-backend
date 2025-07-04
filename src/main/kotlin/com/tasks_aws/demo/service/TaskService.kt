package com.tasks_aws.demo.service

import com.tasks_aws.demo.model.Task
import com.tasks_aws.demo.repository.TaskRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getAllTasks(): List<Task> = taskRepository.findAll()
    fun getTaskById(taskId: String): Optional<Task> = taskRepository.findById(taskId)
    fun getTasksByListId(listId: String): List<Task> = taskRepository.findByListId(listId)
    fun createTask(task: Task): Task = taskRepository.save<Task>(task)
}