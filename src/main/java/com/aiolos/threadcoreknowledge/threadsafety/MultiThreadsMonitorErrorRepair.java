package com.aiolos.threadcoreknowledge.threadsafety;

/**
 * 用工厂模式修复注册监听器数据不一致的错误
 * @author Aiolos
 * @date 2019-09-01 21:37
 */
public class MultiThreadsMonitorErrorRepair {

    int count;
    private EventListener eventListener;

    private MultiThreadsMonitorErrorRepair(MySource source) {

        this.eventListener = new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("\n我得到的数字是" + count);
            }
        };

        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadsMonitorErrorRepair getInstance(MySource source) {
        MultiThreadsMonitorErrorRepair safeListener = new MultiThreadsMonitorErrorRepair(source);
        // 做完所有准备工作再注册上去
        source.registerListener(safeListener.eventListener);
        return safeListener;
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
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event() {
            });
        }).start();
        MultiThreadsMonitorErrorRepair multiThreadsMonitorErrorRepair = MultiThreadsMonitorErrorRepair.getInstance(mySource);
    }
}
