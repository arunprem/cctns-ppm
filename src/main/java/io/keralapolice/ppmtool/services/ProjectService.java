package io.keralapolice.ppmtool.services;


import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.exceptions.ProjectIdException;
import io.keralapolice.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID'" + project.getProjectIdentifier().toUpperCase() + "'Already exists");
        }

    }


    public Project findProjectByIdentifier(String projectId) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID'" + projectId.toUpperCase() + "'Does Not Exist");
        }
        return project;

    }

    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    public void deleteByProjectIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID'" + projectId.toUpperCase() + "'Does not exist");
        }
        projectRepository.delete(project);
    }

}
