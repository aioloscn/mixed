package com.aiolos.threadcoreknowledge.deadlock;

import java.util.Random;

/**
 * @author Aiolos
 * @date 2019-10-15 10:19
 */
public class LiveLock {

    static class Spoon {
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use() {
            System.out.printf("%s has eaten!", owner.name);
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }
    }

    static class Diner {
        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ": " + spouse.name + " eat first");
                    spoon.setOwner(spouse);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + " finished eating");
                spoon.setOwner(spouse);
            }
        }
    }

    public static void main(String[] args) {

        Diner a = new Diner("a");
        Diner b = new Diner("b");
        Spoon spoon = new Spoon(a);

        new Thread(() -> a.eatWith(spoon, b)).start();
        new Thread(() -> b.eatWith(spoon, a)).start();
    }
}
