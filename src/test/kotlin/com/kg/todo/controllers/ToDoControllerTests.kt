package com.kg.todo.controllers

import com.kg.todo.models.Task
import com.kg.todo.mocks.*
import com.kg.todo.utils.STATUS_CANCELLED
import com.kg.todo.utils.STATUS_COMPLETED
import com.kg.todo.utils.STATUS_ACTIVE
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import java.util.logging.Logger

class ToDoControllerTests {

    val logger: Logger = Logger.getLogger("logger")

    @Autowired
    private lateinit var toDo: ToDoController

    @Test
    fun showTasksTest() {
        // confirm filtering happens
    }

    fun addTaskTest() {
        // could confirm handling of task title of string "null"
        // how to feed it a task to process?
        // @Before/@After run surrounding EACH test in suite
        // @BeforeClass/@AfterClass run once, surrounding test suite


    }
}

//var addedTaskId: Long = 0
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
//class ToDoControllerTests {
//
//    val logger: Logger = Logger.getLogger("logger")
//
//    @Autowired
//    private lateinit var toDo: ToDoController
//
//    @Test
//    @Order(1)
//    fun showTasksTest() {
//        val result= toDo.showTasks()
//        assert(result.statusCode == HttpStatus.OK)
//    }
//
//    @Test
//    @Order(6)
//    fun addTaskTest() {
//        val result = toDo.addTask(
//                newTaskMock, newTaskMock.title, newTaskMock.file)
//        val resultBody = result.body as Task
//        addedTaskId = resultBody.id
//        assert(result.statusCode == HttpStatus.CREATED)
//        assert(resultBody.status == STATUS_ACTIVE)
//        assert(resultBody.title == newTaskMock.title)
//    }
//
//    @Test
//    @Order(7)
//    fun completeTaskTest() {
//        logger.info(addedTaskId.toString())
//        val result = toDo.completeTask(addedTaskId)
//        val resultBody = result.body as Task
//        assert(result.statusCode == HttpStatus.OK)
//        assert(resultBody.status == STATUS_COMPLETED)
//    }
//
//    @Test
//    @Order(8)
//    fun deleteTaskTest() {
//        val result = toDo.deleteTask(addedTaskId)
//        val resultBody = result.body as Task
//        assert(result.statusCode == HttpStatus.NO_CONTENT)
//        assert(resultBody.status == STATUS_CANCELLED)
//    }
//
//}

