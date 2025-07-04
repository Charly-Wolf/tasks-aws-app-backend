package com.tasks_aws.demo.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired

class TaskListServiceTest(
    @Autowired private val testee: TaskListService
) : DescribeSpec({
    describe("Task List Service Test") {
        it("should...") {
            // given
            // when
            // then
        }
    }
})
