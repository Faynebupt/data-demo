package demo.repo;

import demo.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * Created by 张少昆 on 2017/7/27.
 */
@Profile("crud")
public interface StudentCrudRepository extends CrudRepository<Student, Integer> {
}
