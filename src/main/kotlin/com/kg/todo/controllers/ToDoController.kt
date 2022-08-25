package com.kg.todo.controllers

import com.kg.todo.models.*
import com.kg.todo.repos.TasksRepo
import com.kg.todo.repos.UsersRepo
import com.kg.todo.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Logger

@RestController
@CrossOrigin
class ToDoController () {
    val logger: Logger = Logger.getLogger("logger")

    @Autowired
    private lateinit var tasksRepo: TasksRepo

    @Autowired
    private lateinit var usersRepo: UsersRepo

    @GetMapping("/tasks")
    fun showTasks(): ResponseEntity<List<Task>> {
        val resp = tasksRepo.findAll()
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @GetMapping("/tasks/{userId}")
    fun showUserTasks(
            @PathVariable userId: Long
    ): ResponseEntity<List<Task>> {
        val resp = tasksRepo.findAllByUserIdOrderById(userId).filter { it.status != STATUS_CANCELLED }
        if (resp.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @GetMapping("/activeTasks/{userId}")
    fun showActiveUserTasks(
            @PathVariable userId: Long
    ): ResponseEntity<List<Task>> {
        val resp = tasksRepo.findByStatusAndUserId(STATUS_INCOMPLETE, userId)
        if (resp.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @PostMapping("/addTask", consumes = ["multipart/form-data"])
    fun addTask(
            @ModelAttribute newTask: NewTask,
            @RequestPart string: String?, file: MultipartFile?
    ): ResponseEntity<Task> {
        val taskTitle = newTask.title
        // TODO change userId below to variable after login functionality is created
        val task = Task(
            userId = 1,
            title = taskTitle,
            collaborators = null,
            status = STATUS_INCOMPLETE
        )
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.CREATED)
    }

    @DeleteMapping("/deleteTask/{id}")
    fun deleteTask(
            @PathVariable id: Long
    ): ResponseEntity<Task> {
        val task: Task? = tasksRepo.findByIdOrNull(id)
        if (task === null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        task.status = STATUS_CANCELLED
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.NO_CONTENT)
    }

    @PatchMapping("completeTask/{id}")
    fun completeTask(
            @PathVariable id: Long
    ): ResponseEntity<Task?> {
        val task: Task? = tasksRepo.findByIdOrNull(id)
        if (task === null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        task.status = STATUS_COMPLETED
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.OK)
    }

    @PatchMapping("reactivateTask/{id}")
    fun reactivateTask(
        @PathVariable id: Long
    ): ResponseEntity<Task?> {
        val task: Task? = tasksRepo.findByIdOrNull(id)
        if (task === null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        task.status = STATUS_INCOMPLETE
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.OK)
    }

    @PatchMapping("completeOrReactivateTask/{id}")
    fun completeOrReactivateTask(
        @PathVariable id: Long
    ): ResponseEntity<Task?> {
        val task: Task? = tasksRepo.findByIdOrNull(id)
        if (task === null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        if (task.status === STATUS_INCOMPLETE) {
            logger.info("found incomplete status")
            task.status = STATUS_COMPLETED
        } else {
            if (task.status === STATUS_COMPLETED) {
                logger.info("found completed status")
                task.status = STATUS_INCOMPLETE
            }
        }
        task.status = STATUS_COMPLETED
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.OK)
    }

    // TODO User functionality
//    @PostMapping("/addUser")
//    fun addUser(
//            @RequestBody user: User
//    ): ResponseEntity<User> {
//        usersRepo.save(user)
//        return ResponseEntity(user, HttpStatus.CREATED)
//    }


// ----- TECHNICAL INTERVIEW CODE PROBLEM FROM FETCH REWARDS -----
//    @PostMapping("addPoints")
//    fun addTransaction(
//    ): MutableList<Points> {
////        var list = mutableListOf<Points>()
//        val transaction1 = Points(
//            payer = "Dannon",
//            points = 100,
//            timestamp = LocalDateTime.now()
//        )
//        val transaction2 = Points(
//            payer = "Tillamook",
//            points = 75,
//            timestamp = LocalDateTime.now().minusHours(4)
//        )
//        val transaction3 = Points(
//            payer = "Dannon",
//            points = 150,
//            timestamp = LocalDateTime.now().minusDays(2)
//        )
//        list.add(transaction1)
//        list.add(transaction2)
//        list.add(transaction3)
//        list.sortBy { it.timestamp }
//        return list
//    }
//
//    @GetMapping("points")
//    fun showPoints(): MutableMap<String, Int> {
//        val pointsBalance = mutableMapOf<String, Int>()
//        list.forEach {
//            if (it.payer in pointsBalance.keys) {
//                // TODO find more elegant solution than two variables
//                val currVal = pointsBalance.getValue(it.payer)
//                val newVal = currVal + it.points
//                pointsBalance[it.payer] = newVal
//            } else {
//                pointsBalance[it.payer] = it.points
//            }
//        }
//        return pointsBalance
//    }


}
