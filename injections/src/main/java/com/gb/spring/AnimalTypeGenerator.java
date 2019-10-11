package com.gb.spring;

public class AnimalTypeGenerator implements ObjectTypeGenerator {

    public String getObjectType() {
        String[] animalTypes = {"Cat", "Dog", "Horse", "Duck"};
        return animalTypes[(int) (Math.random() * animalTypes.length)];
    }

}
