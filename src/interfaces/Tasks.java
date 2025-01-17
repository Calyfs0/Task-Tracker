package interfaces;

import java.util.List;
import models.Task;

public interface Tasks {
    
    String Add(Task t);
    String Update(int id);
    String Delete(int id);
    List<Task> GetAllTasks();
    List<Task> GetAllCompletedTasks();
    List<Task> GetAllTasksInProgress();
    List<Task> GetAllUnstartedTasks();
}
