package com.example.javastudy.jdk8;

import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zengrenshang
 * @Date: 2020/6/5 15:21
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Student {
    private Long id;


    private String name;


    private int age;


    private String address;

    public static void main(String[] args) {
        Student s1 = new Student(1L, "肖战", 15, "浙江");

        Student s2 = new Student(2L, "王一博", 15, "湖北");

        Student s3 = new Student(3L, "杨紫", 17, "北京");

        Student s4 = new Student(4L, "李现", 17, "浙江");

        List<Student> students = new ArrayList<>();

        students.add(s1);

        students.add(s2);

        students.add(s3);

        students.add(s4);


//        List<Student> streamStudents = testFilter(students);
//
//        streamStudents.forEach(System.out::println);
//
//        testMap(students);
//
//        testDistinct1();
//
        testDistinct2();

//        testSort1();

//        testSort2();

//        testLimit();

//        testSkip();

//        testReduce();

//        testMin();

        testMatch();
    }

    private static void testMatch() {

        Student s1 = new Student(1L, "肖战", 15, "浙江");

        Student s2 = new Student(2L, "王一博", 15, "湖北");

        Student s3 = new Student(3L, "杨紫", 17, "北京");

        Student s4 = new Student(4L, "李现", 17, "浙江");

        List<Student> students = new ArrayList<>();

        students.add(s1);

        students.add(s2);

        students.add(s3);

        students.add(s4);

        Boolean anyMatch = students.stream().anyMatch(s ->"湖北".equals(s.getAddress()));

        if (anyMatch) {

            System.out.println("有湖北人");

        }

        Boolean allMatch = students.stream().allMatch(s -> s.getAge()>=15);

        if (allMatch) {

            System.out.println("所有学生都满15周岁");

        }

        Boolean noneMatch = students.stream().noneMatch(s -> "杨紫".equals(s.getName()));

        if (noneMatch) {

            System.out.println("没有叫杨紫的同学");

        }

    }



    /**

     * 求集合中元素的最小值

     */

    private static void testMin() {

        Student s1 = new Student(1L, "肖战", 14, "浙江");

        Student s2 = new Student(2L, "王一博", 15, "湖北");

        Student s3 = new Student(3L, "杨紫", 17, "北京");

        Student s4 = new Student(4L, "李现", 17, "浙江");

        List<Student> students = new ArrayList<>();

        students.add(s1);

        students.add(s2);

        students.add(s3);

        students.add(s4);

        Student minS = students.stream().min((stu1,stu2) ->Integer.compare(stu1.getAge(),stu2.getAge())).get();

        System.out.println(minS.toString());

    }

    /**

     * 集合reduce,将集合中每个元素聚合成一条数据

     */

    private static void testReduce() {

        List<String> list = Arrays.asList("欢","迎","你");

        String appendStr = list.stream().reduce("",(a,b) -> a+b);

        System.out.println(appendStr);

    }


    /**

     * 集合skip，删除前n个元素

     */

    private static void testSkip() {

        List<String> list = Arrays.asList("333","222","111");

        list.stream().skip(0).forEach(System.out::println);

    }


    /**

     * 集合limit，返回前几个元素

     */

    private static void testLimit() {

        List<String> list = Arrays.asList("333","222","111");

        list.stream().limit(4).forEach(System.out::println);

    }


    /**

     * 集合排序（指定排序规则）

     */

    private static void testSort2() {

        Student s1 = new Student(1L, "肖战", 15, "浙江");

        Student s2 = new Student(2L, "王一博", 15, "湖北");

        Student s3 = new Student(3L, "杨紫", 17, "北京");

        Student s4 = new Student(4L, "李现", 17, "浙江");

        Student s5 = new Student(4L, "李现", 17, "浙江");

        List<Student> students = new ArrayList<>();

        students.add(s1);

        students.add(s2);

        students.add(s3);

        students.add(s4);
        students.add(s5);

        students.stream()

                .sorted((stu1,stu2) ->Long.compare(stu2.getId(), stu1.getId()))

                .sorted((stu1,stu2) -> Integer.compare(stu2.getAge(),stu1.getAge()))

                .forEach(System.out::println);

    }



    /**

     * 集合排序（默认排序）

     */

    private static void testSort1() {

        List<String> list = Arrays.asList("333","222","111");

        list.stream().sorted().forEach(System.out::println);

    }


    /**

     * 集合去重（引用对象）

     */

    private static void testDistinct2() {

        //引用对象的去重，引用对象要实现hashCode和equal方法，否则去重无效

        Student s1 = new Student(1L, "肖战", 15, "浙江");

        Student s2 = new Student(2L, "王一博", 15, "湖北");

        Student s3 = new Student(3L, "杨紫", 17, "北京");

        Student s4 = new Student(4L, "李现", 17, "浙江");

        Student s5 = new Student(1L, "ceshi ", 15, "浙江");

        List<Student> students = new ArrayList<>();

        students.add(s1);

        students.add(s2);

        students.add(s3);

        students.add(s4);

        students.add(s5);

        students.stream().distinct().forEach(System.out::println);

    }

    /**

     * 集合去重（基本类型）

     */

    private static void testDistinct1() {

        //简单字符串的去重

        List<String> list = Arrays.asList("111","222","333","111","222");

        list.stream().distinct().forEach(System.out::println);

    }


    /**

     * 集合的筛选

     * @param students

     * @return

     */

    private static List<Student> testFilter(List<Student> students) {

        //筛选年龄大于15岁的学生

//        return students.stream().filter(s -> s.getAge()>15).collect(Collectors.toList());

        //筛选住在浙江省的学生

        return students.stream().filter(s ->"浙江".equals(s.getAddress())).collect(Collectors.toList());

    }

    /**

     * 集合转换

     * @param students

     * @return

     */

    private static void testMap(List<Student> students) {

        //在地址前面加上部分信息，只获取地址输出

        List<String> addresses = students.stream().map(s ->"住址:"+s.getAddress()).collect(Collectors.toList());

        addresses.forEach(a ->System.out.println(a));

    }

}



