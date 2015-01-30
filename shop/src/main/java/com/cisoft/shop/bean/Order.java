package com.cisoft.shop.bean;


import com.cisoft.shop.ApiConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lecion on 2014-12-09
 */
public class Order extends AbsBean {

    private int id;
    private String userPhone;
    private String userName;
    private String timeGo;
    private String orderState;
    private String orderNumber;
    private String content;
    private double moneyAll;
    private List<OrderGoods> goodsList;

    public Order(JSONObject jsonObj){
        this.parse(jsonObj);
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimeGo() {
        return timeGo;
    }

    public void setTimeGo(String timeGo) {
        this.timeGo = timeGo;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getMoneyAll() {
        return moneyAll;
    }

    public void setMoneyAll(double moneyAll) {
        this.moneyAll = moneyAll;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public void parse(JSONObject jsonObj) {
        goodsList = new ArrayList<OrderGoods>();
        try {
            Iterator<String> iterator =  jsonObj.keys();
            String key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                if (key.equals(ApiConstants.KEY_ORDER_ID)) {
                    this.id = jsonObj.getInt(ApiConstants.KEY_ORDER_ID);
                } else if (key.equals(ApiConstants.KEY_ORDER_USER_PHONE)) {
                    this.userPhone =  jsonObj.getString(ApiConstants.KEY_ORDER_USER_PHONE);
                } else if (key.equals(ApiConstants.KEY_ORDER_USER_NAME)) {
                    this.userName =  jsonObj.getString(ApiConstants.KEY_ORDER_USER_NAME);
                } else if (key.equals(ApiConstants.KEY_ORDER_TIME_GO)) {
                    this.timeGo = jsonObj.getString(ApiConstants.KEY_ORDER_TIME_GO);
                } else if (key.equals(ApiConstants.KEY_ORDER_ORDER_STATE)) {
                    this.orderState = jsonObj.getString(ApiConstants.KEY_ORDER_ORDER_STATE);
                } else if (key.equals(ApiConstants.KEY_ORDER_ORDER_NUMBER)) {
                    this.orderNumber = jsonObj.getString(ApiConstants.KEY_ORDER_ORDER_NUMBER);
                } else if (key.equals(ApiConstants.KEY_ORDER_ORDER_CONTENT)) {
                    this.content = jsonObj.getString(ApiConstants.KEY_ORDER_ORDER_CONTENT);
                } else if (key.equals(ApiConstants.KEY_ORDER_MONEY_ALL)) {
                    this.moneyAll = jsonObj.getDouble(ApiConstants.KEY_ORDER_MONEY_ALL);
                } else if (key.equals(ApiConstants.KEY_ORDER_ORDER_COMMODITY_VO_LIST)) {
                    JSONArray goodListArr = jsonObj.getJSONArray(ApiConstants.KEY_ORDER_ORDER_COMMODITY_VO_LIST);
                    JSONObject goodsObj = null;
                    OrderGoods goods = null;
                    for (int i = 0; i < goodListArr.length(); i++) {
                        goodsObj = goodListArr.getJSONObject(i);
                        goods = new OrderGoods(goodsObj);
                        goodsList.add(goods);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}