package com.tasks_aws.demo.service

import com.tasks_aws.demo.model.dto.UserDto
import com.tasks_aws.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<UserDto> = userRepository.findAll().map { it.toDto()}
}