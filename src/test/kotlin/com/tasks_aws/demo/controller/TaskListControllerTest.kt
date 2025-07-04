package com.tasks_aws.demo.controller

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired

class TaskListControllerTest(
    @Autowired private val testee: TaskListController
) : DescribeSpec({

    describe("Task List Controller Test") {
        it("should fetch all lists by user") {
            // given
            // when
            // then
        }

        it("should fetch list by id") {
            // given
            // when
            // then
        }

        it("should return a response entity with an error if list title is empty") {
            // given
            // when
            // then
        }

        it("should correctly create a list") {
            // given
            // when
            // then
        }
    }
})
