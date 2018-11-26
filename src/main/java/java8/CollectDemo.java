package java8;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wang Chen Peng
 * @date 2018/11/16
 * describe:
 */
class Student {
    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private Gender gender;

    /**
     * 班级
     */
    private Grade grade;

    public Student(String name, int age, Gender gender, Grade grade) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "[name=" + name + ", age=" + age + ", gender=" + gender
                + ", grade=" + grade + "]";
    }

}

/**
 * 性别
 */
enum Gender {
    MALE, FEMALE
}

/**
 * 班级
 */
enum Grade {
    ONE, TWO, THREE, FOUR;
}

public class CollectDemo {

    /**
     * 终止操作
     * <p>
     * 查找与匹配
     * allMatch --检查是否匹配所有元素
     * anyMatch --检查是否至少匹配一个元素
     * noneMatch --检查是否没有匹配所有元素
     * findFirst -- 返回第一个元素
     * findAny -- 返回流中的任意一个元素
     * count -- 返回流中的总个数
     * max -- 返回流中最大值
     * min -- 返回流中最小值
     */

    public static void main(String[] args) {
        // 测试数据
        List<Student> students = Arrays.asList(
                new Student("小明", 10, Gender.MALE, Grade.ONE),
                new Student("大明", 9, Gender.MALE, Grade.THREE),
                new Student("小白", 8, Gender.FEMALE, Grade.TWO),
                new Student("小黑", 13, Gender.FEMALE, Grade.FOUR),
                new Student("小红", 7, Gender.FEMALE, Grade.THREE),
                new Student("小黄", 13, Gender.MALE, Grade.ONE),
                new Student("小青", 13, Gender.FEMALE, Grade.THREE),
                new Student("小紫", 9, Gender.FEMALE, Grade.TWO),
                new Student("小王", 6, Gender.MALE, Grade.ONE),
                new Student("小李", 6, Gender.MALE, Grade.ONE),
                new Student("小马", 14, Gender.FEMALE, Grade.FOUR),
                new Student("小刘", 13, Gender.MALE, Grade.FOUR));

        // 得到所有学生的年龄列表
        TreeSet<Integer> collect = students.stream().map(Student::getAge).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect.toString());

        System.out.println("------------------------------------------");
        // 统计汇总信息
        IntSummaryStatistics collect1 = students.stream().collect(Collectors.summarizingInt(Student::getAge));
        System.out.println(collect1.toString());

        System.out.println("------------------------------------------");
        // 分块 根据条件来分类
        Map<Boolean, List<Student>> collect2 = students.stream().collect(Collectors.partitioningBy(s -> s.getGender() == Gender.MALE));
        MapUtils.verbosePrint(System.out, "男女学生列表", collect2);

        System.out.println("------------------------------------------");
        Map<Boolean, List<Student>> collect3 = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() == 13));
        MapUtils.verbosePrint(System.out, "年龄为13的", collect3);

        System.out.println("------------------------------------------");
        // 分组
        Map<Integer, List<Student>> collect4 = students.stream().collect(Collectors.groupingBy(Student::getAge));
        MapUtils.verbosePrint(System.out, "按照年龄分组", collect4);
        //收集年龄为十三的 获取到的是list
        System.out.println(collect4.get(13).toString());


        System.out.println("------------------------------------------");
        // 得到所有班级学生的个数
        Map<Grade, Long> collect5 = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
        MapUtils.verbosePrint(System.out, "班级里的人数", collect5);

        System.out.println("------------------------------------------");
        //获得第一个年龄为13的 (短路操作)
        Optional<Student> first = students.stream().filter(x -> x.getAge() == 13).findFirst();
        System.out.println(first.get());

        System.out.println("------------------------------------------");
        boolean b = students.stream().anyMatch(x -> x.getAge() == 13);
        System.out.println("是否有一个人的年龄等于13岁:" + b);

        System.out.println("------------------------------------------");
        boolean b1 = students.stream().allMatch(x -> x.getAge() == 13);
        System.out.println("是否全部人的年龄等于13岁:" + b1);

        /**
         * 求出年龄总和
         * 先将0作为x，然后运算 0+y，再将这个结果作为x，运算 x+y，y为集合的元素，x就作为运算结果
         */
        System.out.println("------------------------------------------");
        Integer reduce = students.stream().map(Student::getAge).reduce(0, (x, y) -> x + y);
        String reduce1 = students.stream().map(Student::getName).reduce("", (x, y) -> x + "," + y);
        System.out.println("年龄总和 :" +reduce);
        System.out.println("名称"+reduce1);

        /**
         * 收集
         */
        //收集名称 创建一个list 存储
        System.out.println("------------------------------------------");
        List<String> collect6 = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(collect6);

        //年龄大于十岁的 收集名称 创建一个set存储
        System.out.println("------------------------------------------");
        Set<String> collect7 = students.stream().filter(x -> x.getAge() > 10).map(Student::getName).collect(Collectors.toSet());
        System.out.println(collect7);

        System.out.println("------------------------------------------");
        TreeSet<Integer> collect8 = students.stream().map(Student::getAge).filter(x -> x > 5).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect8);

        //平均年龄
        System.out.println("------------------------------------------");
        System.out.println(students.stream().collect(Collectors.averagingInt(Student::getAge)));

        //年龄最大的对象
        System.out.println("------------------------------------------");
        Optional<Student> collect9 = students.stream().collect(Collectors.maxBy((x, y) -> Integer.compare(x.getAge(), y.getAge())));
        System.out.println(collect9);

        //拼接成字符串
        System.out.println("------------------------------------------");
        String collect10 = students.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(collect10);

        //字符串的开头 结尾 中间 自定义
        System.out.println("------------------------------------------");
        String collect11 = students.stream().map(Student::getName).collect(Collectors.joining(",", "--------", "====="));
        System.out.println(collect11);
    }

    /**
     * 求和
     */
    @Test
    public void test01() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }


}
