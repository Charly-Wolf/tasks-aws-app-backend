package com.tasks_aws.demo.controller

import com.tasks_aws.demo.model.Task
import com.tasks_aws.demo.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {
    // TODO : use validation, only show tasks of the logged in user
    @GetMapping()
    fun getTasks(): List<Task> = taskService.getAllTasks()

    @GetMapping("/byList")
    fun getTasksByListId(@RequestParam listId: String): List<Task> = taskService.getTasksByListId(listId)

    @GetMapping("/{taskId}")
    fun getTaskById(@PathVariable taskId: String) = taskService.getTaskById(taskId)
}