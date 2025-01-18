package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.Tasks;
import models.Task;

public class TaskService implements Tasks {

    static final Path JSON_PATH = Path
            .of("tasks.json");

    @Override
    public String Add(String input) {
        try {
            CreateFileIfNotExist();

            String data = Files.readString(JSON_PATH);
            System.out.println(data);

            CreateJSONFile(data, input);

            return "Successfully Added!";
        } catch (Exception ex) {
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
        if (!Files.exists(JSON_PATH)) {

        }
        return AllTasks;
    }

    @Override
    public List<Task> GetAllCompletedTasks() {
        List<Task> AllCompletedTasks = new ArrayList<Task>();

        if (!Files.exists(JSON_PATH)) {

        }
        return AllCompletedTasks;
    }

    @Override
    public List<Task> GetAllTasksInProgress() {
        List<Task> AllTasksInProgress = new ArrayList<Task>();

        if (!Files.exists(JSON_PATH)) {

        }
        return AllTasksInProgress;
    }

    @Override
    public List<Task> GetAllUnstartedTasks() {
        List<Task> AllUnstartedTasks = new ArrayList<Task>();

        if (!Files.exists(JSON_PATH)) {

        }
        return AllUnstartedTasks;
    }

    public static void CreateFileIfNotExist() throws IOException {

        boolean exists = Files.exists(JSON_PATH);
        if (!exists) {
            Files.createFile(JSON_PATH);
            String text = "{\"taskscount\":\"0\", \"tasks\":[]}";
            Files.writeString(JSON_PATH, text);
        }
    }

    public static void CreateJSONFile(String data, String input) throws IOException {
        int count = findId(data);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        Task t = new Task();
        t.setId(count + 1);
        t.setTaskDescription(input);
        t.setTaskStatus("todo");
        t.setCreateAt(dtf.format(now));
        t.setUpdatedAt(dtf.format(now));

        String id = "\"id\":" + t.getId();
        String description = "\"description\":\"" + t.getTaskDescription() + "\"";
        String status = "\"status\":\"" + t.getTaskStatus() + "\"";
        String createdAt = "\"createdAt\":\"" + t.getCreateAt() + "\"";
        String updatedAt = "\"updatedAt\":\"" + t.getUpdatedAt() + "\"";

        String currentTask = "{" + id + "," + description + "," + status + "," + createdAt + "," + updatedAt + "}]}";
        if (count != 0) {
            currentTask = "," + currentTask;
        }
        data = data.replaceFirst("\"\\d+\"", "\"" + (count + 1) + "\"");
        data = data.substring(0, data.length() - 2) + currentTask;

        System.out.println(data);
        Files.writeString(JSON_PATH, data);

    }

    public static int findId(String data) {
        int id = 0;
        Pattern p = Pattern.compile("^\\D+(\\d+).*");
        Matcher m = p.matcher(data);
        if (m.find()) {
            id = Integer.parseInt(m.group(1));
        }
        return id;
    }

}
