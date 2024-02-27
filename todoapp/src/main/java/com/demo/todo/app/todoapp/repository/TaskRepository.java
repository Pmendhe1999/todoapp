package com.demo.todo.app.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.todo.app.todoapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {

	
	@Query(value = "select * from to_do.task where user_id =:taskByUserId",nativeQuery = true)
	List<Task> getTaskByUserId(@Param("taskByUserId") Integer taskByUserId);

}
