package cn.com.yusys.yusp.constant;

/**
 * 结算指令状态.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public enum SettleStatusEnum {
    /**
     * 待生效
     */
    TO_TAKE_EFFECT("0"),
    /**
     * 应履行.
     */
    SHOULD_PERFORM("Y"),
    /**
     * 交割中.
     */
    HANDLING("1"),
    /**
     * 待取消.
     */
    TO_CANCEL("2"),
    /**
     * 截止中
     */
    CUT_OFFING("3"),
    /**
     * 取消中.
     */
    CANCELING("4"),
    /**
     * 划转中.
     */
    TRANSFERING("5"),
    ADJUSTING("6"),
    WAITING("W"),
    SUCCESS("S"),
    /**
     * 已终止.
     */
    TERMINATED("T"),
    /**
     * 失败.
     */
    FAILURE("F"),
    /**
     * 已取消.
     */
    CANCELED("C"),
    /**
     * 作废
     */
    INVALID("X");


    private String code;

    SettleStatusEnum(String code) {
        this.code = code;
    }

    /**
     * 获取编码.
     *
     * @return
     */
    public String getCode() {
        return code;
    }
}
