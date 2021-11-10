import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Project name(项目名称)：菱形语法
 * Package(包名): PACKAGE_NAME
 * Class(类名): test
 * Author(作者）: mao
 * Author QQ：1296193245
 * Date(创建日期)： 2021/11/10
 * Time(创建时间)： 18:09
 * Version(版本): 1.0
 * Description(描述)：
 * 在 Java 7 版本以前，如果使用带泛型的接口、类定义变量，那么调用构造器创建对象时构造器的后面也必须带泛型，这显得有些多余了。例如如下两条语句：
 * List<String> strList = new ArrayList<String>();
 * Map<String, Integer> scores = new HashMap<String, Integer>();
 * 上面两条语句中等号右边的尖括号部分完全是多余的，Java 7 版本以前是必需的，不能省略。从 Java 7 开始，
 * Java 允许在构造器后不带完整的泛型信息，只要给出一对尖括号<>即可。Java 可以推断出尖括号里应该是什么泛型信息。
 * 即上面两条语句可以改写为如下形式：
 * List<String> strList = new ArrayList<>();
 * Map<String, Integer> scores = new HashMap<>();
 * 把两个尖括号并排放在一起非常像一个菱形，这种语法也就被称为“菱形”语法。
 */

interface Foo<T>
{
    void test1(T t);
}

@SuppressWarnings("all")
public class test
{
    public static int getIntRandom(int min, int max)         //空间复杂度和时间复杂度更低
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }

    public static int getIntRandom1(int min, int max)          //获取int型的随机数
    {
        if (min > max)
        {
            min = max;
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args)
    {
        //------------------------------------------------------
        long startTime = System.nanoTime();   //获取开始时间
        //------------------------------------------------------
// Java自动推断出ArrayList的<>里应该是Integer
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
        {
            list.add(getIntRandom(1000, 5000));
        }
        for (int i = 0; i < 1000; i++)
        {
            if (i % 15 == 0 && i > 0)
            {
                System.out.println();
            }
            System.out.print(list.get(i) + "  ");
        }
        list.sort((int1, int2) -> int2 - int1);
        System.out.println();
        System.out.println();
        for (int i = 0; i < 1000; i++)
        {
            if (i % 15 == 0 && i > 0)
            {
                System.out.println();
            }
            System.out.print(list.get(i) + "  ");
        }
        System.out.println();

        HashMap<String, ArrayList<String>> bookInfo = new HashMap<>();
        ArrayList<String> books = new ArrayList<>();
        books.add("操作系统");
        books.add("高等数学");
        books.add("大学英语");
        books.add("数据结构");
        bookInfo.put("教材类", books);
        ArrayList<String> books2 = new ArrayList<>();
        books2.add("安徒生童话");
        books2.add("格林童话");
        books2.add("伊索寓言");
        bookInfo.put("故事类", books2);
        System.out.println(bookInfo);    //遍历方式1
        bookInfo.forEach((key, value) -> System.out.println(key + ":  " + value));   //遍历方式2
        for (String s : bookInfo.keySet())      //遍历方式3
        {
            System.out.println(s + ":");
            ArrayList<String> arrayList = bookInfo.get(s);
            for (String ss : arrayList)
            {
                System.out.print(ss + "  ");
            }
            System.out.println();
        }
        // 指定Foo类中泛型为String
        Foo<String> f = new Foo<>()
        {
            // test()方法的参数类型为String
            public void test1(String t)
            {
                System.out.println("test 方法的 t 参数为：" + t);
            }
        };
        // 使用泛型通配符，此时相当于通配符的上限为Object
        Foo<?> fo = new Foo<>()
        {
            // test()方法的参数类型为Object
            public void test1(Object t)
            {
                System.out.println("test 方法的 Object 参数为：" + t);
            }
        };
        // 使用泛型通配符，通配符的上限为Number
        Foo<? extends Number> fn = new Foo<>()
        {
            // 此时test ()方法的参数类型为Number
            public void test1(Number t)
            {
                System.out.println("test 方法的 Number 参数为：" + t);
            }
        };
        System.out.println();
        //------------------------------------------------------
        long endTime = System.nanoTime(); //获取结束时间
        if ((endTime - startTime) < 1000000)
        {
            double final_runtime;
            final_runtime = (endTime - startTime);
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "微秒");
        }
        else if ((endTime - startTime) >= 1000000 && (endTime - startTime) < 10000000000L)
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 1000;
            final_runtime = final_runtime / 1000;
            System.out.println("算法运行时间： " + final_runtime + "毫秒");
        }
        else
        {
            double final_runtime;
            final_runtime = (endTime - startTime) / 10000;
            final_runtime = final_runtime / 100000;
            System.out.println("算法运行时间： " + final_runtime + "秒");
        }
        Runtime r = Runtime.getRuntime();
        float memory;
        memory = r.totalMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("JVM总内存：%.3fMB\n", memory);
        memory = r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf(" 空闲内存：%.3fMB\n", memory);
        memory = r.totalMemory() - r.freeMemory();
        memory = memory / 1024 / 1024;
        System.out.printf("已使用的内存：%.4fMB\n", memory);
        //------------------------------------------------------
    }
}
