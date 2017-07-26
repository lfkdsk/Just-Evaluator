/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.token;

import com.lfkdsk.justel.exception.UnSupportMethodException;

import java.util.Objects;

/**
 * Created by liufengkai on 2017/7/26.
 */
public class BoolToken extends ReservedToken {

    enum BooleanEnum {
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

    public static BooleanEnum getBoolean(String token) {
        if (Objects.equals(token, "true")) {
            return BooleanEnum.TRUE;
        } else if (Objects.equals(token, "false")) {
            return BooleanEnum.FALSE;
        }

        throw new UnSupportMethodException("use wrong boolean token " + token);
    }

    @Override
    public String getText() {
        return super.getText();
    }

    @Override
    public boolean isBool() {
        return true;
    }
}
