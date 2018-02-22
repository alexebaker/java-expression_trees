package Parser.Operators;

import Tokenizer.Token;

public class Operator {
    private String op;

    public Operator() {
        this("");
    }

    public Operator(Token token) {
        this(token.getValue());
    }

    public Operator(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return op;
    }
}
