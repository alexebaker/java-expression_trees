package Parser;

import Tokenizer.*;

public class Grammar {
    /**
     * Determines in the given token is a Term Op from the grammar
     *
     * @param token token to test
     * @return true if token is a Term Op, false otherwise
     */
    public boolean isTermOp(Token token) {
        switch (token.getValue()) {
            case "+":
            case "-":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is a Factor Op from the grammar
     *
     * @param token toekn to test
     * @return true if token is a Factor Op, false otherwise
     */
    public boolean isFactorOp(Token token) {
        switch (token.getValue()) {
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is an EQ Op from the grammar
     *
     * @param token token to test
     * @return true if token is a Factor Op, false otherwise
     */
    public boolean isEqOp(Token token) {
        switch (token.getValue()) {
            case "==":
            case "!=":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is a Rel Op from the grammar
     *
     * @param token token to test
     * @return true if token is a Factor Op, false otherwise
     */
    public boolean isRelOp(Token token) {
        switch (token.getValue()) {
            case "<":
            case "<=":
            case ">":
            case ">=":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is a Preun Op from the grammar
     *
     * @param token token to test
     * @return true if token is a Preun Op, false otherwise
     */
    public boolean isPreunOp(Token token) {
        switch (token.getValue()) {
            case "-":
            case "--":
            case "++":
            case "&":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is a Postun Op from the grammar
     *
     * @param token token to test
     * @return true if token is a Postun Op, false otherwise
     */
    public boolean isPostunOp(Token token) {
        switch (token.getValue()) {
            case "--":
            case "++":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given token is an ID from the grammar
     *
     * @param token token to test
     * @return true if token is an ID, false otherwise
     */
    public boolean isID(Token token) {
        return IdentifierToken.isToken(token);
    }

    /**
     * Determines in the given token is a Number from the grammar
     *
     * @param token token to test
     * @return true if token is a Number, false otherwise
     */
    public boolean isNum(Token token) {
        return NumberToken.isToken(token);
    }
}
