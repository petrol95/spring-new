package com.gb.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.gb.spring")
public class JavaConfig {

    @Bean(name = "animalTypeGenerator")
    public ObjectTypeGenerator animalTypeGenerator() {
        return new AnimalTypeGenerator();
    }

    @Bean(name = "javaCodeCreator")
    public CodeCreator javaCodeCreator(ObjectTypeGenerator animalTypeGenerator) {
        CodeCreator codeCreator = new JavaCodeCreator();
        ((JavaCodeCreator)codeCreator).setClassNameGenerator(animalTypeGenerator);
        return codeCreator;
    }
}
