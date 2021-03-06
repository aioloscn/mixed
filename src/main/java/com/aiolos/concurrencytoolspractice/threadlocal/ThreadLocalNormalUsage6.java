package com.aiolos.concurrencytoolspractice.threadlocal;

/**
 * 演示ThreadLocal用法2：避免一个线程中参数在多个方法中传递
 * @author Aiolos
 * @date 2020-01-02 17:21
 */
public class ThreadLocalNormalUsage6 {

    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {
    public void process() {
        User user = new User("aiolos");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);
        // 既然要重新set，remove()可有可无
        UserContextHolder.holder.remove();
        user = new User("aiolosxhx");
        UserContextHolder.holder.set(user);
        // 但是如果不再使用这个ThreadLocal了，应该主动调用remove防止内存泄露
        UserContextHolder.holder.remove();
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println(user.name);
    }
}

class UserContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User {
    String name;

    public User(String name) {
        this.name = name;
    }
}