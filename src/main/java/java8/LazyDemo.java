package java8;

import java.util.function.Supplier;

/**
 * @author Wang Chen Peng
 * @date 2018/11/16
 * describe:
 */
class  Log{
    public boolean isDebug(){
        return true;
    }
    public void debug (String str){
        if (this.isDebug()){
            System.out.println(str);
        }
    }
    public void debug(Supplier<String> str){
        if (this.isDebug()){
            System.out.println(str.get());
        }
    }
}


public class LazyDemo {
    public static void main(String[] args) {
        LazyDemo lazyDemo = new LazyDemo();
        Log log = new Log();
        log.debug(()->"打印日志之前必须判断日志级别: "+lazyDemo.toString());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
