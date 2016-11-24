package dataservice.promotiondataservice;

import po.PromotionPO;
import util.DateUtil;
import util.Place;
import util.PromotionType;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by vivian on 16/10/13.
 */
public class PromotionDataService_Stub implements PromotionDataService{
    @Override
    public ResultMessage addPromotion(PromotionPO promotionpo) {
        if(promotionpo.getPromotionID().equals("201610120102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }

    }

    @Override
    public ResultMessage deletePromotion(String promotionID) {
        if(promotionID.equals("201610130101")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage updatePromotion(PromotionPO newPromotionPO) {
        if(newPromotionPO.getPromotionID().equals("201610130102")){
            return ResultMessage.SUCCESS;
        }else{
            return ResultMessage.FAILED;
        }
    }

    @Override
    public PromotionPO searchByPromotionID(String promotionID) {
        return new PromotionPO("201610120102",  PromotionType.Hotel_Birthday, 0.80,"2016-10-01", "2016-10-03","VivianCompany" ,"00000000",1, Place.XINJIEKOU, 0);
    }

    @Override
    public ArrayList<PromotionPO> searchPromotionsByType(PromotionType promotionType) {
        return null;
    }

    @Override
    public ArrayList<PromotionPO> searchHotelPromotions(String hotelID) {
        return null;
    }
}
