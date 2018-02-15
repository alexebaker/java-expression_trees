/**
 * Main class called to compile the input
 */
public class Main {
    public static void main(String[] argv) {
        if (argv.length > 1) {
            System.err.println("Too many arguments, can only give 0 or 1 argument.");
            System.exit(1);
        }

        System.setProperty("line.separator", "\n");
        Compiler.CompilerState cs = new Compiler.CompilerState();

        if (argv.length == 1) {
            cs = new Compiler.CompilerState(argv[0]);
        }

        cs.getIO().close();
        System.exit(0);
    }
}
