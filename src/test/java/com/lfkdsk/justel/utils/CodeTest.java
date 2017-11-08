package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import org.junit.Test;

import java.io.*;

public class CodeTest {
    @Test
    public void codeTests() throws IOException {
        File allTests = new File("/Users/liufengkai/Documents/AliCode/20171030_1446573_docker_upgrade_flow_task_1/lattice-client/src/test/java/com/alibaba/code");
        LineNumberReader reader = new LineNumberReader(new FileReader(allTests));

        com.lfkdsk.justel.lexer.Lexer lexer = new JustLexerImpl();
        JustParser parser = new JustParserImpl();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isEmpty()) {
                lexer.reset(line);
                lexer.hasMore();
                parser.parser(lexer);
            }
        }
    }
}
