package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Wang Chen Peng
 * @date 2018/11/16
 * describe:
 */
public class CreateStreamDemo {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();

        /**
         * 从集合创建
         */
        //串行流
        list.stream();
        //并行流
        list.parallelStream();

        /**
         * 从数组创建
         */
        Arrays.stream(new String[] {"111","2222","3333"});

        /**
         * 创建数字流
         */
        //固定int的流
        IntStream.of(1,2,3);
        //一个区间内的流
        IntStream.rangeClosed(1, 100);

        /**
         * random 无限流
        */
        IntStream limit = new Random().ints().limit(100);

        /**
         * 自己产生流
         */
        Random random = new Random();
        Stream<Integer> limit1 = Stream.generate(() -> random.nextInt()).limit(100);
    }
}
