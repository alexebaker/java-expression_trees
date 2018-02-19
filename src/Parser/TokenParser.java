package Parser;

import Tokenizer.*;

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
        parseProgram(parseTree.getRoot());
        return parseTree;
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public boolean parseProgram(ParseNode node) {
        Token token = tr.peek();

        if (EOFToken.isToken(token)) {
            // End of the input stream. If we got this far without an error, then the parse was good
            return true;
        }
        else if (parseExpression(node)) {
            token = tr.peek();
            if (token.getValue().equals(";")) {
                tr.read();
                return parseProgram(node);
            }
        }
        return false;
    }

    /**
     * Function to be called to parse an Expression as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseExpression(ParseNode node) {
        Token token;
        if (parseAsgnExpr(node)) {
            token = tr.peek();
            if (token.getValue().equals(",")) {
                tr.read();
                return parseExpression(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse an AsgnExpr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseAsgnExpr(ParseNode node) {
        Token token = tr.peek();
        if (grammar.isID(token) || grammar.isNum(token) || token.getValue().equals("(")) {
            if (parsePostfixExpr(node)) {
                token = tr.peek();
                if (token.getValue().equals("=")) {
                    tr.read();
                    return parseAsgnExpr(node);
                }
            }
            return false;
        }
        return parseCondExpr(node);
    }

    /**
     * Function to be called to parse a CondExpr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseCondExpr(ParseNode node) {
        Token token;
        if (parseLogOrExpr(node)) {
            token = tr.peek();
            if (token.getValue().equals("?")) {
                tr.read();
                if (parseExpression(node)) {
                    token = tr.peek();
                    if (token.getValue().equals(":")) {
                        tr.read();
                        return parseCondExpr(node);
                    }
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a LogOr Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseLogOrExpr(ParseNode node) {
        Token token;
        if (parseLogAndExpr(node)) {
            token = tr.peek();
            if (token.getValue().equals("||")) {
                tr.read();
                return parseLogOrExpr(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a LogAnd Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseLogAndExpr(ParseNode node) {
        Token token;
        if (parseEqExpr(node)) {
            token = tr.peek();
            if (token.getValue().equals("&&")) {
                tr.read();
                return parseLogAndExpr(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse an EQ Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseEqExpr(ParseNode node) {
        Token token;
        if (parseRelExpr(node)) {
            token = tr.peek();
            if (grammar.isEqOp(token)) {
                tr.read();
                return parseEqExpr(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a Rel Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseRelExpr(ParseNode node) {
        Token token;
        if (parseSimpleExpr(node)) {
            token = tr.peek();
            if (grammar.isRelOp(token)) {
                tr.read();
                return parseRelExpr(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a Simple Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseSimpleExpr(ParseNode node) {
        Token token;
        if (parseTerm(node)) {
            token = tr.peek();
            if (grammar.isTermOp(token)) {
                tr.read();
                return parseSimpleExpr(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a Term as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseTerm(ParseNode node) {
        Token token;
        if (parseFactor(node)) {
            token = tr.peek();
            if (grammar.isFactorOp(token)) {
                tr.read();
                return parseTerm(node);
            }
            return true;
        }
        return false;
    }

    /**
     * Function to be called to parse a Factor as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseFactor(ParseNode node) {
        Token token = tr.peek();
        if (grammar.isPreunOp(token)) {
            tr.read();
            return parseFactor(node);
        }
        return parsePostfixExpr(node);
    }

    /**
     * Function to be called to parse a Postfix Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parsePostfixExpr(ParseNode node) {
        Token token;
        if (parsePrimaryExpr(node)) {
            token = tr.peek();
            if (grammar.isPostunOp(token)) {
                tr.read();
                return parsePostfixExpr(node);
            }
            else if (token.getValue().equals("[")) {
                return parseArraySpec(node);
            }
            else {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to be called to parse an Array Spec as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseArraySpec(ParseNode node) {
        Token token = tr.read();
        if (token.getValue().equals("[")) {
            if (parseOptArraySize(node)) {
                token = tr.read();
                if (token.getValue().equals("]")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Function to be called to parse an Opt Array Size as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parseOptArraySize(ParseNode node) {
        Token token = tr.peek();
        if (token.getValue().equals("]")) {
            return true;
        }
        return parseExpression(node);
    }

    /**
     * Function to be called to parse a Primary Expr as defined in the grammar.
     *
     * @return true if the parse was successful, false otherwise
     */
    private boolean parsePrimaryExpr(ParseNode node) {
        Token token = tr.read();
        if (grammar.isID(token) || grammar.isNum(token) || token.getValue().equals("(")) {
            if (token.getValue().equals("(")) {
                return parseExpression(node);
            }
            return true;
        }
        return false;
    }
}
