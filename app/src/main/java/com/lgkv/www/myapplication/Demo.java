package com.lgkv.www.myapplication;

import java.util.List;

/**
 * Created by wudi on 17-4-6.
 */

public class Demo {
    private String id;
    private String name;
    private List<EntityDemo> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityDemo> getList() {
        return list;
    }

    public void setList(List<EntityDemo> list) {
        this.list = list;
    }

    public static class EntityDemo {
        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        private String age;
    }

}
