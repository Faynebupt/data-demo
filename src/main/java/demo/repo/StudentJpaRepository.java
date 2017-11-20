package demo.repo;

import demo.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


/**
 * Created by 张少昆 on 2017/7/27.
 */
@Profile( "jpa" )
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

	//开启SQL支持
	@Query( nativeQuery = true, value = "select * from student" )
	List<Student> allSql();

	@Query( "select o from Student o" )
		//TODO 必须取一个别名！！！
	List<Student> allHql();

	@Query( "select o from Student o where o.name= :name" )
	Student byName(@Param( "name" ) String name);

	@Query( "select o from Student o where o.name= ?1" )
	Student byIndex(String name);

	@Transactional  //both is ok
	@Modifying
	@Query( "update Student o set o.name=:name,o.age=:age where id=:id" )
	Integer modify(@Param( "name" ) String name, @Param( "age" ) Integer age, @Param( "id" ) Integer id);
}
