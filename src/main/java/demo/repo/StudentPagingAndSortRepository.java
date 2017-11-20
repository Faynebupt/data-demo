package demo.repo;

import demo.entity.Student;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Created by 张少昆 on 2017/7/27.
 */
@Profile("page")
public interface StudentPagingAndSortRepository extends PagingAndSortingRepository<Student, Integer> {
}
