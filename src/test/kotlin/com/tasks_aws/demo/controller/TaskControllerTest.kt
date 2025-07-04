package com.tasks_aws.demo.controller

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired

class TaskControllerTest(
    @Autowired private val testee: TaskController
) : DescribeSpec({

    describe("Task Controller Test") {
        it("should fetch all tasks") {
            // given
            // when
            // then
        }

        it("should fetch tasks by listId") {
            // given
            // when
            // then
        }

        it("should fetch task by id") {
            // given
            // when
            // then
        }

        it("should return a response entity with an error if task title is empty") {
            // given
            // when
            // then
        }

        it("should return a response entity with an error if the listId is empty") {
            // given
            // when
            // then
        }

        it("should correctly create a task") {
            // given
            // when
            // then
        }
    }
})
