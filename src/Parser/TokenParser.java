package Parser;

import Tokenizer.EOFToken;
import Tokenizer.Token;
import Tokenizer.TokenReader;

import java.io.IOException;
import java.util.Stack;

public class TokenParser {
    TokenReader tr;
    ParseTree parseTree;
    Grammar grammar;

    public TokenParser(TokenReader tr) {
        this.tr = tr;
        this.parseTree = new ParseTree();
        this.grammar = new Grammar();
    }

    public ParseTree getParseTree() {
        parseProgram();
        return parseTree;
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public boolean parseProgram() {
        Token token = tr.peek();

        if (EOFToken.isToken(token)) {
            // End of the input stream. If we got this far without an error, then the parse was good
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseExpression() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse an AsgnExpr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseAsgnExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a CondExpr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseCondExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a LogOr Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseLogOrExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a LogAnd Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseLogAndExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse an EQ Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseEqExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Rel Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseRelExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Simple Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseSimpleExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Term as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseTerm() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Factor as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseFactor() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Postfix Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parsePostfixExpr() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse an Array Spec as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseArraySpec() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse an Opt Array Size as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseOptArraySize() {
        Token token = tr.peek();
        return false;
    }

    /**
     * Function to be called to parse a Primary Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parsePrimaryExpr() {
        Token token = tr.peek();
        return false;
    }
}
