package com.kg.todo.repos

import com.kg.todo.models.Task
import com.kg.todo.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface UsersRepo : JpaRepository<User, Long> {

}
