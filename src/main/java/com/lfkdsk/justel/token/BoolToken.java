/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.token;

import com.lfkdsk.justel.exception.UnSupportMethodException;

/**
 * Boolean Token
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see com.lfkdsk.justel.token.Token
 * @see com.lfkdsk.justel.token.ReservedToken
 * @see com.lfkdsk.justel.literal.BoolLiteral
 */
public class BoolToken extends ReservedToken {

    public enum BooleanEnum {
        TRUE("true"),
        FALSE("false");

        private final String booleanToken;

        BooleanEnum(String token) {
            this.booleanToken = token;
        }

        @Override
        public String toString() {
            return booleanToken;
        }
    }

    public BoolToken(int lineNumber, BooleanEnum booleanEnum) {
        super(lineNumber, booleanEnum.toString());
    }

    /**
     * Judge token => Boolean Value
     *
     * @param token tokenString
     * @return BooleanEnum
     */
    public static BooleanEnum booleanValue(String token) {
        for (BooleanEnum booleanEnum : BooleanEnum.values()) {
            if (booleanEnum.booleanToken.equals(token)) {
                return booleanEnum;
            }
        }

        throw new UnSupportMethodException("use wrong boolean token " + token);
    }

    @Override
    public boolean isBool() {
        return true;
    }

    @Override
    public String toString() {
        return "BoolToken{" +
                "token='" + token + '\'' +
                ", lineNumber=" + lineNumber +
                ", tag=" + tag +
                '}';
    }
}
