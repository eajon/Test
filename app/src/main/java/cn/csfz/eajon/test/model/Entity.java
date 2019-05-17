package cn.csfz.eajon.test.model;

import java.io.Serializable;

public class Entity implements Serializable {

    String haha;
    String test;

    public String getHaha() {
        return haha;
    }

    public void setHaha(String haha) {
        this.haha = haha;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}