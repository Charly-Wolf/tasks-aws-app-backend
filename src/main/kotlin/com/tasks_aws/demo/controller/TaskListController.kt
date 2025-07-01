package com.tasks_aws.demo.controller

import com.tasks_aws.demo.service.TaskListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lists")
class TaskListController(private val tasklistService: TaskListService) {
    @GetMapping("/byUser")
    fun getListsByUser(@RequestParam userId: String) = tasklistService.getTaskListByUserId(userId)

    @GetMapping("/byList")
    fun getListById(@RequestParam listId: String) = tasklistService.getTaskListById(listId)
}