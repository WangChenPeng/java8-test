package java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * 方法引用: 若lambda体中的内容 有方法已经实现了 , 我们可以使用 "方法引用"
 *          (可以理解为方法引用是 lambda表达式的另外一种表现形式)
 *  主要有三种语法格式
 *
 *  对象::示例方法名
 *
 *  类::静态方法名
 *
 *  类::实例方法名
 *
 *  注意:
 *      1 lambda体中调用方法的参数列表与返回值类型,要与函数式接口中抽象方法的函数列表和 返回值保持一致
 *      2 如果lambda参数列表中第一个参数时实例方法的调用者 第二个参数时实例方法的参数时
 *        可以用如下语法 ClassName :: method
 *
 *  构造器引用:
 *  格式:
 *          ClassName::new
 *  注意:
 *          需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 *  数组引用:
 *  格式
 *          Tpye :: new
 *
 *
 */
public class TestMethodRef {

    /**
     * 对象::示例方法名
     */
    @Test
    public void test01(){

        Consumer<String> con = (x)-> System.out.println(x);
        con.accept("1");
        // 若lambda体中的内容 有方法已经实现了 , 我们可以使用 "方法引用"
        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;
        con1.accept("2");
        Consumer<String> con2 = (x)-> ps.println(x);
        con2.accept("3");
        Consumer<String> con3 = System.out::println;
        con3.accept("4");

    }

    @Test
    public void test2(){
        Employee employee = new Employee();
        employee.setName("123");
        employee.setAge(99);
        Supplier<String> sup = () -> employee.getName();
        String s = sup.get();
        System.out.println(s);

        Supplier<Integer> sup1 = employee::getAge;
        Integer integer = sup1.get();
        System.out.println(integer);
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (x,y)-> Integer.compare(x, y);
        int compare = com.compare(9, 7);
        System.out.println(compare);

        Comparator<Integer> com1 =Integer::compare;
    }

    /**
     * 对象::实例方法名
     */
    @Test
    public void test4(){
        BiPredicate<String,String> bp =   (x,y)->x.equals(y);
        System.out.println(bp.test("1233", "123"));

        //如果第一个参数时实例方法的调用者 第二个参数时实例方法的参数时 可以用如下语法
        BiPredicate<String,String> bp1 = String::equals;
    }

    /**
     * 无参构造器引用
     */
    public void test5(){
        //之前的写法
        Supplier<Employee> sup = ()->new Employee();
        //构造器引用的写法
        Supplier<Employee> sup1 =Employee::new;
        Employee employee = sup1.get();
        System.out.println(employee);
    }
    /**
     * 一个,两个 参数的构造器
     */
    @Test
    public void test6(){
        //之前用lambda语法实现 一个参数
        Function<Integer,Employee> fun = (x) -> new Employee(x);
        Employee apply = fun.apply(97);
        System.out.println(apply);

        //用构造器引用实现 一个参数
        Function<Integer,Employee> fun1 = Employee::new ;
        Employee apply1 = fun1.apply(66);
        System.out.println(apply1);

        //用构造器引用实现 两个参数
        BiFunction<Integer,Double,Employee> fun2 = Employee::new;
        Employee apply2 = fun2.apply(55, 5.5);
        System.out.println(apply2);
    }

    /**
     * 数组引用
     */
    @Test
    public void test7(){
        Function<Integer,String[]> fun = (x) -> new String[x];
        String[] apply = fun.apply(6);
        System.out.println(apply.length);

        Function<Integer,String[]> fun1 = String[] :: new;
        String[] apply1 = fun1.apply(5);
        System.out.println(apply1.length);
    }
}
