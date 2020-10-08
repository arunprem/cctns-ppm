package io.keralapolice.ppmtool.repository;


import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {


    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
}
