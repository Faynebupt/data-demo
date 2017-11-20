package demo.entity;

// import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 张少昆 on 2017/7/26.
 */
@Entity
// @Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column()
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    @Override
    public String toString(){
        return "Student{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", age=" + age +
                       '}';
    }
}
