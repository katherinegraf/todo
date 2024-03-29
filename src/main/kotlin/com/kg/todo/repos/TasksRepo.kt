package com.kg.todo.repos

import com.kg.todo.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TasksRepo : JpaRepository<Task, Long> {

    fun findAllByOrderById(): List<Task>

}
