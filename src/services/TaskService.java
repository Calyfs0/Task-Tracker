package services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import interfaces.Tasks;
import models.Task;

public class TaskService implements Tasks {

    String JSON_PATH = "../db/tasks.json";

    @Override
    public String Add(Task t) {
        try{
            Path path = Path.of(JSON_PATH);

            boolean exists = Files.exists(path);
            if(!exists){
                Files.createFile(path);
            }
            
            

            return "Successfully Added!";
        }
        catch(Exception ex){
            System.out.println("Failed to add task. See below for detailed error.");
            return ex.toString();
        }
    }

    @Override
    public String Update(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Update'");
    }

    @Override
    public String Delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

    @Override
    public List<Task> GetAllTasks() {
        List<Task> AllTasks = new ArrayList<Task>();
        Path path = Path.of(JSON_PATH);
        if(!Files.exists(path)){
            return AllTasks;
        }

    }

    @Override
    public List<Task> GetAllCompletedTasks() {
        List<Task> AllCompletedTasks = new ArrayList<Task>();
        Path path = Path.of(JSON_PATH);
        if(!Files.exists(path)){
            return AllCompletedTasks;
        }
    }

    @Override
    public List<Task> GetAllTasksInProgress() {
        List<Task> AllTasksInProgress = new ArrayList<Task>();
        Path path = Path.of(JSON_PATH);
        if(!Files.exists(path)){
            return AllTasksInProgress;
        }
    }

    @Override
    public List<Task> GetAllUnstartedTasks() {
        List<Task> AllUnstartedTasks = new ArrayList<Task>();
        Path path = Path.of(JSON_PATH);
        if(!Files.exists(path)){
            return AllUnstartedTasks;
        }
    }
    
}
