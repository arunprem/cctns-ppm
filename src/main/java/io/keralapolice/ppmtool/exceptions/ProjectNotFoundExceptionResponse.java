package io.keralapolice.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {

    private String projectNoteFound;

    public ProjectNotFoundExceptionResponse(String projectNoteFound) {
        this.projectNoteFound = projectNoteFound;
    }

    public String getProjectNoteFound() {
        return projectNoteFound;
    }

    public void setProjectNoteFound(String projectNoteFound) {
        this.projectNoteFound = projectNoteFound;
    }
}
