package Tokenizer;

/**
 * Base Token class for the compiler
 */
public class Token {
    private String token;
    private String value;
    private String location;
    private long lineCount;
    private long charCount;

    Token(String token, Compiler.CompilerState cs) {
        this(token, "", cs);
    }

    Token(String token, String value, Compiler.CompilerState cs) {
        this.token = token;
        this.value = value;
        this.location = cs.getInputPath();
        this.lineCount = cs.getIO().getLineCount();
        this.charCount = cs.getIO().getCharCount();

        // Kinda a hack, but used to get the correct char position for the different tokens.
        // Need to subtract off the length of the token, since it is printed after the entire
        // token is read, so char position will be at the end of the token and not the beginning.
        if (value.length() > 0) {
            this.charCount = this.charCount - value.length() + 1;
        }
        else if (!token.equals("$EOF")) {
            this.charCount = this.charCount - token.length() + 1;
        }
    }

    @Override
    public String toString() {
        String str = location + ":" + lineCount + ":" + charCount + ":" + token;
        if (this.value.length() > 0) {
            str += ":" + this.value;
        }
        return str;
    }
}
