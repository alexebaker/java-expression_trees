package Parser;

public class ParseTree {
    private ParseNode root;

    public ParseTree() {
        this.root = new ParseNode();
    }

    public ParseNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
