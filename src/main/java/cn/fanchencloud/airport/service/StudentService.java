package cn.fanchencloud.airport.service;

import cn.fanchencloud.airport.entity.Student;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-2
 * Time: 下午5:10
 * Description:
 *
 * @author chen
 */
public interface StudentService {
    /**
     * 添加 一个学生
     *
     * @param student 学生对象
     * @return 添加结果
     */
    int add(Student student);

    /**
     * 更新一个学生的信息
     *
     * @param student 学生对象
     * @return 修改结果
     */
    int update(Student student);

    /**
     * 删除一个学生的信息
     *
     * @param studentId 学生学号
     * @return 删除结果
     */
    int deleteByStudentId(int studentId);

    /**
     * 查询学生信息
     *
     * @param studentId 学生学号
     * @return 学生信息
     */
    Student queryStudentByStudentId(int studentId);
}
