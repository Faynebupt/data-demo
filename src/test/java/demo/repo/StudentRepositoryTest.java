package demo.repo;

import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 张少昆 on 2017/7/29.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
@Commit
public class StudentRepositoryTest {
	@Autowired
	private StudentRepository repo;

	@Test
	public void find(){
		Student stu=new Student();
		stu.setId(3);
		stu.setName("wander");
		stu.setAge(77);
//		repo.updateNameAndAge(stu);
		repo.save(stu);
	}
}