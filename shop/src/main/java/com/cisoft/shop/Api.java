package com.cisoft.shop;

/**
 * Created by comet on 2014/10/18.
 */
public class Api {

    public static final String SERVER_URL = "http://123.56.84.203:8584/Lazyer";
//    public static final String SERVER_URL = "http://lazyorder.yliec.com";
    public static final String URL_SEPERATOR = "/";
    public static final String FILE_SUFFIX = ".json";

    public static final String ORDER_STATE_CREATE = "CREATE";
    public static final String ORDER_STATE_READY = "READY";
    public static final String ORDER_STATE_CANCEL = "CANCEL";

    /* 这里存放响应的状态码 */
    public static final int RESPONSE_STATE_SUCCESS = 200;
    public static final int RESPONSE_STATE_FAILURE = 404;
    public static final int RESPONSE_STATE_SERVICE_EXCEPTION = 103;
    public static final int RESPONSE_STATE_NOT_NET = 102;
    public static final int RESPONSE_STATE_NET_POOR = 101;
    public static final int RESPONSE_STATE_ERROR_LOGIN = 400;


    /* 这里存放全局使用的key */
    public static final String KEY_STATE = "state";
    public static final String KEY_DATA = "data";
    public static final String KEY_MESSAGE = "message";

    /* 这里存放API接口里的模块名（控制器名）,以"MODULE_"打头 */
    public static final String MODULE_MERCHANTS = "merchants";
    public static final String MODULE_MER_CATEGORY = "merCategory";
    public static final String MODULE_ORDER = "order";


    public static final String MODULE_COMMODITY = "commodity";
    public static final String MODULE_COM_CATEGORY = "category";

    public static final String MODULE_BUILD = "building";

    public static final String MODULE_HISTORY_ORDER = "order";

    public static final String MODULE_EXPMER = "expmer";

    public static final String MODULE_EXPRESS = "express";

    public static final String MODULE_SETTING = "setting";

    /* 这里存放API接口里的方法名,以"METHOD_+模块名"打头 */
    public static final String METHOD_MERCHANTS_FIND_BY_TYPE_ID = "findMerchantsOrCanteen";
    public static final String METHOD_MER_CATEGORY_FIND_ALL = "findAll";
    public static final String METHOD_MER_UPDATE_OPERATING_STATE = "updateOperatingState";
    public static final String METHOD_MER_MER_LOGIN = "merLogin";


    public static final String METHOD_ORDER_FIND_ORDERS_BY_MER_ID = "findOrdersByMerId";
    public static final String METHOD_ORDER_UPDATE_ORDER_STATE= "updateOrderState";

    public static final String METHOD_COMMODITY_FIND_ALL_BY_MER_ID = "findCommodityByMerchantsId";
    public static final String METHOD_COMMODITY_FIND_BY_MER_AND_TYPE_ID = "findCommodityByMerchantsIdAndTypeId";
    public static final String METHOD_COMMODITY_FIND_COMMODITY_BY_KEY = "findCommodityByKey";
    public static final String METHOD_COMMODITY_UPDATE_COM_STATE = "updateComState";

    public static final String METHOD_CATEGORY_FIND_ALL_BY_MER_ID = "findCategoryByMerchantsId";

    public static final String METHOD_BUILD_FIND_ALL = "findBuildings";

    public static final String METHOD_HIS_ORDER_FIND_ALL = "findOrderByUserPhone";

    public static final String METHOD_EXPMER_EXPMER_LOGIN = "expmerLogin";
    public static final String METHOD_EXPMER_UPDATE_OPERATING_STATE = "updateOperatingState";

    public static final String METHOD_EXPRESS_FIND_EXPRESS_BY_STATE = "findExpressByState";

    public static final String METHOD_EXPRESS_UPDATE_EXPRESS_STATUE = "updateExpressStatue";

    public static final String METHOD_CHECK_UPDATE = "checkUpdate";

    /* 这里存放返回json的key,以"KEY_ + 模块名简写"打头,以模块扎堆↖(^ω^)↗ */

    //商家KEY
    public static final String KEY_MER_PAGE = "page";
    public static final String KEY_MER_PAGER = "size";
    public static final String KEY_MER_DATA = "data";
    public static final String KEY_MER_ID = "id";
    public static final String KEY_MER_MONTH_SALES = "monthSaleNum";
    public static final String KEY_MER_OPEN_TIME = "merOpenTime";
    public static final String KEY_MER_CLOSE_TIME = "merCloseTime";
    public static final String KEY_MER_NAME = "merName";
    public static final String KEY_MER_FACE_PIC = "merPic";
    /**
     * 0表示未营业
     * 1表示营业
     */
    public static final String KEY_MER_OPERATING_STATE = "operatingState";
    public static final String KEY_MER_ADDRESS = "merAddress";
    public static final String KEY_MER_PROMOTION_INFO = "sales";
    public static final String KEY_MER_TYPE_ID = "typeId";
    public static final String KEY_MER_MER_ID = "merId";
    public static final String KEY_MER_MER_PHONE = "merPhone";
    public static final String KEY_MER_MER_PWD = "merPwd";
    public static final String KEY_MER_CID = "cID";
    public static final String KEY_MER_NODISTRIBUTION_PRICE = "nodistributionPrice";


    //订单KEY
    public static final String KEY_ORDER_MER_ID = "merId";
    public static final String KEY_ORDER_PAGE = "page";
    public static final String KEY_ORDER_SIZE = "size";
    public static final String KEY_ORDER_ID = "id";
    public static final String KEY_ORDER_ORDER_ID = "orderId";
    public static final String KEY_ORDER_USER_PHONE = "userPhone";
    public static final String KEY_ORDER_USER_NAME = "userName";
    public static final String KEY_ORDER_TIME_GO = "timeGo";                                //已下单时间
    public static final String KEY_ORDER_ORDER_STATE = "orderStatue";                        //订单状态 CRAETE（最新订单）   READY（已准备）
    public static final String KEY_ORDER_STATE = "state";                        //订单状态 CRAETE（最新订单）   READY（已准备）
    public static final String KEY_ORDER_ORDER_NUMBER = "orderNumber";
    public static final String KEY_ORDER_ORDER_CONTENT = "content";
    public static final String KEY_ORDER_RDER_PRICE = "orderPrice";
    public static final String KEY_ORDER_DISTRIBUTION_PRICE = "distributionPrice";          //配送费，为0则免配送费
    public static final String kEY_ORDER_ADDRESS = "address";                               //地址信息
    public static final String KEY_ORDER_ORDER_COMMODITY_VO_LIST = "orderCommodityVOList";  //订单商品列表
    public static final String KEY_ORDER_COM_ID = "comId";
    public static final String KEY_ORDER_COM_NAME = "comName";
    public static final String KEY_ORDER_COM_NUM = "comNum";
    public static final String KEY_ORDER_COM_PRICE = "price";                               //单价
    public static final String KEY_ORDER_DEDUCTION = "deduction";                           //优惠价格
    public static final String KEY_ORDER_SETTLED_PRICE = "settledPrice";                           //结算后价格


    //快递商家订单key
    public static final String KEY_EXPRESS_EXPMER_ID = "expmerId";
    public static final String KEY_EXPRESS_EXPRESS_ID = "expressId";
    public static final String KEY_EXPRESS_EXPRESS_STATE = "expressState";
    public static final String KEY_EXPRESS_PAGE = "page";
    public static final String KEY_EXPRESS_SIZE = "size";
    public static final String KEY_EXPRESS_STATE = "state";
    public static final String KEY_EXPRESS_DATA = "data";
    public static final String KEY_EXPRESS_ID = "id";
    public static final String KEY_EXPRESS_ADDRESS = "address";
    public static final String KEY_EXPRESS_CONTENT = "content";
    public static final String KEY_EXPRESS_DISTRIBUTION_PRICE = "distributionPrice";
    public static final String KEY_EXPRESS_EXPRESS_NUMBER = "expressNumber";
    public static final String KEY_EXPRESS_EXPRESS_TIME = "expressTime";
    public static final String KEY_EXPRESS_GET_MESSAGE_TIME = "getmessageTime";
    public static final String KEY_EXPRESS_MESSAGE = "message";
    public static final String KEY_EXPRESS_STATUE = "statue";
    public static final String KEY_EXPRESS_USER_NAME = "userName";
    public static final String KEY_EXPRESS_USER_PHONE = "userPhone";
    public static final String KEY_EXPRESS_EXPRESS_MERCHANT = "expressMerchant";



    public static final String KEY_MC_DATA = "data";
    public static final String KEY_MC_CATEGORY_ID = "id";
    public static final String KEY_MC_CATEGORY_NAME = "merCategoryName";


    //商品KEY
    public static final String KEY_COM_MER_ID = "merId";
    public static final String KEY_COM_PAGE = "page";
    public static final String KEY_COM_SIZE = "size";
    public static final String KEY_COM_SORT = "sortType";
    public static final String KEY_COM_ID = "id";
    public static final String KEY_COM_NAME = "cmName";
    public static final String KEY_COM_PICTURE = "cmPicture";
    public static final String KEY_COM_CAT_ID = "catId";
    public static final String KEY_COM_CAT_NAME = "catName";
    public static final String KEY_COM_SALES_NUM = "salesNum";
    public static final String KEY_COM_PRICE = "cmPrice";
    public static final String KEY_COM_TYPE_ID = "typeId";
    public static final String KEY_COM_KEY_NAME = "keyName";
    public static final String KEY_COM_COM_ID = "comId";
    public static final String KEY_COM_COM_STATE = "comState";


    public static final String KEY_CAT_MER_ID = "merId";
    public static final String KEY_CAT_ID = "id";
    public static final String KEY_CAT_NAME = "catName";


    public static final String KEY_BUILD_DATA = "data";
    public static final String KEY_BUILD_ID = "id";
    public static final String KEY_BUILD_NAME = "building";
    public static final String KEY_BUILD_SCHOOL_ID = "schoolId";


    public static final String KEY_HIS_ORDER_DATA = "data";
    public static final String KEY_HIS_ORDER_PAGE = "page";
    public static final String KEY_HIS_ORDER_PAGER = "size";
    public static final String KEY_HIS_ORDER_USER_PHONE = "userPhone";
    public static final String KEY_HIS_ORDER_ID = "id";
    public static final String KEY_HIS_ORDER_MER_ID = "merId";
    public static final String KEY_HIS_ORDER_MER_NAME = "merName";
    public static final String KEY_HIS_ORDER_TIME = "orderDate";
    public static final String KEY_HIS_ORDER_ADDRESS = "address";
    public static final String KEY_HIS_ORDER_GOOD_LIST = "orderComVOlist";
    public static final String KEY_HIS_ORDER_TOTAL_PRICE = "moneyAll";
    public static final String KEY_HIS_ORDER_GOOD_NAME = "comName";
    public static final String KEY_HIS_ORDER_GOOD_COUNT = "comNum";

    public static final String KEY_EXPMER_ID = "id";
    public static final String KEY_EXPMER_EXPMER_PHONE = "expmerPhone";
    public static final String KEY_EXPMER_EXPMER_PWD = "expmerPwd";
    public static final String KEY_EXPMER_EXPMER_NAME = "expmerName";
    public static final String KEY_EXPMER_CID = "cID";
    public static final String KEY_EXPMER_ADDRESS = "expmerAddress";
    public static final String KEY_EXPMER_OPENTIME = "expmerOpenTime";
    public static final String KEY_EXPMER_CLOSETIME = "expmerCloseTime";
    public static final String KEY_EXPMER_PIC = "expmerPic";
    public static final String KEY_EXPMER_OPERATING_STATE = "operatingState";
    public static final String KEY_EXPMER_SALES = "sales";

    public static final String KEY_SETTING_TYPE = "type";
    public static final String KEY_SETTING_VERSION_CODE = "versionCode";
    public static final String KEY_SETTING_VERSION_NAME = "versionName";
    public static final String KEY_SETTING_DOWNLOAD_URL = "downloadUrl";
    public static final String KEY_SETTING_UPDATE_CONTENT = "updateContent";

}
