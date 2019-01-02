package cn.fanchencloud.airport.controller;

import cn.fanchencloud.airport.entity.Student;
import cn.fanchencloud.airport.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    /**
     * 根据学生学号查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Student queryStudentBySno(int studentId) {
        return this.studentService.queryStudentByStudentId(studentId);
    }

    @PostMapping(value = "/add")
    public String addStudent(Student student) {
        int add = studentService.add(student);
        if (add == 0) {
            return "添加失败";
        } else {
            return "添加成功";
        }
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
