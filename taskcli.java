import java.util.ArrayList;
import java.util.List;

public class taskcli {

    static final String INPUT_ADD = "add";
    static final String INPUT_UPDATE = "update";
    static final String INPUT_DELETE = "delete";
    static final String INPUT_MARK_IN_PROGRESS = "mark-in-progress";
    static final String INPUT_MARK_DONE = "mark-done";
    static final String INPUT_LIST_TASKS = "list";
    static final TaskService ts = new TaskService();

    public static void main(String[] args) throws Exception {
        /*
         * CLI Commands
         * # Adding a new task
         * java taskcli add "Buy groceries"
         * # Output: Task added successfully (ID: 1)
         * 
         * # Updating and deleting tasks
         * java taskcli update 1 "Buy groceries and cook dinner"
         * java taskcli delete 1
         * 
         * # Marking a task as in progress or done
         * java taskcli mark-in-progress 1
         * java taskcli mark-done 1
         * 
         * # Listing all tasks
         * java taskcli list
         * 
         * # Listing tasks by status
         * java taskcli list done
         * java taskcli list todo
         * java taskcli list in-progress
         * 
         */

        // if (args.length == 0) {
        // System.out.println("Please check the command list from README.md");
        // return;
        // }

         String input = args[0];
        //String input = "list";

        switch (input) {
            case INPUT_ADD: {
                String output = ts.Add(args[1]);
                System.out.println(output);
                break;
            }
            case INPUT_LIST_TASKS: {
                List<Task> allTasks = new ArrayList<>();
                
                if(args.length == 2){
                    
                    if(args[1].equals(Status.DONE.toString())){
                        allTasks = ts.GetAllCompletedTasks();
                    }
                    else if(args[1].equals(Status.INPROGRESS.toString())){
                        allTasks = ts.GetAllTasksInProgress();
                    }
                    else if(args[1].equals(Status.TODO.toString())){
                        allTasks = ts.GetAllUnstartedTasks();
                    }
                    else{
                        System.out.println("Please check the README.md file for commands.");
                        return;
                    }
                }
                else{
                    allTasks = ts.GetAllTasks();
                }

                allTasks.forEach(item -> {
                    System.out.println(
                            "{ id: " + item.getId() +
                                    ", description: " + item.getTaskDescription() +
                                    ", status: " + item.getTaskStatus() +
                                    ", created at: " + item.getCreateAt() +
                                    ", updated at: " + item.getUpdatedAt() + " }");
                });

                break;

            }

            case INPUT_UPDATE: {
                 ts.Update(Integer.parseInt(args[1]), args[2]);
                //ts.Update(1, "Study for 4 hours");
                List<Task> allTasks = ts.GetAllTasks();
                allTasks.forEach(item -> {
                    System.out.println(
                            "{ id: " + item.getId() +
                                    ", description: " + item.getTaskDescription() +
                                    ", status: " + item.getTaskStatus() +
                                    ", created at: " + item.getCreateAt() +
                                    ", updated at: " + item.getUpdatedAt() + " }");
                });
                break;
            }

            case INPUT_DELETE: {
                ts.Delete(Integer.parseInt(args[1]));
               List<Task> allTasks = ts.GetAllTasks();
               allTasks.forEach(item -> {
                   System.out.println(
                           "{ id: " + item.getId() +
                                   ", description: " + item.getTaskDescription() +
                                   ", status: " + item.getTaskStatus() +
                                   ", created at: " + item.getCreateAt() +
                                   ", updated at: " + item.getUpdatedAt() + " }");
               });
               break;
           }
           
           case INPUT_MARK_IN_PROGRESS:{
                ts.MarkInProgress(Integer.parseInt(args[1]));
                List<Task> allTasks = ts.GetAllTasks();
               allTasks.forEach(item -> {
                   System.out.println(
                           "{ id: " + item.getId() +
                                   ", description: " + item.getTaskDescription() +
                                   ", status: " + item.getTaskStatus() +
                                   ", created at: " + item.getCreateAt() +
                                   ", updated at: " + item.getUpdatedAt() + " }");
               });
               break;
           }

           case INPUT_MARK_DONE:{
            ts.MarkDone(Integer.parseInt(args[1]));
            List<Task> allTasks = ts.GetAllTasks();
           allTasks.forEach(item -> {
               System.out.println(
                       "{ id: " + item.getId() +
                               ", description: " + item.getTaskDescription() +
                               ", status: " + item.getTaskStatus() +
                               ", created at: " + item.getCreateAt() +
                               ", updated at: " + item.getUpdatedAt() + " }");
           });
           break;
       }

            default:
                System.out.println("Please check the README.md file for commands.");
                break;
        }
    }

}
