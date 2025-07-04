package com.tasks_aws.demo.controller

import com.tasks_aws.demo.model.dto.UserDto
import com.tasks_aws.demo.service.UserService
import com.tasks_aws.demo.service.UserServiceTest
import com.tasks_aws.demo.util.Fixtures
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired

class UserControllerTest() : DescribeSpec({

    val userServiceMock = mockk<UserService>()
    val testee = UserController(userServiceMock)

    afterEach {
        clearMocks(userServiceMock)
    }

    describe("User Controller Test") {
        it("should fetch all users") {
            // given
            val expectedUsers = listOf<UserDto>(
                Fixtures.mockUserDto().copy("user-1"),
                Fixtures.mockUserDto().copy("user-2")
            )

            every { userServiceMock.getAllUsers() } returns expectedUsers

            // when
            val result = testee.getUsers()

            // then
            result.size shouldBe 2
            result shouldBe expectedUsers
        }

        xit("should fetch user by id") {
            // given
            // when
            // then
        }

        xit("should return a response entity with an error if user email, name or password are empty") {
            // given
            // when
            // then
        }

        xit("should correctly create a user") {
            // given
            // when
            // then
        }
    }
})
