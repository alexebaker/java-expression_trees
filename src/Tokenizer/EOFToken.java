package Tokenizer;

public class EOFToken extends Token {
    public EOFToken(Compiler.CompilerState cs) {
        super("$EOF", cs);
    }

    /**
     * Checks if ch is a valid EOF TokenReader.Token
     * @param ch character to check if it is an EOF token
     * @return true if ch is an EOF token, false otherwise
     */
    public static boolean isToken(int ch) {
        return ch == -1;
    }
}
