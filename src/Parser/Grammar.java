package Parser;

import Tokenizer.IdentifierToken;
import Tokenizer.NumberToken;

public class Grammar {
    /**
     * Determines in the given char is a Term Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Term Op, false otherwise
     */
    public boolean isTermOp(int ch) {
        switch (Character.toString((char) ch)) {
            case "+":
            case "-":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given char is a Factor Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Factor Op, false otherwise
     */
    public boolean isFactorOp(int ch) {
        switch (Character.toString((char) ch)) {
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given char is an EQ Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Factor Op, false otherwise
     */
    public boolean isEqOp(int ch) {
        switch (Character.toString((char) ch)) {
            case "==":
            case "!=":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given char is a Rel Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Factor Op, false otherwise
     */
    public boolean isRelOp(int ch) {
        switch (Character.toString((char) ch)) {
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
     * Determines in the given char is a Preun Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Preun Op, false otherwise
     */
    public boolean isPreunOp(int ch) {
        switch (Character.toString((char) ch)) {
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
     * Determines in the given char is a Postun Op from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Postun Op, false otherwise
     */
    public boolean isPostunOp(int ch) {
        switch (Character.toString((char) ch)) {
            case "--":
            case "++":
                return true;
            default:
                return false;
        }
    }

    /**
     * Determines in the given char is an ID from the grammar
     *
     * @param ch character to test
     * @return true if ch is an ID, false otherwise
     */
    public boolean isID(int ch) {
        return IdentifierToken.isToken(Character.toString((char) ch));
    }

    /**
     * Determines in the given char is a Number from the grammar
     *
     * @param ch character to test
     * @return true if ch is a Number, false otherwise
     */
    public boolean isNum(int ch) {
        return NumberToken.isToken(Character.toString((char) ch));
    }
}
