package com.kg.todo.models

import org.springframework.web.multipart.MultipartFile

class NewTask (
    val title: String?,
    val file: MultipartFile?

    // reference:
    // https://www.baeldung.com/sprint-boot-multipart-requests
)