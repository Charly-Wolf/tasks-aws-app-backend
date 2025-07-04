package com.tasks_aws.demo.controller

import com.tasks_aws.demo.model.TaskList
import com.tasks_aws.demo.service.TaskListService
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
@RequestMapping("/lists")
class TaskListController(private val tasklistService: TaskListService) {
    @GetMapping("/byUser")
    fun getListsByUser(@RequestParam userId: String) = tasklistService.getTaskListByUserId(userId)

    @GetMapping("/{listId}")
    fun getListById(@PathVariable listId: String) = tasklistService.getTaskListById(listId)

    @PostMapping
    fun createTaskList(@RequestBody taskList: TaskList): ResponseEntity<Any> {
        if(taskList.title.isBlank())  {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mapOf("error" to "List and its title must not be empty"))
        }
            val createdList = tasklistService.createTaskList(taskList)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdList)
    }
}