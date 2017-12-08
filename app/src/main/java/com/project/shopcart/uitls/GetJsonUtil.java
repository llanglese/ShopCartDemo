package com.project.shopcart.uitls;


import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Administrator on 2017/2/4.
 */
public class GetJsonUtil {
    private static String TAG = GetJsonUtil.class.getSimpleName();
    private static String CODE = "code";
    private static String ERROR = "desc";
    private static String RESULT = "result";
    private static String DATA = "data";
    private static String LstDATA = "listData";
    private static JSONObject getJSONObject(String resultJson) {
        try {
            return new JSONObject(resultJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param resultJson
     * @return
     */
    public static int getResponseCode(String resultJson) {
        try {
//            if (getJSONObject(resultJson).getInt(CODE) == 10010) {
//
//            }
            return getJSONObject(resultJson).getInt(CODE);//TODO CODE 改为你服务器设定的key
        } catch (Exception e) {
           // KLog.e(TAG, "getResponseCode No value for status");
        }
        return -1;
    }

    /**
     * @param resultJson
     * @return
     */
    public static String getResponseResult(String resultJson) {
        try {
            return getJSONObject(resultJson).getString(RESULT);//TODO DATA 改为你服务器设定的key
        } catch (Exception e) {
            //KLog.e(TAG, "getResponseResult No value for result");
        }
        return null;
    }

    /**
     * @param resultJson
     * @return
     */
    public static String getResponseData(String resultJson) {
        try {
            return getJSONObject(resultJson).getString(DATA);//TODO DATA 改为你服务器设定的key
        } catch (Exception e) {
          //  KLog.e(TAG, "getResponseData No value for data");
        }
        return null;
    }
    /**
     * @param resultJson
     * @return
     */
    public static String getResponseLstData(String resultJson) {
        try {
            return getJSONObject(resultJson).getString(LstDATA);//TODO DATA 改为你服务器设定的key
        } catch (Exception e) {
           // KLog.e(TAG, "getResponseData No value for data");
        }
        return null;
    }
    /**
     * @param resultJson
     * @return
     */
    public static String getResponseResultData(String resultJson) {
        try {
            return getJSONObject(getResponseResult(resultJson)).getString(DATA);//TODO DATA 改为你服务器设定的key
        } catch (Exception e) {
           // KLog.e(TAG, "getResponseData No value for result data");
        }
        return null;
    }
//
//    /**
//     * @param resultJson
//     * @return
//     */
//    public static String getResponseError(String resultJson) {
//        try {
//            return getJSONObject(resultJson).getString(ERROR);//TODO DATA 改为你服务器设定的key
//        } catch (Exception e) {
//          //  KLog.e(TAG, "getResponseData No value for error");
//        }
//        return AppConstantValue.NETWORK_STATE;
//    }
}
