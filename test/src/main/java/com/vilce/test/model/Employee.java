package com.vilce.test.model;

import java.io.Serializable;

/**
 * @Description: Description
 * @ProjectName: com.vilce.learn
 * @Package: com.vilce.annotation.model.Employee
 * @Author: 雷才哲
 * @Date: 2019/11/21 14:35
 * @Version: 1.0
 */
public class Employee implements Serializable {
    String name;
    int age;
    int salary;
    public Employee(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public void print() {
        System.out.println("Record for: "+name);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Salary: "+salary);
    }
}
