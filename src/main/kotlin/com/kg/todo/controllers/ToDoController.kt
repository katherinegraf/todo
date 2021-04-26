package com.kg.todo.controllers

import com.kg.todo.models.Task
import com.kg.todo.models.User
import com.kg.todo.repos.TasksRepo
import com.kg.todo.repos.UsersRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger

@RestController
class ToDoController () {
    val logger: Logger = Logger.getLogger("logger")

    @Autowired
    private lateinit var tasksRepo: TasksRepo

    @Autowired
    private lateinit var usersRepo: UsersRepo

    @GetMapping("/allTasks")
    fun showAllTasks(): List<Task> {
        return tasksRepo.findAll()
    }

    // TODO --- implement ResponseBody in below methods

    @GetMapping("/allTasksForUser/{id}")
    fun showAllUserTasks(
            @PathVariable id: Long
    ): List<Task> {
        return tasksRepo.findAllByUserId(id)
    }

    @GetMapping("/activeTasksForUser/{id}")
    fun showActiveUserTasks(
            @PathVariable id: Long
    ): List<Task> {
        return tasksRepo.findByIsActiveTrueAndUserId(id)
    }

    @PostMapping("/addUser")
    fun addUser(
            @RequestBody user: User
    ): User {
        usersRepo.save(user)
        return user
    }

    @PostMapping("/addTask")
    fun addTask(
            @RequestBody task: Task
    ): Task {
        tasksRepo.save(task)
        return task
    }

    @DeleteMapping("/deleteTask/{id}")
    fun deleteTask(
            @PathVariable id: Long
    ): String {
        tasksRepo.deleteById(id)
        return "Task # $id has been deleted."
    }

    @PatchMapping("markComplete/{id}")
    fun markComplete(
            @PathVariable id: Long
    ): Task? {
        val task: Task? = tasksRepo.findByIdOrNull(id)
        if (task != null) {
            task.isActive = false
            tasksRepo.save(task)
        }
        else {
           // TODO provide ResponseBody for error
        }
        return task
    }
}
