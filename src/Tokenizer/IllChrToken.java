package Tokenizer;

import Compiler.CompilerState;

public class IllChrToken extends Token {
    IllChrToken(String value, CompilerState cs) {
        super("$illchr", value, cs);
    }

    /**
     * Checks if ch is a valid Illegal Character Token
     *
     * @param str string to check if it is an Illegal Character Token
     * @return true if str is an Illegal Character Token, false otherwise
     */
    public static boolean isToken(String str) {
        return !LiteralToken.isToken(str) && !KeywordToken.isToken(str) && !IdentifierToken.isToken(str) && !NumberToken.isToken(str);
    }

    /**
     * Checks if token is a valid Illegal Character Token
     *
     * @param token token to check if it is a Illegal Character Token
     * @return true if token is a Illegal Character Token, false otherwise
     */
    public static boolean isToken(Token token) {
        return token instanceof IllChrToken && IllChrToken.isToken(token.getValue());
    }
}
