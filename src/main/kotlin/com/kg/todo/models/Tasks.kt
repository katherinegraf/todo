package com.kg.todo.models

import com.kg.todo.utils.*
import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task (
        @Column(name = "title")
        val title: String?,

        @Column(name = "status")
        var status: String = STATUS_ACTIVE
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;
}