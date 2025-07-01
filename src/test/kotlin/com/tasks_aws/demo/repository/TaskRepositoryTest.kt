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
class TaskRepositoryTest(
    @Autowired private val testee: TaskRepository
) : DescribeSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        describe("TaskRepository") {

            beforeTest { testee.deleteAll() }

            it("should save and retrieve a Task") {
                // Given
                val task1 = Fixtures.mockTask().copy(id = "1", listId = "list1", title = "task1")
                val task2 = Fixtures.mockTask().copy(id = "2", listId = "list1", title = "task2")

                // When
                testee.save(task1)
                testee.save(task2)
                val allTasks = testee.findAll()

                // Then
                allTasks.size shouldBe 2

                val retrievedFirstTask = allTasks.first()
                val expectedTask = Fixtures.mockTask().copy(id = "1", listId = "list1", title = "task1")

                retrievedFirstTask.id shouldBe expectedTask.id
                retrievedFirstTask.listId shouldBe expectedTask.listId
                retrievedFirstTask.title shouldBe expectedTask.title
            }

            it("should find task by id") {
                // Given
                val task1 = Fixtures.mockTask().copy(id = "1", listId = "list1", title = "task1")
                val task2 = Fixtures.mockTask().copy(id = "2", listId = "list1", title = "task2")
                testee.save(task1)
                testee.save(task2)

                // When
                val retrievedTask = testee.findById("2")

                // Then
                retrievedTask.isPresent shouldBe true
                retrievedTask.get().id shouldBe task2.id
                retrievedTask.get().listId shouldBe task2.listId
                retrievedTask.get().title shouldBe task2.title
            }

            it("should find lists by listId") {
                // Given
                val task1 = Fixtures.mockTask().copy(id = "1", listId = "list1", title = "task1")
                val task2 = Fixtures.mockTask().copy(id = "2", listId = "list2", title = "task2")
                val task3 = Fixtures.mockTask().copy(id = "3", listId = "list1", title = "task3")

                testee.save(task1)
                testee.save(task2)
                testee.save(task3)

                // When
                val retrievedTasks = testee.findByListId("list1")

                // Then
                retrievedTasks.size shouldBe 2
                retrievedTasks.map {it.title}.toSet() shouldBe setOf("task1", "task3")
            }

            // TODO test not finding tasks
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
