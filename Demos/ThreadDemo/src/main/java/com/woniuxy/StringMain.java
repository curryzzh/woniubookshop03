package com.woniuxy;

public class StringMain {

    public static void main(String[] args) {

        /*
        字符串常量,就是直接双引号也字符串的形式,
            会放到常量池中

         */

        // a==b  true   原因: 都是常量池中的对象
        String a = "woniu";
        String b = "woniu";

        // c==d  false   原因: 通过构造函数时会强制开辟内存,所以结果的内存地址不相等
        String c = new String("woniu");
        String d = new String("woniu");

        // e==f  true   原因: .intern() 强制返回常量池中的对象
        String e = new String("woniu").intern();
        String f = new String("woniu").intern();

        // 对象之间用 ==  比的是内存地址是否一样
        System.out.println("a==b "+ (a==b) );
        System.out.println("c==d "+ (c==d) );
        System.out.println("e==f "+ (e==f) );

        System.out.println(new Object());

    }

}
