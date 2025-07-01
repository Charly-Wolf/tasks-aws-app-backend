package com.tasks_aws.demo

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TasksAwsDemoApplication>().with(TestcontainersConfiguration::class).run(*args)
}
