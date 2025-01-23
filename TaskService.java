import java.io.IOException;
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
                break;
            }
        }

        try {
            Files.delete(Utility.JSON_PATH);
            Utility.CreateFileIfNotExist();
            
            int count = 0;
            for (Task t : AllTasks) {
                String data = Files.readString(Utility.JSON_PATH);
                Utility.writeToFile(data, count, t);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(int id) {
        List<Task> AllTasks = GetAllTasks();

        for (Task t : AllTasks) {
            if (t.getId() == id) {
                AllTasks.remove(t);
                break;
            }
        }

        try {
            Files.delete(Utility.JSON_PATH);
            Utility.CreateFileIfNotExist();
            
            int count = 0;
            for (Task t : AllTasks) {
                String data = Files.readString(Utility.JSON_PATH);
                Utility.writeToFile(data, count, t);
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
