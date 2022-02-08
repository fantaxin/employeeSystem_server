package com.example.employee_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    @TableId(value="id",type= IdType.AUTO)//id zizeng
    private Integer id;
    private String nikeName;
    private String username;
    private String password;
    private Integer age;
    private String sec;
    private String address;
}
