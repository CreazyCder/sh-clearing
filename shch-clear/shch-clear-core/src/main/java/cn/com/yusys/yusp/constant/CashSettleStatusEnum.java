package cn.com.yusys.yusp.constant;

/**
 * 资金结算状态.
 *
 * @author wpplu
 * @since 2019/11/18
 */
public enum CashSettleStatusEnum {
    /**
     * 应履行.默认值.
     */
    SHOULD_PERFORM("0"),
    /**
     * 交割中.
     */
    HANDLING("1"),
    /**
     * 等款.
     */
    WAIT_CASHING("2"),
    /**
     * 款足。
     */
    BOND_OK("3"),
    /**
     * 截止中
     */
    CUT_OFFING("4"),
    /**
     * 取消中.
     */
    CANCELING("5"),
    /**
     * 成功
     */
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

    CashSettleStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
