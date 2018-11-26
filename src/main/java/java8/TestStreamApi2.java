package java8;

import com.sun.corba.se.impl.ior.iiop.RequestPartitioningComponentImpl;
import com.sun.org.apache.xml.internal.utils.StringComparable;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

/**
 * 练习Stream api的中间操作
 */
public class TestStreamApi2 {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 11, 999.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六", 44, 44.44),
            new Employee("孙七qi", 55, 9555.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六liu", 44, 44.44),
            new Employee("孙七", 55, 9555.99),
            new Employee("钱八", 66, 666.66)
    );

    /**
     * 筛选与切片
     *
     */
    @Test
    public void test1(){
        //中间操作 过滤年龄大于55的 内部迭代 迭代操作由 Stream api 完成
        Stream<Employee> stream = employees.stream()
                .filter((e) -> {
                                    System.out.println("中间操作");
                                    return e.getAge()>55;
                                });

        // 终止操作 后一次性执行所有操作 即为 惰性求值
        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //首先获取流 然后过滤 条件为工资大于100 并分页输出2条记录
        //limit短路可以用于提高效率
        Stream<Employee> limit = employees.stream()
                .filter((e)->{
                    System.out.println("短路");
                    return e.getSalary()>100 ;
                })
                .limit(8);

        //终止操作后输出流的结果
        limit.forEach(System.out::println);
    }

    @Test
    public void test3(){
        //获取流后首先过滤 然后忽略前两条数据 后并遍历输出
        employees.stream()
                .filter((e)->e.getSalary()>100)
                .skip(2)
                .forEach(System.out::println);
    }


    @Test
    public void test4(){
        //去重根据hashCode和equals来判断 要重写方法
        employees.stream()
                .filter((e)->e.getSalary()>100)
                .skip(2)
                .distinct()
                .forEach(System.out::println);

    }

    /**
     * 数据分块
     */
    @Test
    public void test5(){
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 40));
        collect.forEach((key, value) -> {
            System.out.print(key+"    ");
            System.out.println(value);
        });
    }

    @Test
    public void test7(){
        Map<Boolean, List<Employee>> collect = employees.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 40, toList()));
        collect.forEach((key,value)->{
            System.out.print(key+"   ");
            System.out.println(value);
        });

    }

    /**
     * 分组
     * 统计数量
     */
    @Test
    public void test6(){
        Map<Integer, Long> collect = employees.stream().collect(Collectors.groupingBy(Employee::getAge, counting()));
        collect.forEach((key,value)->{
            System.out.print(key+"   ");
            System.out.println(value);
        });
    }

    /**
     * 先根据年龄分组然后提取名称到list
     */
    @Test
    public void test8(){
        Map<Integer, List<String>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getAge, Collectors.mapping(Employee::getName, Collectors.toList())));

        collect.forEach((x,y)->{
            System.out.print(x);
            System.out.println(y);
        });
    }

    @Test
    public void test9(){
        //改变流中的数据 再转换成int流 获取其中的最大值 判断是否有数据 如果有则打印
        employees.parallelStream().map(Employee::getName).mapToInt(e->e.length()).max().ifPresent(System.out::println);
    }

    @Test
    public void test10(){
        int n=100;
        int[] ints = new int[n];
        Arrays.parallelSetAll(ints, i->i);
        Arrays.stream(ints).forEach(System.out::print);

    }

}
