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
class TaskListRepositoryTest(
    @Autowired private val testee: TaskListRepository
) : DescribeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        describe("TaskListRepository") {

            beforeTest { testee.deleteAll() }

            it("should save and retrieve a TaskList") {
                // Given
                val list1 = Fixtures.mockTaskList().copy(id = "1", userId = "user1", title = "list1")
                val list2 = Fixtures.mockTaskList().copy(id = "2", userId = "user1", title = "list2")

                // When
                testee.save(list1)
                testee.save(list2)
                val allLists = testee.findAll()

                // Then
                allLists.size shouldBe 2

                val retrievedFirstList = allLists.first()
                val expectedList = Fixtures.mockTaskList().copy(id = "1", userId = "user1", title = "list1")

                retrievedFirstList.id shouldBe expectedList.id
                retrievedFirstList.userId shouldBe expectedList.userId
                retrievedFirstList.title shouldBe expectedList.title
            }

            it("should find list by id") {
                // Given
                val list1 = Fixtures.mockTaskList().copy(id = "1", userId = "user1", title = "list1")
                val list2 = Fixtures.mockTaskList().copy(id = "2", userId = "user1", title = "list2")
                testee.save(list1)
                testee.save(list2)

                // When
                val retrievedList = testee.findById("2")

                // Then
                retrievedList.isPresent shouldBe true
                retrievedList.get().id shouldBe list2.id
                retrievedList.get().userId shouldBe list2.userId
                retrievedList.get().title shouldBe list2.title
            }

            it("should find lists by userId") {
                // Given
                val list1 = Fixtures.mockTaskList().copy(id = "1", userId = "user1", title = "list1")
                val list2 = Fixtures.mockTaskList().copy(id = "2", userId = "user1", title = "list2")
                val list3 = Fixtures.mockTaskList().copy(id = "3", userId = "user2", title = "list3")

                testee.save(list1)
                testee.save(list2)
                testee.save(list3)

                // When
                val retrievedLists = testee.findByUserId("user1")

                // Then
                retrievedLists.size shouldBe 2
                retrievedLists.map {it.title}.toSet() shouldBe setOf("list1", "list2")
            }

            // TODO test not finding lists
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
