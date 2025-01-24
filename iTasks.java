
import java.util.List;

public interface iTasks {

    String Add(String input);

    void Update(int id, String updatedValue);

    void Delete(int id);

    void MarkDone(int id);

    void MarkInProgress(int id);

    List<Task> GetAllTasks();

    List<Task> GetAllCompletedTasks();

    List<Task> GetAllTasksInProgress();

    List<Task> GetAllUnstartedTasks();


}
