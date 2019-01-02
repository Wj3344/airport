package cn.fanchencloud.airport.service.impl;

import cn.fanchencloud.airport.entity.Student;
import cn.fanchencloud.airport.mapper.StudentMapper;
import cn.fanchencloud.airport.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-2
 * Time: 下午5:11
 * Description:
 *
 * @author chen
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;
    @Override
    public int add(Student student) {
        return this.studentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return this.studentMapper.update(student);
    }

    @Override
    public int deleteByStudentId(int studentId) {
        return this.studentMapper.deleteByIds(studentId);
    }

    @Override
    public Student queryStudentByStudentId(int studentId) {
        return this.studentMapper.queryStudentById(studentId);
    }

    @Autowired
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
}
