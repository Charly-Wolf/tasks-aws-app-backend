package com.tasks_aws.demo.service

import com.tasks_aws.demo.model.TaskList
import com.tasks_aws.demo.repository.TaskListRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class TaskListService(private val taskListRepository: TaskListRepository) {
    fun getTaskListByUserId(userId: String): List<TaskList> = taskListRepository.findByUserId(userId)
    fun getTaskListById(taskListId: String): Optional<TaskList> = taskListRepository.findById(taskListId)
}