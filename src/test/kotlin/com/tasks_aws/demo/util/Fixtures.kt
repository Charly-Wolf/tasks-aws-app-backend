package com.tasks_aws.demo.util

import com.tasks_aws.demo.model.Task
import com.tasks_aws.demo.model.TaskList
import com.tasks_aws.demo.model.User
import java.time.Instant

object Fixtures {
    fun mockUser(): User = User(id = null, name = "Test User", email = "test@example.com", password = "secret", createdAt = Instant.now() )

    fun mockTaskList(userId: String = "user1"): TaskList = TaskList(id = null, userId = userId, title = "Test List", createdAt = Instant.now())

    fun mockTask(listId: String = "list1"): Task = Task(id = null, listId = listId, title = "Test Task", done = false, createdAt = Instant.now())
}