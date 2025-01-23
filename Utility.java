import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    private Utility() {
    }

    public static final Path JSON_PATH = Path
            .of("tasks.json");

    public static void CreateFileIfNotExist() {
        try {
            boolean exists = Files.exists(JSON_PATH);
            System.out.println("File exists? " + exists);
            if (!exists) {
                System.out.println("Creating file...");
                Files.createFile(JSON_PATH);
                System.out.println("File created.");
                String text = "{\"taskscount\":\"0\", \"tasks\":[]}";
                Files.writeString(JSON_PATH, text);
                System.out.println("File successfully created.");
            }
        } catch (IOException ex) {
            System.out.println("Unable to create file when it didn't existed.");
            System.out.println(ex.toString());
        }

    }

    public static void CreateJSONFile(String data, String input) {
        try {
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

            writeToFile(data, count, t);
        } catch (Exception ex) {
            System.out.println("Unable to create JSON file in CreateJSONFile method.");
            System.out.println(ex.toString());
        }

    }

    public static void writeToFile(String data, int count, Task t) {
        try {
            String id = "\"id\":" + t.getId();
            String description = "\"description\":\"" + t.getTaskDescription() + "\"";
            String status = "\"status\":\"" + t.getTaskStatus() + "\"";
            String createdAt = "\"createdAt\":\"" + t.getCreateAt() + "\"";
            String updatedAt = "\"updatedAt\":\"" + t.getUpdatedAt() + "\"";

            String currentTask = "{" + id + "," + description + "," + status + "," + createdAt + "," + updatedAt
                    + "}]}";
            if (t.getId() != 1) {
                currentTask = "," + currentTask;
            }
            data = data.replaceFirst("\"\\d+\"", "\"" + (t.getId()) + "\"");
            data = data.substring(0, data.length() - 2) + currentTask;

            System.out.println(data);
            Files.writeString(JSON_PATH, data);
        } catch (IOException ex) {
            System.out.println("Unable to write to file in writeToFile method.");
            System.out.println(ex.toString());
        }

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

    public static List<Task> GetDataFromJSON() {
        try {
            List<Task> finalList = new ArrayList<>();
            Task t = null;
            String data = Files.readString(JSON_PATH);
            data = data.substring(data.indexOf("["), data.indexOf("]") + 1);
            data = data.replace("[", "").replace("]", "").replace("{", "").replace("},", "#").replace("}", "");
            String[] dataList = data.split("#");
            for (String s : dataList) {
                s = s.replace("\"", "");
                String[] d = s.split(",");
                t = new Task();
                for (String dd : d) {
                    String[] taskData = dd.split(":");

                    switch (taskData[0]) {
                        case "id":
                            t.setId(Integer.parseInt(taskData[1]));
                            break;
                        case "description":
                            t.setTaskDescription(taskData[1]);
                            break;
                        case "status":
                            t.setTaskStatus(taskData[1]);
                            break;
                        case "createdAt":
                            t.setCreateAt(taskData[1]);
                            break;
                        case "updatedAt":
                            t.setUpdatedAt(taskData[1]);
                            finalList.add(t);
                            break;
                        default:
                            break;

                    }

                }

            }

            return finalList;

        } catch (Exception ex) {
            System.out.println("Failed to get data" + ex.toString());
            return null;
        }

    }
}
