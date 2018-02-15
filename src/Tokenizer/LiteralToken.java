package Tokenizer;

public class LiteralToken extends Token {
    LiteralToken(String token, Compiler.CompilerState cs) {
        super(token, cs);
    }

    /**
     * Checks if ch is a valid Literal TokenReader.Token
     * @param str string to check if it is an Literal token
     * @return true if ch is an Literal TokenReader.Token, false otherwise
     */
    public static boolean isToken(String str) {
        switch (str) {
            case "(":
            case ")":
            case "[":
            case "]":
            case "{":
            case "}":
            case ".":
            case ",":
            case ";":
            case ":":
            case "::":
            case "!":
            case "?":
            case "=":
            case "==":
            case "!=":
            case "<<":
            case ">>":
            case "<":
            case ">":
            case "<=":
            case ">=":
            case "&":
            case "&&":
            case "|":
            case "||":
            case "^":
            case "*":
            case "%":
            case "/":
            case "+":
            case "-":
            case "++":
            case "--":
                return true;
            default:
                return false;
        }
    }
}
