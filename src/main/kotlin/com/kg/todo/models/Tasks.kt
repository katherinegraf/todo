package com.kg.todo.models

import javax.persistence.*

@Entity
@Table(name = "tasks")
class Task (
        @Column(name = "user_id")
        val userId: Long,

        @Column(name = "title")
        val title: String,

        @Column(name = "is_active")
        var isActive: Boolean,

        @ManyToMany(mappedBy = "tasks")
        val collaborators: Set<User>? = setOf()
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;
}