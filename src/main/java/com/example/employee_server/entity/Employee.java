package com.example.employee_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("Employee")
@Data
public class Employee {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private char sex;
    private int age;
    private int departmentId;
    private int postId;
    private Double salary;
    private String entryDate;
}
