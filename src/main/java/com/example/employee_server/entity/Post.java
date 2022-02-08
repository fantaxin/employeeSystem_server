package com.example.employee_server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("Post")
@Data
public class Post {
//    @TableId(value="id",type= IdType.AUTO)//id zizeng
    private Integer id;
    private String name;
}
