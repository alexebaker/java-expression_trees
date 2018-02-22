package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class LogAndExprNode extends ParseNode {
    private LogAndExprNode logAndExprNode;
    private EqExprNode eqExprNode;

    public LogAndExprNode() {
        this.logAndExprNode = null;
        this.eqExprNode = null;
    }

    public void setLogAndExprNode(LogAndExprNode logAndExprNode) {
        this.logAndExprNode = logAndExprNode;
    }

    public void setEqExprNode(EqExprNode eqExprNode) {
        this.eqExprNode = eqExprNode;
    }

    public static LogAndExprNode parse(TokenReader tr) {
        EqExprNode eqExprNode = EqExprNode.parse(tr);
        if (eqExprNode != null) {
            Token token;
            LogAndExprNode logAndExprNode = new LogAndExprNode();
            logAndExprNode.setEqExprNode(eqExprNode);
            token = tr.peek();
            if (token.getValue().equals("&&")) {
                tr.read();
                LogAndExprNode logAndExprNode1 = LogAndExprNode.parse(tr);
                if (logAndExprNode1 != null) {
                    logAndExprNode.setLogAndExprNode(logAndExprNode1);
                    return logAndExprNode;
                }
                return null;
            }
            return logAndExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (eqExprNode != null) {
            str.append(eqExprNode.toString());
            if (logAndExprNode != null) {
                str.append("&&");
                str.append(logAndExprNode.toString());
            }
        }
        return str.toString();
    }
}
