package io.keralapolice.ppmtool.services;

import io.keralapolice.ppmtool.domain.Backlog;
import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.domain.ProjectTask;
import io.keralapolice.ppmtool.exceptions.ProjectNotFoundException;
import io.keralapolice.ppmtool.repository.BacklogRepository;
import io.keralapolice.ppmtool.repository.ProjectRepository;
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

    @Autowired
    private ProjectRepository projectRepository;


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

            if (projectTask.getPriority()==0||projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw  new ProjectNotFoundException("Project Not Found");
        }


    }

    public List<ProjectTask> findBacklogByid(String backlog_id) {
        Project project = projectRepository.findByProjectIdentifier(backlog_id);

        if(project==null){
            throw  new ProjectNotFoundException("Project with Id "+backlog_id+" does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }


    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id){

        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);

        if(backlog==null){
            throw new ProjectNotFoundException("Project with Id " +backlog_id+" does not exist");

        }

        ProjectTask projectTask = projectTaskRepository.findProjectTaskByProjectSequence(pt_id);

        if(projectTask==null){
            throw new ProjectNotFoundException(("Project Task "+pt_id+" not Found"));
        }

        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task "+pt_id+" does not exist in project "+backlog_id);
        }
        //
        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);

        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id,String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id,pt_id);
        projectTaskRepository.delete(projectTask);
    }
}
