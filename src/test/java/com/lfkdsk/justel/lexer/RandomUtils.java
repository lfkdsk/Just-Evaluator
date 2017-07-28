/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.lexer;

import java.util.Random;

/**
 * Created by liufengkai on 16/4/16.
 */
public class RandomUtils {
    public static int RandomInt(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    public static float RandomFloat(double min, double max) {
        return (float) (min + Math.random() * (max - min));
    }

    public static String RandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String RandomStringWithPerfix(String perfix, int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - perfix.length(); i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return perfix + sb.toString();
    }
}
