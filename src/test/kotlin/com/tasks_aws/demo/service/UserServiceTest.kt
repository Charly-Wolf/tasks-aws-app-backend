package com.tasks_aws.demo.service

import com.tasks_aws.demo.model.User
import com.tasks_aws.demo.repository.UserRepository
import com.tasks_aws.demo.util.Fixtures
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.springframework.beans.factory.annotation.Autowired

class UserServiceTest() : DescribeSpec({
    val userRepositoryMock = mockk<UserRepository>()
    val testee = UserService(userRepositoryMock)

    afterEach { clearMocks(userRepositoryMock) }

    describe("User Service Test") {
        it("should get all users") {
            // given
            val expectedUsers = listOf(
                Fixtures.mockUser().copy(id = "user-1"),
                Fixtures.mockUser().copy(id = "user-2")
            )

            val expectedUsersDto = expectedUsers.map { it.toDto() }

            every { userRepositoryMock.findAll() } returns expectedUsers

            // when
            val result = testee.getAllUsers()

            // then
            result shouldBe expectedUsersDto
        }
    }
})
