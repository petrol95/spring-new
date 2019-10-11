package com.gb.spring;

public class VBNetCodeCreator implements CodeCreator {
    private ObjectTypeGenerator animalTypeGenerator;

    public VBNetCodeCreator(ObjectTypeGenerator animalTypeGenerator) {
        this.animalTypeGenerator = animalTypeGenerator;
    }

    public String getClassExample() {
        return "Public Class " + animalTypeGenerator.getObjectType() + "()\n   \nEnd Class";
    }
}
