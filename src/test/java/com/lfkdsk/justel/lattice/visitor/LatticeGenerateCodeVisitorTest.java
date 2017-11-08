package com.lfkdsk.justel.lattice.visitor;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.lattice.LatticeParserImpl;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class LatticeGenerateCodeVisitorTest {
    LatticeParserImpl latticeParser;

    Lexer lexer;

    @BeforeEach
    void setUp() {
        latticeParser = new LatticeParserImpl();
        lexer = new JustLexerImpl();
        Logger.init();
    }

    @Test
    void testGenerateCode() {
//        "!\"seller\" isBSeller() and (\"category\" == \"50026316\" or \"category.rootCategoryId\" == \"50008141\" or \"category.rootCategoryId\" == \"50016422\" or \"category.rootCategoryId\" == \"50002766\" or \"category.rootCategoryId\" == \"50050359\" or \"category.rootCategoryId\" == \"50020275\" or \"category.rootCategoryId\" == \"124458005\")"
        lexer.reset("!\"seller\" isBSeller() and (\"category\" == \"50026316\" or \"category.rootCategoryId\" == \"50008141\" or \"category.rootCategoryId\" == \"50016422\" or \"category.rootCategoryId\" == \"50002766\" or \"category.rootCategoryId\" == \"50050359\" or \"category.rootCategoryId\" == \"50020275\" or \"category.rootCategoryId\" == \"124458005\")");
        lexer.hasMore();
        LatticeGenerateCodeVisitor visitor = new LatticeGenerateCodeVisitor();
        Logger.v(visitor.visitAstProgram((AstProgram) latticeParser.parser(lexer)));
    }

    @Test
    void testGenerateCode2() {
        lexer.reset("\"item\" hasTag() \"108098\"");
        lexer.hasMore();
        LatticeGenerateCodeVisitor visitor = new LatticeGenerateCodeVisitor();
        Logger.v(visitor.visitAstProgram((AstProgram) latticeParser.parser(lexer)));
    }

    @Test
    void generateCodeFile() throws IOException {
        File file = new File("./src/test/java/com/lfkdsk/justel/lattice/visitor/code");
        File writeFile = new File("./src/test/java/com/lfkdsk/justel/lattice/visitor/write_code");

        if (!writeFile.exists())
            writeFile.createNewFile();

        FileWriter writer = new FileWriter(writeFile);
        LineNumberReader reader = new LineNumberReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                lexer.reset(line);
                lexer.hasMore();
                LatticeGenerateCodeVisitor visitor = new LatticeGenerateCodeVisitor();
                writer.append(visitor.visitAstProgram((AstProgram) latticeParser.parser(lexer))).append('\n');
                writer.flush();
            }
        }

        writer.flush();
        writer.close();
        reader.close();
    }
}