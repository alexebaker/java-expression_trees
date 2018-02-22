package Parser.Nodes;

import Parser.Operators.TermOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class SimpleExprNode extends ParseNode {
    private TermNode termNode;
    private TermOp termOp;
    private SimpleExprNode simpleExprNode;

    public SimpleExprNode() {
        this.termNode = null;
        this.termOp = null;
        this.simpleExprNode = null;
    }

    public void setSimpleExprNode(SimpleExprNode simpleExprNode) {
        this.simpleExprNode = simpleExprNode;
    }

    public void setTermNode(TermNode termNode) {
        this.termNode = termNode;
    }

    public void setTermOp(TermOp termOp) {
        this.termOp = termOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (termNode != null) {
            if (termOp != null && simpleExprNode != null) {
                str.append("(");
                str.append(termNode);
                str.append(termOp);
                str.append(simpleExprNode);
                str.append(")");
            }
            else {
                str.append(termNode);
            }
        }
        return str.toString();
    }

    public static SimpleExprNode parse(TokenReader tr) {
        TermNode termNode = TermNode.parse(tr);
        if (termNode != null) {
            Token token;
            SimpleExprNode simpleExprNode = new SimpleExprNode();
            simpleExprNode.setTermNode(termNode);
            token = tr.peek();
            if (TermOp.isOp(token)) {
                simpleExprNode.setTermOp(new TermOp(tr.read()));
                SimpleExprNode simpleExprNode1 = SimpleExprNode.parse(tr);
                if (simpleExprNode1 != null) {
                    simpleExprNode.setSimpleExprNode(simpleExprNode1);
                    return simpleExprNode;
                }
                return null;
            }
            return simpleExprNode;
        }
        return null;
    }
}
