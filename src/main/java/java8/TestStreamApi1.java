package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Stream 的三个操作步骤:
 *      1创建Stream
 *      2中间操作
 *      3终止操作
 */
public class TestStreamApi1 {

    @Test
    public void test1(){
        //1 创建Stream 可以通过Collection系列集合提供的Stream方法或parallelStream方法获取流
        //Stream() 获取的是串行流 parallelStream()获取的是并行流
        List<String> list = new ArrayList<>();

        //1.1 获取串行流
        Stream<String> stream = list.stream();

        //1.2 通过Arrays中的静态方法 Stream获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        //1.3 通过Stream中的静态方法 of 来获取流
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        //1.4 创建无限流
        //1.4.1 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x+2);
        //stream3.limit(1000).forEach(System.out::println);

        //1.4.2 生成
        Stream<Integer> stream4 = Stream.generate(()->new Random().nextInt(101));
        stream4.forEach(System.out::println);
    }

    @Test
    public void test02(){
        Stream<Integer> iterate = Stream.iterate(0, x -> x +1);
        iterate.limit(10).filter(x->x%2==0).forEach(System.out::println);
    }

}
