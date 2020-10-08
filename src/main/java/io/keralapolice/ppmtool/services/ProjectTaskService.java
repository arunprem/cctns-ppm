package io.keralapolice.ppmtool.services;

import io.keralapolice.ppmtool.domain.Backlog;
import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.domain.ProjectTask;
import io.keralapolice.ppmtool.exceptions.ProjectNotFoundException;
import io.keralapolice.ppmtool.repository.BacklogRepository;
import io.keralapolice.ppmtool.repository.ProjectTaskRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;


    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        //PTS to be added to a specific project

        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);


            projectTask.setBacklog(backlog);

            Integer BacklogSequence = backlog.getPTSequence();

            BacklogSequence++;

            backlog.setPTSequence(BacklogSequence);

            projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);


            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw  new ProjectNotFoundException("Project Not Found");
        }


    }

    public List<ProjectTask> findBacklogByid(String backlog_id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }
}