package java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLambda2 {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 11, 999.99),
            new Employee("李四", 222, 222.22),
            new Employee("王五", 33, 333.33),
            new Employee("找六", 44, 44.44),
            new Employee("孙七", 55, 9555.99),
            new Employee("钱八", 66, 666.66)
    );

    @Test
    public void test1() {
        Collections.sort(employees, (e1, e2) -> {
            //如果年龄一样
            if (e1.getAge() == e2.getAge()) {
                //如果年龄一样按照姓名比较
                return e1.getName().compareTo(e2.getName());
            }
            //如果年龄不一样
            else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        //如果年龄不一样 按照年龄对比
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //需求用户处理字符串的方法
    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    /**
     * 将字符串去空格 并将小写转换为大写
     */
    @Test
    public void test2() {
        String newStr1 = strHandler("java8   ", (str) -> str.trim().toUpperCase());
        System.out.println(newStr1);

        String newStr2 = strHandler("java8   ", (str) -> str.trim().substring(0, 4));
        System.out.println(newStr2);
    }

    //需求对于两个Long数据进行处理
    public void op(Long L1,Long L2,MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(L1, L2));
    }

    @Test
    public void test3(){
        op(100L,200L,(x,y)->x+y);
        op(100L,200L,(x,y)->x*y);
    }
}
