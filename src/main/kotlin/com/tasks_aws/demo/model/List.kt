package com.tasks_aws.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(List.COLLECTION_NAME)
class List (
    @Id
    val id: String? = null,
    val userId: String,
    val title: String,
    val createdAt: Instant = Instant.now()
) {
    companion object {
        const val COLLECTION_NAME = "lists"
    }
}