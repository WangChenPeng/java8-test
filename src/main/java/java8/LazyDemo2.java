package java8;


import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

/**
 * @author Wang Chen Peng
 * @date 2018/11/16
 * describe:
 */
class MyLazy {
    private final IntSupplier data;

    MyLazy(IntSupplier data) {
        this.data = data;
    }

    public MyLazy some(IntUnaryOperator op) {
        return new MyLazy(() -> op.applyAsInt(this.data.getAsInt()));
    }

    public int Value() {
        return this.data.getAsInt();
    }
}

public class LazyDemo2 {
    public static void main(String[] args) {
        MyLazy lazy = new MyLazy(() -> 10);
        System.out.println("没有调用value最终操作，中间操作的日志不应该打印");
        // 我们要实现不调用最终操作value的情况下
        // 中间操作some存入的函数并不会真正执行
        lazy.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber);
        System.out.println("调用value最终操作");
        int value = lazy.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber).Value();
        System.out.println("value  " + value);
        System.out.println("------------");
        //方法引用时  只有一个入参和出参的时候 可以省略()->
        //peek 经常用作debug
        // 假设流里面不调用最终操作，那么所有的中间操作都不会真正被执行
        // 这就是流的惰性求值
        // 下面的代码如果没有 foreach 不执行sum这种最终操作
        IntStream.rangeClosed(1, 10).peek(System.out::println).map(LazyDemo2::doubleNumber).forEach(System.out::println);


    }

    public static int doubleNumber(int i) {
        System.out.println("方法被调用了");
        return i * 2;
    }

}
