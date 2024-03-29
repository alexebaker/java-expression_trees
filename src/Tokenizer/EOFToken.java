package Tokenizer;

import Compiler.CompilerState;

public class EOFToken extends Token {
    public EOFToken(CompilerState cs) {
        super("$EOF", cs);
    }

    @Override
    public String toString() {
        return "";
    }

    /**
     * Checks if ch is a valid EOF Token
     *
     * @param ch character to check if it is an EOF Token
     * @return true if ch is an EOF Token, false otherwise
     */
    public static boolean isToken(int ch) {
        return ch == -1;
    }

    /**
     * Checks if token is a valid EOF Token
     *
     * @param token token to check if it is an EOF Token
     * @return true if token is an EOF Token, false otherwise
     */
    public static boolean isToken(Token token) {
        return token instanceof EOFToken;
    }
}
