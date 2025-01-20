
import java.util.List;

public interface iTasks {

    String Add(String input);

    void Update(int id, String updatedValue);

    String Delete(int id);

    List<Task> GetAllTasks();

    List<Task> GetAllCompletedTasks();

    List<Task> GetAllTasksInProgress();

    List<Task> GetAllUnstartedTasks();

    Task GetTaskById(int id);

    List<Task> GetTaskByName(String taskName);
}
