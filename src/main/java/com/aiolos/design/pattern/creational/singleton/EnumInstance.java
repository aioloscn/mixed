package com.aiolos.design.pattern.creational.singleton;

/**
 * @author aiolos
 * 2018-11-12
 */
public enum EnumInstance {

    INSTANCE {
        protected void printText() {
            System.out.println("aiolos");
        }
    };
    protected abstract void printText();
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumInstance getInstance() {
        return INSTANCE;
    }
}
