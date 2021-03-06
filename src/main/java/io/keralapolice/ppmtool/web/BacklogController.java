package io.keralapolice.ppmtool.web;

import io.keralapolice.ppmtool.domain.Project;
import io.keralapolice.ppmtool.domain.ProjectTask;
import io.keralapolice.ppmtool.services.MapValidationErrorService;
import io.keralapolice.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/backlog")
@CrossOrigin
public class BacklogController {
    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id){

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

    if(errorMap!=null){
        return  errorMap;
    }

    ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);

    return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);

    }


    @GetMapping("/{backlog_id}")

    public ResponseEntity<List<ProjectTask>>getProjectBacklog(@PathVariable String backlog_id){

        return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogByid(backlog_id),HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?>getProjectTask(@PathVariable String backlog_id,@PathVariable String pt_id){
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id,pt_id);
            return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
    }


    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?>updateProjectTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result,@PathVariable String backlog_id,@PathVariable String pt_id){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap!=null){
            return  errorMap;
        }


         ProjectTask projectTask1 = projectTaskService.updateByProjectSequence(projectTask,backlog_id,pt_id);

        return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?>deleteProjectTask(@PathVariable String backlog_id,@PathVariable String pt_id){
        projectTaskService.deletePTByProjectSequence(backlog_id,pt_id);
        return new ResponseEntity<String>("Project Task "+pt_id+" Deleted ",HttpStatus.OK);
    }



}
