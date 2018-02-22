package Parser.Nodes;

import Parser.Operators.FactorOp;
import Tokenizer.Token;
import Tokenizer.TokenReader;

public class TermNode extends ParseNode {
    private TermNode termNode;
    private FactorOp factorOp;
    private FactorNode factorNode;

    public TermNode() {
        this.termNode = null;
        this.factorOp = null;
        this.factorNode = null;
    }

    public void setTermNode(TermNode termNode) {
        this.termNode = termNode;
    }

    public void setFactorNode(FactorNode factorNode) {
        this.factorNode = factorNode;
    }

    public void setFactorOp(FactorOp factorOp) {
        this.factorOp = factorOp;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (factorNode != null) {
            if (factorOp != null && termNode != null) {
                str.append("(");
                str.append(factorNode.toString());
                str.append(factorOp.toString());
                str.append(termNode.toString());
                str.append(")");
            }
            else {
                str.append(factorNode);
            }
        }
        return str.toString();
    }

    public static TermNode parse(TokenReader tr) {
        FactorNode factorNode = FactorNode.parse(tr);
        if (factorNode != null) {
            Token token;
            TermNode termNode = new TermNode();
            termNode.setFactorNode(factorNode);
            token = tr.peek();
            if (FactorOp.isOp(token)) {
                termNode.setFactorOp(new FactorOp(tr.read()));
                TermNode termNode1 = TermNode.parse(tr);
                if (termNode1 != null) {
                    termNode.setTermNode(termNode1);
                    return termNode;
                }
                return null;
            }
            return termNode;
        }
        return null;
    }
}
