package demo.repo;

import demo.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * Created by 张少昆 on 2017/7/29.
 */
@Profile( "executor" )
public interface StudentJpaSpecificationExecutor extends JpaSpecificationExecutor<Student>, Repository<Student, Integer> {
}
