package java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * java8中内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 *          void accept(T t)
 * Supplier<T> : 供给型接口
 *          T get();
 * Function<T,R> : 函数型接口
 *          R apply(T t)
 * Predicate<T> : 断言型接口
 *          bollean test(T t)
 */
public class TestLambda3 {

    @Test
    public void test05(){
        String newStr = testlambda("java lambda ", "好用", (str1,str2)->str1+str2);
        System.out.println(newStr);
    }

    public String testlambda(String x, String y, BinaryOperator<String> bo){
        return bo.apply(x, y);
    }


    /**
     * Predicate<T> 断言型接口
     */
    @Test
    public void test04(){
        List<String> strings = Arrays.asList("adfa","zxcv","qerqwerq","dfgdfhdfhg");
        List<String> str1 = filterStr(strings, (str) -> str.length()>5);
        System.out.println(str1.toString());

    }

    /**
     * 需求 将满足条件的字符串放入集合中
     */
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        ArrayList<String> strings = new ArrayList<>();
        for (String string : list) {
            if (pre.test(string)){
                strings.add(string);
            }
        }
        return strings;
    }


    /**
     * Function<T,R> 函数型接口
     */
    @Test
    public void test03(){
        String str1 = strHandler("\t\t\t\t\t\t\t   java8 肥肠 好用", (str)->str.trim());
        System.out.println(str1);

        String str2 = strHandler("\t\t\t\t\t\t\t   java8 肥肠 好用", (str)-> str.trim().substring(0, 5));
        System.out.println(str2);
    }

    /**
     * 需求用于处理字符串
     */
    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    /**
     * Supplier<T> 供给型接口
     */
    @Test
    public void test2(){
        List<Integer> list = getNumList(10, ()->new Random().nextInt(101));
        System.out.println(list.toString());
    }

    /**
     * 需求 产生指定个数个整数 并放入集合中
     */
    public List<Integer> getNumList(int num , Supplier<Integer> supplier){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i=0;i<num;i++){
            Integer integer = supplier.get();
            integers.add(integer);
        }
        return integers;
    }


    /**
     * Consumer<T> 消费型接口
     */
    @Test
    public void test1(){

        happy(10000, (m)-> System.out.println("大保健消费"+m+"元"));

    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }


}
