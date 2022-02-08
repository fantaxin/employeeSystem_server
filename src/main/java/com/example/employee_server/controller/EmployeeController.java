package com.example.employee_server.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.employee_server.common.Result;
import com.example.employee_server.entity.Department;
import com.example.employee_server.entity.Employee;
import com.example.employee_server.entity.Post;
import com.example.employee_server.mapper.DepartmentMapper;
import com.example.employee_server.mapper.EmployeeMapper;
import com.example.employee_server.mapper.PostMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    PostMapper postMapper;

    @PostMapping("/add")
    public Result<?> add(@RequestBody Employee employee){
        employeeMapper.insert(employee);
        return Result.success(employee);
    }

    @DeleteMapping("/del{id}")
    public Result<?> del(@PathVariable Integer id){
        System.out.println(id);
        return Result.success(employeeMapper.deleteById(id));
    }

    @PutMapping("/edit")
    public Result<?> edit(@RequestBody Employee employee){
//        Employee e = new Employee();
//        e.setName(employee.getName());
//        e.setSex(employee.getSex());
//        e.setAge(employee.getAge());
//        e.setDepartmentId(employee.getDepartmentId());
//        e.setPostId(employee.getPostId());
//        e.setSalary(employee.getSalary());
//
//        UpdateWrapper<Employee> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("id",employee.getId());

        employeeMapper.updateById(employee);

        return Result.success(employee);
    }

    @GetMapping("/search")
    public Result<?> search(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "13") Integer pageSize,
                            @RequestParam(defaultValue = "") String id,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "") String departmentName,
                            @RequestParam(defaultValue = "") String postName)
    {
        System.out.println(name);

        QueryWrapper<Department> departmentWrappers = new QueryWrapper<>();
        List<Department> departmentList;
        departmentWrappers
                .select("id")
                .like("name",departmentName);
        departmentList = departmentMapper.selectList(departmentWrappers);
        List<Integer> departmentList2 = new ArrayList<Integer>();
        for (Department department : departmentList) {
            departmentList2.add(department.getId());
        }

        QueryWrapper<Post> postWrappers = new QueryWrapper<>();
        List<Post> postList;
        postWrappers
                .select("id")
                .like("name", postName);
        postList = postMapper.selectList(postWrappers);
        List<Integer> postList2 = new ArrayList<Integer>();
        for (Post post : postList) {
            postList2.add(post.getId());
        }

        QueryWrapper<Employee> wrappers = new QueryWrapper<>();

        wrappers.select("id", "name", "sex", "age", "department_id", "post_id", "salary", "entry_date")
                .like("id", id)
                .like("name", name)
                .in("department_id", departmentList2)
                .in("post_id", postList2)
                .orderByDesc("id");
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<>(pageNum,13), wrappers);

        return Result.success(employeePage);
    }

    @GetMapping("/dataInit-departments")
    public Result<?> dataInit_departments(){
        QueryWrapper<Department> departmentWrappers = new QueryWrapper<>();
        List<Department> departmentList;
        departmentWrappers.select("id","name");
        departmentList = departmentMapper.selectList(departmentWrappers);
        return Result.success(departmentList);
    }

    @GetMapping("/dataInit-posts")
    public Result<?> dataInit_posts(){
        QueryWrapper<Post> postWrappers = new QueryWrapper<>();
        List<Post> postList;
        postWrappers.select("id","name");
        postList = postMapper.selectList(postWrappers);
        return Result.success(postList);
    }
}
