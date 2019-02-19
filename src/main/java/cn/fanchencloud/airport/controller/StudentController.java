package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Student;
import cn.fanchencloud.airport.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-2
 * Time: 下午5:15
 * Description:
 *
 * @author chen
 */
@RestController
@RequestMapping("/user")
public class StudentController {
    private StudentService studentService;

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    @RequiresPermissions("student:query")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Student queryStudentBySno(int studentId) {
        return this.studentService.queryStudentByStudentId(studentId);
    }

    @RequiresPermissions("student:add")
    @PostMapping(value = "/add")
    public String addStudent(Student student) {
        int add = studentService.add(student);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @RequiresPermissions("user:user")
    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "测试普通！";
    }

    @RequiresPermissions("user:add")
    @GetMapping("/add")
    @ResponseBody
    public String testAdd() {
        return "测试添加！";
    }

    @RequiresPermissions("user:delete")
    @GetMapping("/delete")
    @ResponseBody
    public String testDelete() {
        return "测试删除！";
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
