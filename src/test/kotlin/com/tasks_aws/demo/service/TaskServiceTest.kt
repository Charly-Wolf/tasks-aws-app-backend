package com.tasks_aws.demo.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired

class TaskServiceTest(
    @Autowired private val testee: TaskService
) : DescribeSpec({
    describe("Task Service Test") {
        it("should...") {
            // given
            // when
            // then
        }
    }
})
