package com.swayam.todo.utils;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<ToDo,Integer> {
}
