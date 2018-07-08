package com.card.cardanalysis.service;

import com.alibaba.fastjson.JSONObject;
import com.card.cardanalysis.util.IdCard;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl {


    public JSONObject cardAnalysis(JSONObject object){
        return  IdCard.analysisIdCard(object.getString("cardNo"));

    }

}
