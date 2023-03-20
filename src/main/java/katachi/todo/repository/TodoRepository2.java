package katachi.todo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.CrudRepository;

import katachi.todo.domain.model.Todo;

@Mapper
public interface TodoRepository2  extends CrudRepository<Todo, Long> {
    /** 全件取得 */
    List<Todo> findAll();
}