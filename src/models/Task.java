package models;

import java.util.Date;

public class Task {
    
    private int id;
    private String taskDescription;
    private String taskStatus;
    private Date createAt;
    private Date updatedAt;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public String getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


}
