package io.keralapolice.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private  Long id;
    @NotBlank(message = "Project Name is required")
    private String projectName;
    @NotBlank(message = "Project identifier is required")
    @Size(min = 4,max = 10,message = "please use 4 to 10 characters")
    @Column(updatable = false,unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Project Description is required")
    private String description;
    @JsonFormat(pattern = "dd/M/yyyy")
    private Date start_date;
    @JsonFormat(pattern = "dd/M/yyyy")
    private Date end_date;

    @JsonFormat(pattern = "dd/M/yyyy")
    private Date created_at;
    @JsonFormat(pattern = "dd/M/yyyy")
    private Date updated_at;

    public Project() {
    }


    public Long getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @PrePersist
    protected  void onCreate(){
        this.created_at = new Date();

    }

    @PreUpdate
protected  void onUpdate(){
        this.updated_at=new Date();
    }




}
