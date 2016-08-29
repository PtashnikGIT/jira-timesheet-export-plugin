package com.bftcom.timesheet.export;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;

import java.util.*;

/**
 * Параметры, по которым выбираются таймшиты из джиры
 */
public class WorklogExportParams {

    private Collection<Project> projects;
    private Date startDate;
    private Date endDate;
    //todo
    //бюджеты
    //пользователи
    //задачи
    //ид-шники worklog

    public WorklogExportParams(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public WorklogExportParams projects(Collection<String> projectNames) {
        if (projectNames == null) {
            this.projects = Collections.emptyList();
        } else {
            this.projects = transformProjects((String[]) projectNames.toArray());
        }
        return this;
    }

    public WorklogExportParams projects(String... projectNames) {
        this.projects = transformProjects(projectNames);
        return this;
    }

    protected Collection<Project> transformProjects(String... projectNames) {
        ProjectManager projectManager = ComponentAccessor.getProjectManager();
        List<Project> projectList = new ArrayList<>();
        for (String name : projectNames) {
            Project project = projectManager.getProjectObjByName(name);
            if (project != null) {
                projectList.add(project);
            }
        }
        return projectList;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
