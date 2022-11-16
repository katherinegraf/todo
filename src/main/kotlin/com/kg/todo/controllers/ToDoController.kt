package com.kg.todo.controllers

import com.kg.todo.models.*
import com.kg.todo.repos.TasksRepo
import com.kg.todo.utils.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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

    @PatchMapping("task/{id}/{status}")
    fun updateTaskStatus(
        @PathVariable id: Long,
        @PathVariable status: String
    ): ResponseEntity<Task?> {
        return if (status in VALID_STATUSES) {
            val task = tasksRepo.findByIdOrNull(id)
            if (task === null) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }
            task.status = status
            tasksRepo.save(task)
            ResponseEntity(task, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}
