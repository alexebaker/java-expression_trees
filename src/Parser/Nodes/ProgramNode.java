package Parser.Nodes;

import Tokenizer.EOFToken;
import Tokenizer.Token;
import Tokenizer.TokenReader;
import Compiler.CompilerIO;

import java.util.Vector;

public class ProgramNode extends ParseNode {
    private Vector<ParseNode> exprs;

    public ProgramNode() {
        this.exprs = new Vector<>();
    }

    public void addExpr(ParseNode expr) {
        exprs.add(expr);
    }

    public Vector<ParseNode> getExprs() {
        return exprs;
    }

    /**
     * Function to be called to parse a program. This will parse multiple statements.
     *
     * @return true if the parse was successful, false otherwise
     */
    public static ParseNode parse(TokenReader tr, CompilerIO io) {
        //System.out.println("Parsing Program...");
        ProgramNode pNode = new ProgramNode();

        while (true) {
            if (EOFToken.isToken(tr.peek())) {
                // End of the input stream. If we got this far without an error, then the parse was good
                break;
            }

            ParseNode exprNode = ExprNode.parse(tr);
            if (exprNode != null) {
                //System.out.println("Good Expr...");
                if (tr.peek().getValue().equals(";")) {
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
        for (ParseNode expr : getExprs()) {
            str.append(expr.toString());
            str.append("\n");
        }
        return str.toString();
    }
}
