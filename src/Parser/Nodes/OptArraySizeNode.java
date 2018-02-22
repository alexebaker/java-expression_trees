package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class OptArraySizeNode extends ParseNode {
    private ExprNode exprNode;

    public OptArraySizeNode() {
        this.exprNode = null;
    }

    public void setExprNode(ExprNode exprNode) {
        this.exprNode = exprNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (exprNode != null) {
            str.append(exprNode.toString());
        }
        return str.toString();
    }

    public static OptArraySizeNode parse(TokenReader tr) {
        Token token = tr.peek();
        OptArraySizeNode optArraySizeNode = new OptArraySizeNode();
        if (token.getValue().equals("]")) {
            return optArraySizeNode;
        }

        ExprNode exprNode = ExprNode.parse(tr);
        if (exprNode != null) {
            optArraySizeNode.setExprNode(exprNode);
            return optArraySizeNode;
        }
        return null;
    }
}
