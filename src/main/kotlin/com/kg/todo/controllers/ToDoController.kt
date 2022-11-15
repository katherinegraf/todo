package com.kg.todo.controllers

import com.kg.todo.models.*
import com.kg.todo.repos.TasksRepo
import com.kg.todo.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.relational.core.sql.IsNull
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

    @GetMapping("/tasks")
    fun showTasks(): ResponseEntity<List<Task>> {
        val allTasks = tasksRepo.findAllByOrderById()
        val resp = allTasks.filter {
            it.status == STATUS_ACTIVE || it.status == STATUS_COMPLETED
        }
        return ResponseEntity(resp, HttpStatus.OK)
    }

    @PostMapping("/addTask", consumes = ["multipart/form-data"])
    fun addTask(
            @ModelAttribute newTask: NewTask,
            @RequestPart string: String?, file: MultipartFile?
    ): ResponseEntity<Task> {
        val taskTitle = newTask.title
        return if (taskTitle != null && taskTitle != "null") {
            tasksRepo.save(Task(
                title = taskTitle,
                status = STATUS_ACTIVE
            ))
            ResponseEntity(HttpStatus.CREATED)
        } else {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
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
        task.status = STATUS_ACTIVE
        tasksRepo.save(task)
        return ResponseEntity(task, HttpStatus.OK)
    }
}
