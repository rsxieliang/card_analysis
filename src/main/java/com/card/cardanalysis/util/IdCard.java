package com.card.cardanalysis.util;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xieliang
 * @Description
 * @Date Create in 上午 11:40 2018-07-02
 * Modified By:
 */
public class IdCard {

    private static Logger log = LoggerFactory.getLogger(IdCard.class);

    private static final String AREA_CODE="/area-code.properties";


    public static JSONObject analysisIdCard(String idCard) {
        JSONObject map = new JSONObject();

        String area = analysisAddress(idCard.substring(0, 6));
        map.put("address", area);

        String birthday = analysisBirthday(idCard);
        map.put("birthday", birthday);

        String sex = analysisSex(idCard);
        map.put("sex", sex);

        int age = analysisAge(idCard);
        map.put("age", age);

        return map;
    }

    /**
     * 根据身份证解析 籍贯所在地
     *
     * @param code
     * @return
     */
    public static String analysisAddress(String code) {
        Properties properties = new Properties();
        String area;
        try {
            properties.load(IdCard.class.getResourceAsStream(AREA_CODE));
            area = properties.getProperty(code);
            if (area == null) {
                return "未知区域";
            }
            area = new String(area.getBytes("iso-8859-1"), "utf-8");
            return area;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            log.error("解析身份证地址异常", ioe);
        }
        return null;
    }

    /**
     * 根据身份证解析 出生年月
     *
     * @param idCard
     * @return
     */
    public static String analysisBirthday(String idCard) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = analysisDate(idCard);
        return format.format(date);
    }

    public static Date analysisDate(String idCard) {
        String birthday = null;
        SimpleDateFormat format = null;
        Date date = new Date();
        if (idCard.length() == 18) {
            birthday = idCard.substring(6, 14);
            format = new SimpleDateFormat("yyyyMMdd");
        } else if (idCard.length() == 15) {
            birthday = idCard.substring(6, 12);
            format = new SimpleDateFormat("yyMMdd");
        }
        try {
            date = format.parse(birthday);
        } catch (ParseException pe) {
            log.error("日期转换异常===>", pe);
        }
        return date;
    }

    /**
     * 根据身份证 解析年龄
     *
     * @param idCard
     * @return
     */
    public static int analysisAge(String idCard) {
        Date date = analysisDate(idCard);
        int age = DateKit.diffYear(date, new Date());
        return age;
    }


    /**
     * 根据身份证 解析性别 身份证倒数第二位 奇数代表男 偶数代表女
     *
     * @param idCard
     * @return
     */
    public static String analysisSex(String idCard) {
        String sex = null;
        if (idCard.length() == 15) {
            sex = idCard.substring(14,15);
        } else if (idCard.length() == 18) {
            sex = idCard.substring(16, 17);
        }
        System.out.println(sex);
        int se = Integer.parseInt(sex);
        return se % 2 == 0 ? "女" : "男";
    }



    public static void main(String[] args) {

        Map<String,Object> map= analysisIdCard("513822199604297235");
        System.out.println(map.get("address")+"==="+map.get("sex")+"===="+map.get("birthday"));
    }


}
