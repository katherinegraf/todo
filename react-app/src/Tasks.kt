package com.kg.todo.models

import com.kg.todo.utils.*
import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task (

        @Column(name = "user_id")
        val userId: Long,

        @Column(name = "title")
        val title: String,

        @ManyToMany(mappedBy = "tasks")
        val collaborators: Set<User>? = setOf(),

        @Column(name = "status")
        var status: String = STATUS_INCOMPLETE
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;
}