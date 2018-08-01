package java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class demo1 {

    private List<Employee> employees= Arrays.asList(
            new Employee("张三", 11, 999.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六", 44, 44.44),
            new Employee("孙七", 55, 9555.99),
            new Employee("钱八", 66, 666.66)
    );
    public static void main(String[] args) {


        Comparator<Integer> bycolor = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                //return o1.compareTo(o2);
                return Integer.compare(o1, o2);
            }
        };


        Comparator<Integer> bycolor2 = (o1, o2) -> o1.compareTo(o2);

        List<Integer> list = new ArrayList<>();
        list.add(321);
        list.add(111);
        // list.add(999);
        list.sort(bycolor2);
        System.out.println(list.toString());

        //需求获取当前公司中员工年龄大于35岁的员工信息
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 11, 999.99),
                new Employee("李四", 222, 222.22),
                new Employee("王五", 33, 333.33),
                new Employee("找六", 44, 44.44),
                new Employee("孙七", 55, 9555.99),
                new Employee("钱八", 66, 666.66)
        );

       /* List<Employee> employees1 = filterEmployees(employees);
        filterEmployees(employees);
        for (Employee employee : employees1) {
            System.out.println(employee);
        }*/

    }


    @Test
    public void test() {
        //需求获取当前公司中员工年龄大于35岁的员工信息
        employees = Arrays.asList(
                new Employee("张三", 11, 999.99),
                new Employee("李四", 222, 222.22),
                new Employee("王五", 33, 333.33),
                new Employee("找六", 44, 44.44),
                new Employee("孙七", 55, 9555.99),
                new Employee("钱八", 66, 666.66)
        );
        List<Employee> employees1 = filterEmployees(employees);
        System.out.println(employees1.toString());
    }

    public List<Employee> filterEmployees(List<Employee> list) {
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    @Test
    public void test1(){
        employees.stream().filter((e) ->e.getSalary() >= 100).limit(2).forEach(System.out::println);
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }

}
