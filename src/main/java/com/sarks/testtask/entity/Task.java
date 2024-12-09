package com.sarks.testtask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "admin_id")
    private Employee admin;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "performer_id")
    private Employee performer;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "task_id")
    private List<Comment> comment;
}
