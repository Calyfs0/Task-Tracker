import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements iTasks {

    @Override
    public String Add(String input) {
        try {
            Utility.CreateFileIfNotExist();

            String data = Files.readString(Utility.JSON_PATH);
            System.out.println(data);

            Utility.CreateJSONFile(data, input);

            return "Successfully Added!";
        } catch (Exception ex) {
            System.out.println("Failed to add task. See below for detailed error.");
            return ex.toString();
        }
    }

    @Override
    public void Update(int id, String updatedValue) {
        List<Task> AllTasks = GetAllTasks();

        for (Task t : AllTasks) {
            if (t.getId() == id) {
                t.setTaskDescription(updatedValue);
            }
        }
    }

    @Override
    public String Delete(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'Delete'");
    }

    @Override
    public List<Task> GetAllTasks() {
        List<Task> AllTasks = new ArrayList<Task>();
        if (Files.exists(Utility.JSON_PATH)) {
            AllTasks = Utility.GetDataFromJSON();
        }
        return AllTasks;

    }

    @Override
    public List<Task> GetAllCompletedTasks() {
        List<Task> AllCompletedTasks = new ArrayList<Task>();

        if (!Files.exists(Utility.JSON_PATH)) {

        }
        return AllCompletedTasks;
    }

    @Override
    public List<Task> GetAllTasksInProgress() {
        List<Task> AllTasksInProgress = new ArrayList<Task>();

        if (!Files.exists(Utility.JSON_PATH)) {

        }
        return AllTasksInProgress;
    }

    @Override
    public List<Task> GetAllUnstartedTasks() {
        List<Task> AllUnstartedTasks = new ArrayList<Task>();

        if (!Files.exists(Utility.JSON_PATH)) {

        }
        return AllUnstartedTasks;
    }

    @Override
    public Task GetTaskById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'GetTaskById'");
    }

    @Override
    public List<Task> GetTaskByName(String taskName) {
        throw new UnsupportedOperationException("Unimplemented method 'GetTaskByName'");
    }

}
