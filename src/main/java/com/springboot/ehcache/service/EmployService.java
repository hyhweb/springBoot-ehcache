package com.springboot.ehcache.service;

import com.springboot.ehcache.bean.Employee;
import com.springboot.ehcache.mapper.EmploeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "Employee")

@Service
public class EmployService {



  @Autowired
  private EmploeeMapper emploeeMapper;

 // @Cacheable(value="Employee")
 @Caching(
         cacheable = {
                 @Cacheable(key = "#id")
         }/*,
         put = {
                 @CachePut(key = "#result.id")
         }*/
 )
  public Employee getEmpById(Integer id) {
    System.out.println("查询" + id + "号员工,执行数据库sql，不走缓存");
    Employee employee = emploeeMapper.getEmpById(id);
    return employee;
  }
  //    @CachePut: 既调用方法，又更新缓存数据；同步更新缓存
  //    修改了数据库的某个数据，同时更新缓存
  //    运行：
  //        1.先调用目标方法
  //        2.将目标方法的结果缓存起来

  //@CachePut(value="Employee", key = "#result.id")
  public Employee updateEmp(Employee employee) {
    System.out.println("updateEmp " + employee);
    emploeeMapper.updateEmpById(employee);
    employee.setLastName("test");//从这里更改数据，数据库是不没有这个数据的，缓存里面会有这个数据。说明缓存的是return 的数据。
    return employee;
  }
  //       @CacheEvict:缓存清除
  //       key:指定要清除的数据
  //       allEntries = true : 指定清除这个缓存中的所有数据
  //       beforeInvocation=fales: 缓存的清除是否在方法之前执行
  //       默认代表缓存清除操作是在方法执行之后执行；如果出现异常缓存就不会清除
  //       beforeInvocation=true  代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除

  //@CacheEvict(value="Employee", beforeInvocation = true,allEntries = true)
  public void deleteEmp(Integer id) {
    System.out.println("delteEmployee: " + id);
    //int i = 101 / 0;
   // emploeeMapper.deleteEmpById(id);
  }
}
