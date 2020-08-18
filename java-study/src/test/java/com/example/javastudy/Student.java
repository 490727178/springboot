package com.example.javastudy;

/**
 * @Author: zengrenshang
 * @Date: 2020/5/12 9:50
 */
public class Student extends People {
    @Override
    void sayHello(){
        System.out.println("I am a Student");
    }
    void studentHello(){
        System.out.println("It is student Say");
    }
}
class Teacher extends People{
}
class People{
    void sayHello(){
        System.out.println("Hello Word");
    }
}
class Main{
    public static void main(String[] args) {
        People people1 = new Teacher();
        People people2 = new Student();
        people1.sayHello();
        people2.sayHello();
        ((Student) people2).studentHello();
    }
}
