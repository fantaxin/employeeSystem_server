package com.example.employee_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("Department")
@Data
public class Department {
//    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private Integer manager_id;
}