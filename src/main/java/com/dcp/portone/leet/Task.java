package com.dcp.portone.leet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

enum Priority {HIGH, MEDOUM, LOW}
enum Status {IN_QUEUE, ASSIGNED, IN_PROGRESS}
public class Task implements Comparable<Task>{
    private String project;
    private String desc;
    private String assignee;
    private Priority priority;
    private Status status;

    public Task(String project, String desc, String assignee, Priority priority, Status status) {
        this.project = project;
        this.desc = desc;
        this.assignee = assignee;
        this.priority = priority;
        this.status = status;
    }

    public Task(String project, String desc, Priority priority) {
        this(project, desc, null, priority);
    }

    public Task(String project, String desc, String assignee, Priority priority) {
        this(project,desc,assignee,priority,assignee == null ? Status.IN_QUEUE : Status.ASSIGNED);

    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "%-20s %-20s %-10s %-10s %s".formatted(project, desc,priority, assignee, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!getProject().equals(task.getProject())) return false;
        return getDesc().equals(task.getDesc());
    }

    @Override
    public int hashCode() {
        int result = getProject().hashCode();
        result = 31 * result + getDesc().hashCode();
        return result;
    }

    @Override
    public int compareTo(Task o) {
        int result = this.project.compareTo(o.project);
        if (result == 0) {
            result = this.desc.compareTo(o.desc);
        }
        return result;
    }
}

class TaskData{
    public void main(){
        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
    }

    public void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }
    public void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
        String lineSeperator = "_".repeat(80);

        List<Task> list = new ArrayList<>(collection);
        list.sort(sorter);
        list.forEach(System.out::println);
    }
}