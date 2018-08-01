package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 练习Stream api的中间操作
 */
public class TestStreamApi2 {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 11, 999.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六", 44, 44.44),
            new Employee("孙七", 55, 9555.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六", 44, 44.44),
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

}
