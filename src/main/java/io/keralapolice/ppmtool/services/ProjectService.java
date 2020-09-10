package io.keralapolice.ppmtool.services;


import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project){

        //Logic
        return projectRepository.save(project);
    }


}
