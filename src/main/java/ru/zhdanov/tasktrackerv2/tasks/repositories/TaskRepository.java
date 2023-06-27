package ru.zhdanov.tasktrackerv2.tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zhdanov.tasktrackerv2.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    Optional<Task> getTaskById(Integer id);

    List<Task> findAllByOwnerId(int id);

}
