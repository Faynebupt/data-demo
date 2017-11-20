package demo.repo;

import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 张少昆 on 2017/7/28.
 */

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
@Commit
public class StudentPagingAndSortRepositoryTest {
	@Autowired
	private StudentPagingAndSortRepository repo;

	@Test
	public void save(){
		Student student = new Student();
		student.setName("page");
		student.setAge(17);

		repo.save(student);
	}

	@Test
	public void page(){
		Sort.Direction direction = Sort.Direction.ASC;
		String property = "age";
		//page,index   都是 0-based
		Pageable pagable = new PageRequest(0, 5);  //page
//		Pageable pagable = new PageRequest(0, 5, direction, property);//page and sort
//		Pageable pagable = new PageRequest(2, 5, direction, property);//page and sort
		Page<Student> page = repo.findAll(pagable);

		System.out.println("total elements: " + page.getTotalElements());
		System.out.println("total page: " + page.getTotalPages());
		System.out.println("content:" + page.getContent());
		System.out.println("number:" + page.getNumber());
		System.out.println("number of elements: " + page.getNumberOfElements());
		System.out.println("sige:" + page.getSize());
		System.out.println("sort:" + page.getSort());
	}

	@Test
	public void sort(){
		Sort.Direction direction = Sort.Direction.DESC;
		List<String> property = new ArrayList<>();
		property.add("age");
		property.add("id");
		Sort sort = new Sort(direction, property);

		Iterable<Student> all = repo.findAll(sort);
		all.forEach(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void pageAndSort(){
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pagable = new PageRequest(0, 5, sort);
		Page<Student> page = repo.findAll(pagable);
		System.out.println(page.getContent());
	}
}