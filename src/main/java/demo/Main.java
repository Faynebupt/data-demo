package demo;

import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import demo.repo.StudentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by 张少昆 on 2017/7/27.
 */
public class Main {
    public static void main(String[] args){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataSourceFineConfig.class);
        StudentRepository repo = applicationContext.getBean(StudentRepository.class);

        Student student = new Student();
        student.setName("萝莉");
        student.setAge(13);
        student = repo.save(student);
        System.out.println(student);

    }
}
