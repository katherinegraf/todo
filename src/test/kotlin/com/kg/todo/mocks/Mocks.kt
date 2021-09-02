package com.kg.todo.mocks

import com.kg.todo.models.NewTask

const val testUserId: Long = 1
const val invalidUserId: Long = 500

val newTaskMock = NewTask(
        title = "new task to do",
        file = null
)