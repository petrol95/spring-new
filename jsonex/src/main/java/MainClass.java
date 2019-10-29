import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {

        // from JSON to Object
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(new File("jsonex/stud.json"), Student.class);
        System.out.println(student);

        // from Object to JSON
//        Student student = new Student();
//        student.setId(2);
//        student.setName("John");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//        mapper.writeValue(new File("jsonex/output.json"), student);

    }
}
