package com.tasks_aws.demo.controller

import com.tasks_aws.demo.model.Task
import com.tasks_aws.demo.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Any> {
        if (task.title.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mapOf("Error" to "Task title must not be empty"))
        }

        if (task.listId.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mapOf("Error" to "Task listId must not be empty"))
        }

        val createdTask = taskService.createTask(task)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask)
    }
}