package Parser;

import Tokenizer.Token;

import java.util.Vector;

public class ParseNode {
    private Token token;
    private Vector<ParseNode> children;

    public ParseNode() {
        this(null);
    }

    public ParseNode(Token token) {
        this.token = token;
        this.children = new Vector<>();
    }

    public void addChild(ParseNode child) {
        this.children.add(child);
    }

    public Vector<ParseNode> getChildren() {
        return this.children;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(");
        for(ParseNode child : getChildren()) {
            str.append(child.toString());
        }

        if (token != null) {
            str.append(token.toString());
        }

        str.append(")");
        return str.toString();
    }
}
