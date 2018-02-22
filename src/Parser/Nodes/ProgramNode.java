package Parser.Nodes;

import Tokenizer.EOFToken;
import Tokenizer.Token;
import Tokenizer.TokenReader;
import Compiler.CompilerIO;

import java.util.Vector;

public class ProgramNode extends ParseNode {
    private Vector<ExprNode> exprs;

    public ProgramNode() {
        this.exprs = new Vector<>();
    }

    public void addExpr(ExprNode expr) {
        exprs.add(expr);
    }

    public Vector<ExprNode> getExprs() {
        return exprs;
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ProgramNode parse(TokenReader tr, CompilerIO io) {
        //System.out.println("Parsing Program...");
        Token token = tr.peek();
        ProgramNode pNode = new ProgramNode();

        while (true) {
            if (EOFToken.isToken(token)) {
                // End of the input stream. If we got this far without an error, then the parse was good
                break;
            }

            ExprNode exprNode = ExprNode.parse(tr);
            if (exprNode != null) {
                //System.out.println("Good Expr...");
                token = tr.peek();
                //System.out.println(token);
                if (token.getValue().equals(";")) {
                    tr.read();
                    pNode.addExpr(exprNode);
                    io.write(exprNode.toString());
                    continue;
                }
                io.write(exprNode.toString());
            }
            tr.handleError();
        }
        return pNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (ExprNode expr : getExprs()) {
            str.append(expr.toString());
            str.append("\n");
        }
        return str.toString();
    }
}
