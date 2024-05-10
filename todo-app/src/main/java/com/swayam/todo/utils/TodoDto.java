package com.swayam.todo.utils;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TodoDto {
    private int id;
    private String task;
    private String descript;
    private String number;
    private Date date;
}
