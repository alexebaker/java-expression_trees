package Parser.Nodes;

import Tokenizer.Token;
import Tokenizer.TokenReader;

public class LogOrExprNode extends ParseNode {
    private LogAndExprNode logAndExprNode;
    private LogOrExprNode logOrExprNode;

    public LogOrExprNode() {
        this.logAndExprNode = null;
        this.logOrExprNode = null;
    }

    public void setLogAndExprNode(LogAndExprNode logAndExprNode) {
        this.logAndExprNode = logAndExprNode;
    }

    public void setLogOrExprNode(LogOrExprNode logOrExprNode) {
        this.logOrExprNode = logOrExprNode;
    }

    public static LogOrExprNode parse(TokenReader tr) {
        LogAndExprNode logAndExprNode = LogAndExprNode.parse(tr);
        if (logAndExprNode != null) {
            Token token;
            LogOrExprNode logOrExprNode = new LogOrExprNode();
            logOrExprNode.setLogAndExprNode(logAndExprNode);
            token = tr.peek();
            if (token.getValue().equals("||")) {
                tr.read();
                LogOrExprNode logOrExprNode1 = LogOrExprNode.parse(tr);
                if (logOrExprNode1 != null) {
                    logOrExprNode.setLogOrExprNode(logOrExprNode1);
                    return logOrExprNode;
                }
                return null;
            }
            return logOrExprNode;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        if (logAndExprNode != null) {
            str.append(logAndExprNode.toString());
            if (logOrExprNode != null) {
                str.append("||");
                str.append(logOrExprNode.toString());
            }
        }
        return str.toString();
    }
}
