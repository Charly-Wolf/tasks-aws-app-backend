package com.tasks_aws.demo.repository

import com.tasks_aws.demo.util.Fixtures
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import io.kotest.extensions.spring.SpringExtension
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class UserRepositoryTest(
    @Autowired private val testee: UserRepository
) : DescribeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        describe("UserRepository") {

            beforeTest { testee.deleteAll() }

            it("should save and retrieve a User") {
                // Given
                val user1 = Fixtures.mockUser().copy(id= "1", name="user1")

                // When
                testee.save(user1)
                val allUsers = testee.findAll()

                // Then
                allUsers.size shouldBe  1

                val retrievedUser = allUsers.first()
                val expectedUser = Fixtures.mockUser().copy(id= "1", name="user1")

                retrievedUser.id shouldBe expectedUser.id
                retrievedUser.name shouldBe expectedUser.name
                retrievedUser.email shouldBe expectedUser.email
                retrievedUser.password shouldBe expectedUser.password
            }

            it("should find user by id") {
                // Given
                val user1 = Fixtures.mockUser().copy(id = "1", name = "User1")
                val user2 = Fixtures.mockUser().copy(id = "2", name = "User2")
                testee.save(user1)

                // When
                val savedUser2 = testee.save(user2)
                val retrievedUser = testee.findById("2")

                // Then
                retrievedUser.isPresent shouldBe true
                retrievedUser.get().id shouldBe savedUser2.id
                retrievedUser.get().name shouldBe savedUser2.name
                retrievedUser.get().email shouldBe savedUser2.email
                retrievedUser.get().password shouldBe savedUser2.password
            }

            // TODO test not finding users
        }
    }

    companion object {
        @Container
        val mongoContainer = MongoDBContainer("mongo:6.0.5")

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            if (!mongoContainer.isRunning) {
                mongoContainer.start()
            }
            registry.add("spring.data.mongodb.uri", mongoContainer::getReplicaSetUrl)
        }
    }
}
