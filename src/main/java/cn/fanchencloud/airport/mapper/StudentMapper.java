package cn.fanchencloud.airport.mapper;

import cn.fanchencloud.airport.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-2
 * Time: 下午5:01
 * Description:
 *
 * @author chen
 */
@Mapper
@Component
public interface StudentMapper {
    /**
     * 添加一个学生
     *
     * @param student 添加的学生对象
     * @return 添加结果
     */
    @Insert("insert into student (studentId,studentName,age) values (#{studentId},#{studentName},#{age})")
    int add(Student student);

    /**
     * 更新一个学生信息
     *
     * @param student 更新的学生信息
     * @return 更新结果
     */
    @Update("update student set studentName = #{studentName} ,set age = #{age} where studentId = #{studentId}")
    int update(Student student);

    /**
     * 根据学生学号删除一个学生
     *
     * @param studentId 学生学号
     * @return 删除结果
     */
    @Delete("delete from student where studentId = #{studentId}")
    int deleteByIds(int studentId);

    /**
     * 根据学号查询一个学生信息
     *
     * @param studentId 学生学号
     * @return 学生该信息
     */
    @Select("select * from student where studentId =#{studentId}")
    @Results(id = "student", value = {
            @Result(property = "studentId", column = "studentId", javaType = Integer.class),
            @Result(property = "studentName", column = "studentName", javaType = String.class),
            @Result(property = "age", column = "age", javaType = Integer.class)
    })
    Student queryStudentById(int studentId);
}
