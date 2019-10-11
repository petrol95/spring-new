package com.gb.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class JavaCodeCreator implements CodeCreator {
    private ObjectTypeGenerator classNameGenerator;
    private String author;

    public ObjectTypeGenerator getClassNameGenerator() {
        return classNameGenerator;
    }

    public void setClassNameGenerator(ObjectTypeGenerator classNameGenerator) {
        this.classNameGenerator = classNameGenerator;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public JavaCodeCreator() {
    }

    public String getClassExample() {
        return "@autor:" + author +"\n public class " + classNameGenerator.getObjectType() + "\n{ \n}";
    }
}
