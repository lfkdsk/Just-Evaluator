package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.*;
import com.lfkdsk.justel.utils.NumberUtils;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by liufengkai on 2017/7/23.
 */
public class JustLexer implements Lexer {

    private ArrayList<Token> queue = new ArrayList<>();

    private boolean hasMore;

    private LineNumberReader reader;

    public JustLexer(Reader reader) {
        this.reader = new LineNumberReader(reader);
        this.hasMore = true;
    }

    @Override
    public Token peek(int index) {
        if (fillQueue(index)) {
            return queue.get(index);
        } else {
            return Token.EOF;
        }
    }

    @Override
    public Token read() {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    /**
     * 填充队列
     *
     * @param index 指定num
     * @return 返回状态
     * @throws ParseException
     */
    private boolean fillQueue(int index) throws ParseException {
        while (index >= queue.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    private void addToken(Token token) {
        queue.add(token);
    }

    /**
     * 成行读取
     *
     * @throws ParseException
     */
    private void readLine() throws ParseException {
        String line;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }

        if (line == null) {
            hasMore = false;
            return;
        }

        int lineNum = reader.getLineNumber();

        // init base message
        start = 0;
        end = line.length();
        currentReadString = line;
        if (end == 0) return;

        while (start < end) {
            scanToken();
        }

        queue.add(new IDToken(lineNum, Token.EOL));
    }

    private char peekChar = ' ';
    private int start = 0, end = 0, lineNumber = 0;
    private String currentReadString = "";

    private char readChar() {
        peekChar = currentReadString.charAt(start);
        start++;
        return peekChar;
    }

    private boolean readChar(char ch) {
        return start + 1 <= end && currentReadString.charAt(start + 1) == ch;
    }

    private void scanToken() {
        // jump all blank chars
        jumpBlank();
        // resolve symbols & operators
        if (resolveSymbol()) return;

        if (resolveNumber()) return;

        if (resolveString()) return;

        resolveIDToken();
    }

    private void jumpBlank() {
        for (; ; readChar()) {
            if (peekChar == ' ' || peekChar == '\t') {
                continue;
            } else if (peekChar == '\n')
                lineNumber = lineNumber + 1;
            else break;
        }
    }

    private boolean resolveSymbol() {
        // current peek char
        switch (peekChar) {
            case '?': {
                addToken(SepToken.QUESTION_TOKEN);
                break;
            }

            case ':': {
                addToken(SepToken.COLON_TOKEN);
                break;
            }

            case '+': {
                addToken(SepToken.PLUS_TOKEN);
                break;
            }

            case '-': {
                addToken(SepToken.MINUS_TOKEN);
                break;
            }

            case '*': {
                addToken(SepToken.MULTIPLY_TOKEN);
                break;
            }

            case '/': {
                addToken(SepToken.DIVIDE_TOKEN);
                break;
            }

            case '%': {
                addToken(SepToken.MOD_TOKEN);
                break;
            }

            case '&': {
                if (readChar('&')) {
                    addToken(SepToken.AND_TOKEN);
                } else {
                    addToken(SepToken.AMPERSAND_TOKEN);
                }
                break;
            }

            case '|': {
                if (readChar('|')) {
                    addToken(SepToken.OR_TOKEN);
                } else {
                    addToken(SepToken.BAR_TOKEN);
                }
                break;
            }

            case '<': {
                if (readChar('=')) {
                    addToken(SepToken.LTE_TOKEN);
                } else {
                    addToken(SepToken.LESS_TOKEN);
                }
                break;
            }

            case '>': {
                if (readChar('=')) {
                    addToken(SepToken.GTE_TOKEN);
                } else {
                    addToken(SepToken.GREAT_TOKEN);
                }
                break;
            }

            case '!': {
                if (readChar('=')) {
                    addToken(SepToken.NOT_EQUAL_TOKEN);
                } else {
                    addToken(SepToken.EXCLAM_TOKEN);
                }
                break;
            }

            case '.': {
                addToken(SepToken.DOT_TOKEN);
                break;
            }

            case '[': {
                addToken(SepToken.COLLECT_GET_LEFT_TOKEN);
                break;
            }

            case ']': {
                addToken(SepToken.COLLECT_GET_RIGHT_TOKEN);
                break;
            }

            default:
                return false;
        }

        return true;
    }

    private boolean resolveNumber() {
        if (Character.isDigit(readChar())) {
            // int value parser

            // start index of digits
            int startIndex = start;

            long v = 0;

            do {
                v = 10 * v + Character.digit(peekChar, 10);
                readChar();
            } while (Character.isDigit(peekChar));

            // end index of digit
            int endIndex = start - 1;

            if (peekChar != '.') {
                // int value
                // check value type is long or integer.
                Number checkedNum = NumberUtils.parseNumber(v);
                int checkedType = Token.INTEGER;

                if (checkedNum instanceof Long) {
                    checkedType = Token.LONG;
                }

                addToken(new NumberToken(lineNumber, checkedType,
                        currentReadString.substring(startIndex, endIndex),
                        checkedNum));

                return true;

            } else {
                double x = v;
                double d = 10;

                for (; ; ) {

                    if (!Character.isDigit(readChar())) {
                        break;
                    }

                    x = x + Character.digit(peekChar, 10) / d;
                    d = d * 10;
                }

                endIndex = start - 1;

                // float or double value
                Number checkedNum = NumberUtils.parseNumber(x);
                int checkedType = Token.FLOAT;

                if (checkedNum instanceof Double) {
                    checkedType = Token.DOUBLE;
                }

                addToken(new NumberToken(lineNumber, checkedType,
                        currentReadString.substring(startIndex, endIndex), checkedNum));

                return true;
            }
        }

        return false;
    }

    private boolean resolveString() {
        if (readChar() == '\"') {
            boolean returnFlag = false;
            StringBuilder builder = new StringBuilder();
            char lastChar = ' ';
            for (; ; readChar()) {
                if (peekChar == '\'' && returnFlag
                        && lastChar != '\\') {
                    break;
                } else if (peekChar == '\n') {
                    lineNumber += 1;
                    continue;
                } else if (peekChar != '\'') {
                    builder.append(peekChar);
                }
                lastChar = peekChar;
                returnFlag = true;
            }

            addToken(new StringToken(lineNumber, builder.toString()));
            return true;
        }
        return false;
    }

    private boolean resolveIDToken() {
        if (Character.isLetter(readChar())) {
            StringBuilder buffer = new StringBuilder();

            do {
                buffer.append(peekChar);
                readChar();
            } while (Character.isLetterOrDigit(peekChar) || peekChar == '_');

            addToken(new IDToken(lineNumber, buffer.toString()));

            return true;
        }

        return false;
    }
}
