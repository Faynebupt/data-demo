package demo.repo;

import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 张少昆 on 2017/7/29.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
public class StudentJpaSpecificationExecutorTest {
	@Autowired
	private StudentJpaSpecificationExecutor executor;

	@Test
	public void find(){
		Specification<Student> spec = new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//				Root<Student> from = query.from(Student.class);
				Path<Integer> path = root.get("id");//TODO type
				Predicate gt = cb.gt(path, 20);
				Predicate lt = cb.lt(path, 30);

				Predicate and = cb.and(gt, lt);

//				return cb.ge(path,20);
				return and;
			}
		};
//		executor.findOne(spec);
		List<Student> all = executor.findAll(spec);
		System.out.println(all);
		System.out.println(all.size());
	}

}