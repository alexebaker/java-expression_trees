package Compiler;

import Parser.Nodes.ParseNode;
import Parser.Nodes.ProgramNode;
import Parser.TokenParser;
import Tokenizer.TokenReader;

public class LCC {
    CompilerState cs;

    public LCC(CompilerState cs) {
        this.cs = cs;
    }

    public void compile() {
        TokenParser tp = new TokenParser(new TokenReader(cs), cs);
        tp.parse();
    }
}
