package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.*;
import com.lfkdsk.justel.utils.NumberUtils;
import com.lfkdsk.justel.utils.collection.ArrayQueue;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

/**
 * Lexer - Language Token Lexer
 * Just catch token this Engine supported.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/23.
 */
public class JustLexerImpl implements Lexer {

    /**
     * Token Queue
     */
    private ArrayQueue<Token> queue = new ArrayQueue<>();

    /**
     * has more tokens in Lexer
     */
    private boolean hasMore;

    private LineNumberReader reader;

    public JustLexerImpl(Reader reader) {
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
            return queue.poll();
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

        queue.add(new SepToken(lineNum, Token.EOL));
    }

    private char peekChar = ' ';
    private int start = 0, end = 0, lineNumber = 0;
    private String currentReadString = "";

    private char readChar() {
        if (start >= end) {
            peekChar = ' ';
            return peekChar;
        }

        peekChar = currentReadString.charAt(start);
        start++;
        return peekChar;
    }

    private boolean readChar(char ch) {
        return start <= end && currentReadString.charAt(start) == ch;
    }

    private void scanToken() {
        // jump all blank chars
        jumpBlank();

        // resolve symbols & operators
        if (resolveSymbol()) return;
        // resolve number
        if (resolveNumber()) return;
        // resolve string
        if (resolveString()) return;

        // resolve id
        resolveIDToken();
    }

    private void jumpBlank() {
        for (readChar(); ; readChar()) {
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
                    readChar();
                    addToken(SepToken.AND_TOKEN);
                } else {
                    addToken(SepToken.AMPERSAND_TOKEN);
                }
                break;
            }

            case '|': {
                if (readChar('|')) {
                    readChar();
                    addToken(SepToken.OR_TOKEN);
                } else {
                    addToken(SepToken.BAR_TOKEN);
                }
                break;
            }

            case '<': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.LTE_TOKEN);
                } else {
                    addToken(SepToken.LESS_TOKEN);
                }
                break;
            }

            case '>': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.GTE_TOKEN);
                } else {
                    addToken(SepToken.GREAT_TOKEN);
                }
                break;
            }

            case '!': {
                if (readChar('=')) {
                    readChar();
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

            case '=': {
                if (readChar('=')) {
                    readChar();
                    addToken(SepToken.EQUAL_TOKEN);
                }
                break;
            }

            default:
                return false;
        }

        return true;
    }

    private boolean resolveNumber() {
        if (Character.isDigit(peekChar)) {
            // int value parser

            // start index of digits
            int startIndex = start - 1;

            long v = 0;

            do {
                v = 10 * v + Character.digit(peekChar, 10);
                readChar();
            } while (Character.isDigit(peekChar));

            // end index of digit
            int endIndex = start;

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
        if (peekChar == '\"') {
            boolean returnFlag = false;
            StringBuilder builder = new StringBuilder();
            char lastChar = ' ';
            for (readChar(); ; readChar()) {
                if (peekChar == '\"' && returnFlag
                        && lastChar != '\\') {
                    break;
                } else if (peekChar == '\n') {
                    lineNumber += 1;
                    continue;
                } else if (peekChar != '\"') {
                    builder.append(peekChar);
                }
                lastChar = peekChar;
                returnFlag = true;
            }

            addToken(new StringToken(lineNumber, builder.toString()));
            readChar();
            return true;
        }
        return false;
    }

    private boolean resolveIDToken() {
        if (Character.isLetter(peekChar)) {
            StringBuilder buffer = new StringBuilder();

            do {
                buffer.append(peekChar);
                readChar();
            } while (Character.isLetterOrDigit(peekChar) || peekChar == '_');

            // token word
            String token = buffer.toString();

            switch (token) {

                case "true":
                case "false": {
                    addToken(new BoolToken(lineNumber, BoolToken.getBoolean(token)));
                    break;
                }

                default: {
                    addToken(ReservedToken.containsReservedToken(token) ?
                            new ReservedToken(lineNumber, token) :
                            new IDToken(lineNumber, token));
                    break;
                }
            }

            return true;
        }

        return false;
    }
}
