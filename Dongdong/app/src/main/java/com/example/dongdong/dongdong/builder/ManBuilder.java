package com.example.dongdong.dongdong.builder;

/**
 * Created by dongdong on 2017/8/19.
 */

public class ManBuilder implements PerSonBuilder {
    Person person;

    public ManBuilder(){
        person = new Person();
    }


    @Override
    public void builderHead() {
        person.setHead("我是头");
    }

    @Override
    public void builderBody() {

    }

    @Override
    public void builderFoot() {

    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
