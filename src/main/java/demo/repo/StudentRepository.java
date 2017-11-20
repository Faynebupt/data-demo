package demo.repo;

import demo.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by 张少昆 on 2017/7/27.
 */
@Profile("repo")
public interface StudentRepository extends Repository<Student, Integer> {
	List<Student> findByName(String name);

	@Transactional
	Student save(Student student);//FIXME

	@Transactional
	@Modifying
	void updateNameAndAge(Student student);// why not work??? FIXME

}
