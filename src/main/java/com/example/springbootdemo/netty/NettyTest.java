package com.example.springbootdemo.netty;

import io.netty.util.concurrent.FastThreadLocal;

public class NettyTest {


    final class FastThreadLocalTest extends FastThreadLocal<Object> {
        @Override
        protected Object initialValue() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            return new Object();
        }
    }

    private final FastThreadLocalTest fastThreadLocalTest;

    public NettyTest(){
        fastThreadLocalTest = new FastThreadLocalTest();
    }

    public static void main(String[] args){

        NettyTest fastThreadLocalDemo = new NettyTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Object obj  = fastThreadLocalDemo.fastThreadLocalTest.get();
                try {
                    for (int i=0;i<10;i++){
                        fastThreadLocalDemo.fastThreadLocalTest.set(new Object());
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"ThreadA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object obj  = fastThreadLocalDemo.fastThreadLocalTest.get();
                    for (int i=0;i<10;i++){
                        System.out.println(obj == fastThreadLocalDemo.fastThreadLocalTest.get());
                        Thread.sleep(1000);
                    }
                }catch (Exception e){

                }
            }
        },"ThreadB").start();
    }
}
