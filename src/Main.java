import Compiler.LCC;
import Compiler.CompilerState;

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
        CompilerState cs = new CompilerState();

        if (argv.length == 1) {
            cs = new CompilerState(argv[0]);
        }

        LCC lcc = new LCC(cs);
        int errors = lcc.compile();

        cs.getIO().close();

        if (errors > 9) {
            System.exit(10);
        }
        System.exit(errors);
    }
}
