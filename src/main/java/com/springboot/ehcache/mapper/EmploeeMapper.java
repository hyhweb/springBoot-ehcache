package com.springboot.ehcache.mapper;

import com.springboot.ehcache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmploeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);
    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id = #{id}")
    public  void updateEmpById(Employee employee);
    @Delete("DELETE * FROM employee WHERE id = #{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public  void InsertEmp(Employee employee);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    public Employee getEmpByLastName(String lastName);

}
