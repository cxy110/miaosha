package com.miaosha.vo;

/**
 * @program: miaosha
 * @description:
 * @author: cxy
 * @create: 2019-09-20 17:11
 */
public class test {
  public static void main(String[] args) {
    Integer a = new Integer("5");
    Integer b = new Integer("5");
    Integer c=5;
    int d=5;
    Integer e=128;
    int f=128;
    System.out.println(a==b);
    System.out.println(b==c);
    System.out.println(b==d);

    System.out.println(c==b);
    System.out.println(e==f);
    System.out.println(c==d);
  }
}
