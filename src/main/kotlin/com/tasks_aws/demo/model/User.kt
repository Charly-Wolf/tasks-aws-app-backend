package com.tasks_aws.demo.model

import com.tasks_aws.demo.model.dto.UserDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(User.COLLECTION_NAME)
data class User (
    @Id
    val id: String? = null,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: Instant = Instant.now()
) {
    companion object {
        const val COLLECTION_NAME = "users"
    }

    fun toDto(): UserDto = UserDto(id = this.id, name = this.name)
}