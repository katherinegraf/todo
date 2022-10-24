package com.kg.todo.controllers

import com.kg.todo.models.Task
import com.kg.todo.mocks.*
import com.kg.todo.utils.STATUS_CANCELLED
import com.kg.todo.utils.STATUS_COMPLETED
import com.kg.todo.utils.STATUS_INCOMPLETE
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.util.logging.Logger

var addedTaskId: Long = 0

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ToDoControllerTests {

    val logger: Logger = Logger.getLogger("logger")

    @Autowired
    private lateinit var toDo: ToDoController

    // TODO look into test fixtures rather than mocks
        // might allow you to get rid of the TestMethodOrder
//    @Fixture
//    fun scaffolding() {
//        toDo.addTask()
//    }

    @Test
    @Order(1)
    fun showTasksTest() {
        val result= toDo.showTasks()
        assert(result.statusCode == HttpStatus.OK)
        // TODO Should this be tested later in the queue so can confirm tasks exist?
    }

    @Test
    @Order(2)
    fun showUserTasksTest_success() {
        val result= toDo.showUserTasks(testUserId)
        val resultBody = result.body as List<Task>
        assert(result.statusCode == HttpStatus.OK)
        for (task in resultBody) {
            assert(task.status != STATUS_CANCELLED)
            assert(task.userId == testUserId)
        }
    }

    @Test
    @Order(3)
    fun showUserTasksTest_failure() {
        val result= toDo.showUserTasks(invalidUserId)
        assert(result.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(4)
    fun showActiveUserTasksTest_success() {
        val result= toDo.showActiveUserTasks(testUserId)
        val resultBody = result.body as List<Task>
        assert(result.statusCode == HttpStatus.OK)
        for (task in resultBody) {
            assert(task.status == STATUS_INCOMPLETE)
            assert(task.userId == testUserId)
        }
    }

    @Test
    @Order(5)
    fun showActiveUserTasksTest_failure() {
        val result= toDo.showActiveUserTasks(invalidUserId)
        assert(result.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    @Order(6)
    fun addTaskTest() {
        val result = toDo.addTask(
                newTaskMock, newTaskMock.title, newTaskMock.file)
        val resultBody = result.body as Task
        addedTaskId = resultBody.id
        assert(result.statusCode == HttpStatus.CREATED)
        assert(resultBody.status == STATUS_INCOMPLETE)
        assert(resultBody.title == newTaskMock.title)
    }

    @Test
    @Order(7)
    fun completeTaskTest() {
        logger.info(addedTaskId.toString())
        val result = toDo.completeTask(addedTaskId)
        val resultBody = result.body as Task
        assert(result.statusCode == HttpStatus.OK)
        assert(resultBody.status == STATUS_COMPLETED)
    }

    @Test
    @Order(8)
    fun deleteTaskTest() {
        val result = toDo.deleteTask(addedTaskId)
        val resultBody = result.body as Task
        assert(result.statusCode == HttpStatus.NO_CONTENT)
        assert(resultBody.status == STATUS_CANCELLED)
    }

}
