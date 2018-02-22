package Parser.Nodes;

import Parser.Operators.FactorOp;
import Parser.Operators.PreunOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class FactorNode extends ParseNode {
    private PostfixExprNode postfixExprNode;
    private PreunOp preunOp;
    private FactorNode factorNode;

    public FactorNode() {
        this.postfixExprNode = null;
        this.preunOp = null;
        this.factorNode = null;
    }

    public void setFactorNode(FactorNode factorNode) {
        this.factorNode = factorNode;
    }

    public void setPostfixExprNode(PostfixExprNode postfixExprNode) {
        this.postfixExprNode = postfixExprNode;
    }

    public void setPreunOp(PreunOp preunOp) {
        this.preunOp = preunOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (postfixExprNode != null) {
            str.append(postfixExprNode);
        }
        else if (preunOp != null && factorNode != null) {
            str.append("(");
            str.append(preunOp);
            str.append(factorNode);
            str.append(")");
        }
        return str.toString();
    }

    public static FactorNode parse(TokenReader tr) {
        Token token = tr.peek();
        FactorNode factorNode = new FactorNode();
        if (PreunOp.isOp(token)) {
            factorNode.setPreunOp(new PreunOp(tr.read()));
            FactorNode factorNode1 = FactorNode.parse(tr);
            if (factorNode1 != null) {
                factorNode.setFactorNode(factorNode1);
                return factorNode;
            }
            return null;
        }

        PostfixExprNode postfixExprNode = PostfixExprNode.parse(tr);
        if (postfixExprNode != null) {
            factorNode.setPostfixExprNode(postfixExprNode);
            return factorNode;
        }
        return null;
    }
}
