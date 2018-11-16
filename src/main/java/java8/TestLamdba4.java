package java8;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author Wang Chen Peng
 * @date 2018/11/16
 * describe:
 */
class MyMoney{
    private final int money;

    MyMoney(int money){
        this.money=money;
    }

    public void printMoney(Function<Integer,String> moneyFormat ){
        String apply = moneyFormat.apply(money);
        System.out.println(apply);
    }
}
public class TestLamdba4 {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(9999999);
        myMoney.printMoney((i)->{
            return new DecimalFormat("#,###").format(i);
        });
    }

}
