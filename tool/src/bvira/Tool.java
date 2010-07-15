package bvira;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tool {
    private final PrintStream outputStream;
    private FileSystem fileSystem;
    private final Map<String, Command> commands;

    interface Command {

        void execute(FileSystem fs, List<String> args);
    }

    private Command createProjectCommand = new Command() {
        String[] folders = new String[]{
                "src",
                "test",
                "lib",
                "tools"
        };

        public void execute(FileSystem fs, List<String> args) {
            if (args.size() != 1) {
                throw new RuntimeException("Incorrect arguments for command");
            }

            fs.mkdir(args.get(0));
            for (String folder : folders) {
                fs.mkdir(args.get(0) + File.separatorChar + folder);
            }
        }
    };

    public static String usageInstructions = "bvira [command] parameters" +
            "\n\ncommands" +
            "\n   create <project>   Creates a new project with the given name in the current working directory";

    public Tool(PrintStream outputStream, FileSystem fileSystem) {
        this.outputStream = outputStream;
        this.fileSystem = fileSystem;
        commands = new HashMap<String, Command>();

        commands.put("create", createProjectCommand);
    }

    public static void main(String[] args) {
        Tool tool = new Tool(System.out, new DefaultFileSystem());

        tool.execute(new LinkedList<String>(Arrays.asList(args)));
    }

    public void execute(List<String> args) {

        if (args.size() > 0) {
            String commandName = args.get(0);
            args.remove(0);

            commands.get(commandName).execute(fileSystem, args);
        } else {
            outputStream.print("Bvira\n\n");
            outputStream.print(usageInstructions);
        }
    }
}
