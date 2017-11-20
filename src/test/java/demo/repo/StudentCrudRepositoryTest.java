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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张少昆 on 2017/7/28.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
@Commit
public class StudentCrudRepositoryTest {
	@Autowired
	private StudentCrudRepository repo;

	@Test
	public void setUp(){
	}

	@Test
	public void save(){
		Student stu = new Student();
		stu.setName("lala");
		stu.setAge(18);
		repo.save(stu);
	}

	@Test
	public void saves(){
		List<Student> list = new ArrayList<>(100);
		for(int i = 0; i < 100; i++){
			Student stu = new Student();
			stu.setName("test-" + i);
			stu.setAge(100 - i);
			list.add(stu);
		}
		repo.save(list);
	}
	@Test
	public void count(){
		System.out.println(repo.count());
		System.out.println(repo.exists(11));
		System.out.println(repo.exists(111));
	}

	@Test
	public void findOne(){
		int id=11;
		Student one = repo.findOne(id);
		System.out.println(one);
	}

	@Test
	public void findAll(){
		List<Integer> ids=new ArrayList<>(5);
		ids.add(15);
		ids.add(33);
		ids.add(77);
		Iterable<Student> all = repo.findAll(ids);
		System.out.println(all);
	}

	@Test
	public void findAll2(){
		Iterable<Student> all = repo.findAll();
		System.out.println(all);
	}
}