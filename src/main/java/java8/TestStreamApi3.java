package java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void test2(){
        String msg = "hello";
        String msg2 =null;
        String msg3 =null;
        //只能传入非null
        Optional<String> optional = Optional.of(msg);
        //可以传入null
        Optional<String> optional2 = Optional.ofNullable(msg2);
        Optional<String> optional3 = Optional.ofNullable(msg3);

        /*// 判断是否有值，不为空
        boolean present = optional.isPresent();
        // 如果有值，则返回值，如果等于空则抛异常
        String value = optional.get();
        // 如果为空，返回else指定的值
        String hi = optional.orElse("hi");
        // 如果值不为空，就执行Lambda表达式
        optional.ifPresent(opt -> System.out.println(opt));*/

        //如果非null 则执行其中的代码
        optional.ifPresent((value)-> System.out.println("youzhi :" + value));
        optional2.ifPresent((value)-> System.out.println("youzhi :" + value));

        //是否存在
        if (!optional3.isPresent()){
            System.out.println("空");
        }

        //有值则是他的值 没有值则是指定的值
        System.out.println(optional.orElse("123"));
        System.out.println(optional3.orElse("123"));

        //orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，orElseGet方法可以接受Supplier接口的实现用来生成默认值
        System.out.println(optional.orElseGet(() -> System.currentTimeMillis() + ""));
        System.out.println(optional3.orElseGet(() -> System.currentTimeMillis() + ""));

        //在orElseGet方法中，我们传入一个Supplier接口。然而，在orElseThrow中我们可以传入一个lambda表达式或方法，如果值不存在来抛出异常。
        optional.orElseThrow(IllegalAccessError::new);
        //optional3.orElseThrow(IllegalAccessError::new);


        //flatMap flatMap flatMap flatMap

        String str = "abc,def";
        List<String> list = Arrays.asList("1,2,3", "11,22,33", "44,55,66", "77,88,99");
        List<String> list1 = Arrays.asList("123", "456", "789", "098");
        System.out.println("-----------------------------");
        list.stream().flatMap(x->list1.stream().map(y->x+y+" ")).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("----------------------------");
        list.stream().flatMap(x->Arrays.stream(x.split(","))).collect(Collectors.toList()).forEach(System.out::println);
    }

}
