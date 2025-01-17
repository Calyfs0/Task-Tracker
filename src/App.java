import services.TaskService;

public class App {
    public static void main(String[] args) throws Exception {
        TaskService ts = new TaskService();

        ts.Add();
    }
}
