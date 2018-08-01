package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 映射
 * map 接收lambda,将元素转换成其他形式或提取信息想.接收一个函数作为参数,该函数会被应用到每个元素上,
 * 并将其映射成一个新的元素
 * flatMap 接收一个函数作为参数,并将流中的每个值都换成另一个流,然后把所有流连接成一个流
 */
public class TestStreamApi3 {

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
     * map映射 把流中的每个元素都应用到函数上 并生成新的流
     */
    @Test
    public void test1(){
        List<String> strings = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        strings.stream()
                .map((s)->s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("--------------------");

        employees.stream()
                .map(Employee::getName)
                .distinct()
                .forEach(System.out::println);
    }

}
