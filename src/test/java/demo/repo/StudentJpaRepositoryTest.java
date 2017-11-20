package demo.repo;

import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 张少昆 on 2017/7/29.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
public class StudentJpaRepositoryTest {
	@Autowired
	private StudentJpaRepository repo;

	@Test
	public void findOne(){
		Student one = repo.findOne(11);
		System.out.println(one);

//		one=repo.getOne(12);
//		System.out.println(one);

		Student prob = new Student();
		prob.setAge(88);
		prob.setId(11);
//		ExampleMatcher matcher= ExampleMatcher.matching();//匹配所有非空字段，且AND组合
		ExampleMatcher matcher = ExampleMatcher.matchingAll();//匹配所有非空字段
//		ExampleMatcher matcher = ExampleMatcher.matchingAny();//匹配所有非空字段

		Example<? extends Student> example = Example.of(prob, matcher);
		one = repo.findOne(example);
		System.out.println(one);
	}

	@Test
	public void findAll(){
		Student prob = new Student();
		prob.setAge(88);
		prob.setId(11);
		prob.setName("TEST-1");
//		ExampleMatcher matcher= ExampleMatcher.matching();//匹配所有非空字段，且AND组合  -- 就是matchingAll
//		ExampleMatcher matcher= ExampleMatcher.matchingAll();//匹配所有非空字段，且AND组合
		ExampleMatcher matcher = ExampleMatcher.matchingAny();//匹配所有非空字段，OR组合
//		matcher.withIgnoreCase("name");//忽略某个或某些字段的大小写 -- 看起来默认就忽略
		ExampleMatcher exampleMatcher = matcher.withIgnoreCase(false);//没效果啊
		ExampleMatcher id = matcher.withIgnorePaths("id");//可行

//		Example<? extends Student> example = Example.of(prob, matcher);
//		Example<? extends Student> example = Example.of(prob, exampleMatcher);
		Example<? extends Student> example = Example.of(prob, id);

		List<? extends Student> all = repo.findAll(example);
		System.out.println(all);

	}


	@Test
	public void allSql(){
		List<Student> all = repo.allSql();
		System.out.println(all);
		System.out.println(all.size());
	}

	@Test
	public void allHql(){
		List<Student> students = repo.allHql();
		System.out.println(students);
		System.out.println(students.size());
	}

	@Test
	public void by(){
		Student test = repo.byName("test-1");
		System.out.println(test);

		Student student = repo.byIndex("test-77");
		System.out.println(student);
	}

	@Test
	public void modify(){
		Integer no = repo.modify("modify-test33", 34, 33);
		System.out.println(no);
		Student one = repo.findOne(33);
		System.out.println(one);
	}
}