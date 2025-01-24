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

        Utility.reCreateJSON(AllTasks);
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

        Utility.reCreateJSON(AllTasks);
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

        if (Files.exists(Utility.JSON_PATH)) {
            AllCompletedTasks = GetAllTasks().stream().filter(task -> task.getTaskStatus().equals(Status.DONE.toString())).toList();
        }
        return AllCompletedTasks;
    }

    @Override
    public List<Task> GetAllTasksInProgress() {
        List<Task> AllTasksInProgress = new ArrayList<Task>();

        if (Files.exists(Utility.JSON_PATH)) {
            AllTasksInProgress = GetAllTasks().stream().filter(task -> task.getTaskStatus().equals(Status.INPROGRESS.toString())).toList();
        }
        return AllTasksInProgress;
    }

    @Override
    public List<Task> GetAllUnstartedTasks() {
        List<Task> AllUnstartedTasks = new ArrayList<Task>();

        if (Files.exists(Utility.JSON_PATH)) {
            AllUnstartedTasks = GetAllTasks().stream().filter(task -> task.getTaskStatus().equals(Status.TODO.toString())).toList();
        }
        return AllUnstartedTasks;
    }

    @Override
    public void MarkDone(int id) {
        List<Task> AllTasks = GetAllTasks();

        for (Task t : AllTasks) {
            if (t.getId() == id) {
                t.setTaskStatus(Status.DONE.toString());
                break;
            }
        }

        Utility.reCreateJSON(AllTasks);
    }

    @Override
    public void MarkInProgress(int id) {
        List<Task> AllTasks = GetAllTasks();

        for (Task t : AllTasks) {
            if (t.getId() == id) {
                t.setTaskStatus(Status.INPROGRESS.toString());
                break;
            }
        }

        Utility.reCreateJSON(AllTasks);
    }



}
