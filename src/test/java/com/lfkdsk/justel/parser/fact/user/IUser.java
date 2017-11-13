package com.lfkdsk.justel.parser.fact.user;

/**
 * 维度——人
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface IUser {

    long getUserTag();

    long getUserTag2();

    long getUserTag3();

    long getUserTag4();

    long getUserTag5();

    long getUserTag6();

    long getUserTag7();

    long getUserTag8();

    long getUserTag9();

    long getUserTag10();

    long getUserTag11();

    long getUserTag12();

    long getUserTag13();

    long getUserTag14();

    long getUserTag15();

    long getUserTag16();

    long getUserTag17();

    long getUserTag18();

    long getUserTag19();

    long getUserTag20();

    /**
     * 用户标记位判断
     *
     * @param maskBit
     * @param tagNumber
     * @return
     */
    default boolean hasTagOn(int maskBit, int tagNumber) {
        long userTag;
        switch (tagNumber) {
            case 1:
                userTag = this.getUserTag();
                break;
            case 2:
                userTag = this.getUserTag2();
                break;
            case 3:
                userTag = this.getUserTag3();
                break;
            case 4:
                userTag = this.getUserTag4();
                break;
            case 5:
                userTag = this.getUserTag5();
                break;
            case 6:
                userTag = this.getUserTag6();
                break;
            case 7:
                userTag = this.getUserTag7();
                break;
            case 8:
                userTag = this.getUserTag8();
                break;
            case 9:
                userTag = this.getUserTag9();
                break;
            case 10:
                userTag = this.getUserTag10();
                break;
            case 11:
                userTag = this.getUserTag11();
                break;
            case 12:
                userTag = this.getUserTag12();
                break;
            case 13:
                userTag = this.getUserTag13();
                break;
            case 14:
                userTag = this.getUserTag14();
                break;
            case 15:
                userTag = this.getUserTag15();
                break;
            case 16:
                userTag = this.getUserTag16();
                break;
            case 17:
                userTag = this.getUserTag17();
                break;
            case 18:
                userTag = this.getUserTag18();
                break;
            case 19:
                userTag = this.getUserTag19();
                break;
            case 20:
                userTag = this.getUserTag20();
                break;
            default:
                userTag = 0L;
        }

        return this.unmask(userTag, 1L << maskBit);
    }

    default boolean unmask(long tag, long mask) {
        return (tag & mask) == mask;
    }
}
