import demo.config.AppConfig;
import demo.config.DataSourceFineConfig;
import demo.entity.Student;
import demo.repo.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by 张少昆 on 2017/7/27.
 */
//指定测试类的运行者
@RunWith( SpringJUnit4ClassRunner.class )  //FIXME 测试环境下，自动回滚事务！！！！！
//指定spring配置类
@ContextConfiguration( classes = {AppConfig.class, DataSourceFineConfig.class} )
// @TransactionConfiguration( defaultRollback=false) // 已经无效了，但可以使用下面的俩来强行设置是否回滚测试
// @Transactional
// @Commit
public class SpringDataTest {
	// @Autowired
	// ApplicationContext applicationContext;

	// @Before
	// public void set(){
	//     applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DataSourceFineConfig.class);
	// }
	@Autowired
	private StudentRepository repo;

	@Test
	public void createTable(){
	}

	@Test
	public void findByName(){
		List<Student> list = repo.findByName("萝莉");

		System.out.println(list);
	}

	@Test
	public void saveStu(){
		Student student = new Student();
		student.setName("萝莉");
		student.setAge(13);
		student = repo.save(student);
		System.out.println(student);
	}

	//就是说save既可以insert，也可以update
	@Test
	public void updateStu(){
		Student student = new Student();
		student.setId(3);
		student.setName("萝莉");
		student.setAge(15);
		repo.save(student);
	}
}
