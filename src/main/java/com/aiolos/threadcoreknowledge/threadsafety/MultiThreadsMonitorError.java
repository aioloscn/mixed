package com.aiolos.threadcoreknowledge.threadsafety;

/**
 * 观察者模式
 * @author Aiolos
 * @date 2019-09-01 20:33
 */
public class MultiThreadsMonitorError {

    int count;

    // 过早发布，count还没更新
    public MultiThreadsMonitorError(MySource source) {

        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是" + count);
            }
        });

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener eventListener) {
            this.listener = eventListener;
        }

        void eventCome(Event e) {
            if (listener != null) {
                listener.onEvent(e);
            } else {
                System.out.println("\n还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {}

    public static void main(String[] args) {

        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {
                });
            }
        }).start();
        MultiThreadsMonitorError multiThreadsMonitorError = new MultiThreadsMonitorError(mySource);
    }
}
