package vo;

import po.PromotionPO;
import util.DateUtil;
import util.PromotionType;

import static util.EqualJudgeHelper.judgeEqual;

/**
 * Created by vivian on 16/10/24.
 */
public class Promotion_WebSpecialDateVO extends Promotion_WebVO {
    /**
     * @param promotionID       策略编号
     * @param promotionType     策略类型
     * @param promotionDiscount 策略折扣
     * @param startDate         策略起始日期
     * @param endDate           策略截止日期
     */
    public Promotion_WebSpecialDateVO(String promotionID, PromotionType promotionType, double promotionDiscount, DateUtil startDate, DateUtil endDate) {
        super(promotionID, promotionType, promotionDiscount, startDate, endDate);
    }

    public boolean equals(Object o) {
        if (o instanceof Promotion_WebSpecialDateVO) {
            Promotion_WebSpecialDateVO promotion_WebSpecialDateVO = (Promotion_WebSpecialDateVO) o;
            return compareData(promotion_WebSpecialDateVO);
        }
        return false;
    }

    public int hashCode() {
        return promotionID.hashCode();
    }

    private boolean compareData(Promotion_WebSpecialDateVO pvo) {
        return judgeEqual(pvo.promotionID, this.promotionID)
                && judgeEqual(pvo.promotionType, this.promotionType)
                && judgeEqual(pvo.startDate, this.startDate)
                && judgeEqual(pvo.endDate, this.endDate)
                && judgeEqual(pvo.promotionDiscount, this.promotionDiscount);
    }

    public PromotionPO toPO() {
        return new PromotionPO(this.promotionID, this.promotionType, this.promotionDiscount,
                this.startDate.toString(), this.endDate.toString(),
                null, null, 0, null, 0);
    }
}
