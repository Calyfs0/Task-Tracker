import services.TaskService;

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
         * task-cli add "Buy groceries"
         * # Output: Task added successfully (ID: 1)
         * 
         * # Updating and deleting tasks
         * task-cli update 1 "Buy groceries and cook dinner"
         * task-cli delete 1
         * 
         * # Marking a task as in progress or done
         * task-cli mark-in-progress 1
         * task-cli mark-done 1
         * 
         * # Listing all tasks
         * task-cli list
         * 
         * # Listing tasks by status
         * task-cli list done
         * task-cli list todo
         * task-cli list in-progress
         * 
         */

        if (args.length == 0) {
            System.out.println("Please check the command list from README.md");
            return;
        }

        String input = args[0];
        System.out.println(input);
        System.out.println(args[1]);
        switch (input) {
            case INPUT_ADD: {
                String output = ts.Add(args[1].toString());
                System.out.println(output);
                break;
            }

            default:
                break;
        }
    }

}
