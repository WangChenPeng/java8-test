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
    String str = "my name is 007";

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

    /**
     * 把每个单词的长度调用出来
     */
    @Test
    public void test03(){
        Stream.of(str.split(" ")).filter(s->s.length()>2).map(s->s.length()).forEach(System.out::println);
    }

    /**
     * flatMap A->B属性(是个集合), 最终得到所有的A元素里面的所有B属性集合
     * intStream/longStream 并不是Stream的子类, 所以要进行装箱 boxed
     */
    @Test
    public void test04(){
        Stream.of(str.split(" ")).flatMap(s->s.chars().boxed()).forEach(i->System.out.println((char) i.intValue()));
    }


    @Test
    public void test05(){
        new Random().ints().filter(i->i>100&&i<120).limit(10).forEach(System.out::println);
    }

    /**
     * 性能顺序：lambda parallelStream().forEach()>lambda stream().forEach()≈lambda forEach()>classical iterator≈classical forEach>classical for
     */
    @Test
    public void test06(){
        long start = System.currentTimeMillis();
        //并行流并不保证顺序
        str.chars().parallel().forEach(x->System.out.println((char) x));
        System.out.println(System.currentTimeMillis() - start);
        //在并行流的情况下 使用forEachOrdered 来保证顺序
        str.chars().parallel().forEachOrdered(x->System.out.println((char) x));
        System.out.println(System.currentTimeMillis() - start);


    }
}
