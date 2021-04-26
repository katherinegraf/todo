package com.kg.todo.models

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Column(name = "username")
        val username: String,

        @Column(name = "password")
        val password: String,

        @Column(name = "first_name")
        val firstName: String,

        @Column(name = "last_name")
        val lastName: String,

        @ManyToMany
        @JoinTable(
                name = "users_tasks",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "task_id")]
        )
        val tasks: Set<Task> = setOf()
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0;
}