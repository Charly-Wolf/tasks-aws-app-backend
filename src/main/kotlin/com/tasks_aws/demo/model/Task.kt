package com.tasks_aws.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(Task.COLLECTION_NAME)
data class Task (
    @Id
    val id: String? = null,
    val listId: String,
    val title: String,
    val done: Boolean = false,
    val createdAt: Instant = Instant.now()
)
{
    companion object {
        const val COLLECTION_NAME = "tasks"
    }
}