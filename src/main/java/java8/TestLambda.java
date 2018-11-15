package java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;

/**
 * 左侧 lamdba表达式的参数列表
 * 右侧 lamdba表达式所需要执行的功能 即lamdba体
 */
public class TestLambda {

    /**
     * Lamdba 语法格式1
     * 没有参数并无返回值
     */
    @Test
    public void test1(){
        Runnable r = () -> System.out.println("hello lamdba");
        String str = "123456789";
        Runnable runnable = () -> System.out.println("123");
        runnable.run();

        r.run();
    }

    /**
     * Lamdba 语法格式2
     * 没有参数并无返回值
     */
    @Test
    public void test2(){
        Consumer<String> stringConsumer = (x) -> System.out.println(x);
        Consumer<String> tConsumer = (x) -> System.out.println(x);
        tConsumer.accept("9999");
        stringConsumer.accept("java8好用");
    }

    /**
     * Lamdba 语法格式3
     * 有一个参数并无返回值
     */
    @Test
    public void test3(){
        Consumer<String> stringConsumer = x -> System.out.println(x);
        Consumer<String> tConsumer = x -> System.out.println(x);
        tConsumer.accept("9999");
        stringConsumer.accept("java8好用");
    }

    /**
     * Lamdba 语法格式4
     * 有两个以上的参数 并且lamdba体重有多条语句 并且有返回值
     * 如果lamdba有多条语句 必须使用大括号
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = (o1,o2) -> {
            System.out.println("函数式接口");
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator.compare(3, 2));

    }

    /**
     * Lamdba 语法格式5
     * 若 lamdba体重只有一条语句 return 和{} 可以省略不写
     */
    @Test
    public void test5(){
        Comparator<Integer> comparator = (x,y)-> Integer.compare(x, y);

    }

    /**
     * Lamdba 语法格式6
     * 若 lamdba体重只有一条语句 return 和{} 可以省略不写
     * lamdba表达式的参数列表的数据类型可以省略不写,因为jvm编译器可以通过上下文
     * 推断出数据类型 即 类型推断
     * 如果写了参数类型 所有参数都要写参数类型
     */
    @Test
    public void test6(){
        Comparator<Integer> comparator = (x,y)-> Integer.compare(x, y);

    }

    @Test
    public void test7(){
       Integer num =  operation(100, (x) -> x/10);
        System.out.println(num);

    }

    public Integer operation(Integer num,MyFun mf){
        return mf.getValue(num);
    }
    public static String operationFun(Integer num,MyFunTest mf){
        System.out.println(mf.testFun("111", "222"));
        return mf.testFun("111", "222");
    }

    public static void main(String[] args) {
        MyFunTest myFunTest = (s1, s2) -> s1 + s2;
        String s = operationFun(1, myFunTest);
        System.out.println(s);
    }
}
