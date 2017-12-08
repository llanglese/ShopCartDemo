/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.project.shopcart.uitls;

import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用字符串(String)相关类,为null时返回""
 *
 * @author Lemon
 * @use StringUtil.
 */
public class StringUtil {
    private static final String TAG = "StringUtil";

    public StringUtil() {
    }

    public static final String EMPTY = "无";
    public static final String UNKNOWN = "未知";
    public static final String UNLIMITED = "不限";

    public static final String I = "我";
    public static final String YOU = "你";
    public static final String HE = "他";
    public static final String SHE = "她";
    public static final String IT = "它";

    public static final String MALE = "男";
    public static final String FEMALE = "女";

    public static final String TODO = "未完成";
    public static final String DONE = "已完成";

    public static final String FAIL = "失败";
    public static final String SUCCESS = "成功";

    public static final String SUNDAY = "日";
    public static final String MONDAY = "一";
    public static final String TUESDAY = "二";
    public static final String WEDNESDAY = "三";
    public static final String THURSDAY = "四";
    public static final String FRIDAY = "五";
    public static final String SATURDAY = "六";

    public static final String YUAN = "元";


    private static String currentString = "";

    /**
     * 获取刚传入处理后的string
     *
     * @return
     * @must 上个影响currentString的方法 和 这个方法都应该在同一线程中，否则返回值可能不对
     */
    public static String getCurrentString() {
        return currentString == null ? "" : currentString;
    }

    //获取string,为null时返回"" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 获取string,为null则返回""
     *
     * @param tv
     * @return
     */
    public static String getString(TextView tv) {
        if (tv == null || tv.getText() == null) {
            return "";
        }
        return getString(tv.getText().toString());
    }

    /**
     * 获取string,为null则返回""
     *
     * @param object
     * @return
     */
    public static String getString(Object object) {
        return object == null ? "" : getString(String.valueOf(object));
    }

    /**
     * 获取string,为null则返回""
     *
     * @param cs
     * @return
     */
    public static String getString(CharSequence cs) {
        return cs == null ? "" : getString(cs.toString());
    }

    /**
     * 获取string,为null则返回""
     *
     * @param s
     * @return
     */
    public static String getString(String s) {
        return s == null ? "" : s;
    }

    //获取string,为null时返回"" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //获取去掉前后空格后的string<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 获取去掉前后空格后的string,为null则返回""
     *
     * @param tv
     * @return
     */
    public static String getTrimedString(TextView tv) {
        return getTrimedString(getString(tv));
    }

    /**
     * 获取去掉前后空格后的string,为null则返回""
     *
     * @param object
     * @return
     */
    public static String getTrimedString(Object object) {
        return getTrimedString(getString(object));
    }

    /**
     * 获取去掉前后空格后的string,为null则返回""
     *
     * @param cs
     * @return
     */
    public static String getTrimedString(CharSequence cs) {
        return getTrimedString(getString(cs));
    }

    /**
     * 获取去掉前后空格后的string,为null则返回""
     *
     * @param s
     * @return
     */
    public static String getTrimedString(String s) {
        return getString(s).trim();
    }

    //获取去掉前后空格后的string>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //获取去掉所有空格后的string <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 获取去掉所有空格后的string,为null则返回""
     *
     * @param tv
     * @return
     */
    public static String getNoBlankString(TextView tv) {
        return getNoBlankString(getString(tv));
    }

    /**
     * 获取去掉所有空格后的string,为null则返回""
     *
     * @param object
     * @return
     */
    public static String getNoBlankString(Object object) {
        return getNoBlankString(getString(object));
    }

    /**
     * 获取去掉所有空格后的string,为null则返回""
     *
     * @param cs
     * @return
     */
    public static String getNoBlankString(CharSequence cs) {
        return getNoBlankString(getString(cs));
    }

    /**
     * 获取去掉所有空格后的string,为null则返回""
     *
     * @param s
     * @return
     */
    public static String getNoBlankString(String s) {
        return getString(s).replaceAll(" ", "");
    }

    //获取去掉所有空格后的string >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //获取string的长度<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 获取string的长度,为null则返回0
     *
     * @param tv
     * @param trim
     * @return
     */
    public static int getLength(TextView tv, boolean trim) {
        return getLength(getString(tv), trim);
    }

    /**
     * 获取string的长度,为null则返回0
     *
     * @param object
     * @param trim
     * @return
     */
    public static int getLength(Object object, boolean trim) {
        return getLength(getString(object), trim);
    }

    /**
     * 获取string的长度,为null则返回0
     *
     * @param cs
     * @param trim
     * @return
     */
    public static int getLength(CharSequence cs, boolean trim) {
        return getLength(getString(cs), trim);
    }

    /**
     * 获取string的长度,为null则返回0
     *
     * @param s
     * @param trim
     * @return
     */
    public static int getLength(String s, boolean trim) {
        s = trim ? getTrimedString(s) : s;
        return getString(s).length();
    }

    //获取string的长度>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //判断字符是否非空 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 判断字符是否非空
     *
     * @param tv
     * @param trim
     * @return
     */
    public static boolean isNotEmpty(TextView tv, boolean trim) {
        return isNotEmpty(getString(tv), trim);
    }

    /**
     * 判断字符是否非空
     *
     * @param object
     * @param trim
     * @return
     */
    public static boolean isNotEmpty(Object object, boolean trim) {
        return isNotEmpty(getString(object), trim);
    }

    /**
     * 判断字符是否非空
     *
     * @param cs
     * @param trim
     * @return
     */
    public static boolean isNotEmpty(CharSequence cs, boolean trim) {
        return isNotEmpty(getString(cs), trim);
    }

    /**
     * 判断字符是否非空
     *
     * @param s
     * @param trim
     * @return
     */
    public static boolean isNotEmpty(String s, boolean trim) {
        //		Log.i(TAG, "getTrimedString   s = " + s);
        if (s == null) {
            return false;
        }
        if (trim) {
            s = s.trim();
        }
        if (s.length() <= 0) {
            return false;
        }

        currentString = s;

        return true;
    }

    //判断字符是否非空 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //判断字符类型 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    //判断手机格式是否正确
    public static boolean isPhone(String phone) {
        if (isNotEmpty(phone, true) == false) {
            return false;
        }

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-2,5-9])|(17[0-9]))\\d{8}$");

        currentString = phone;

        return p.matcher(phone).matches();
    }

    //判断email格式是否正确
    public static boolean isEmail(String email) {
        if (isNotEmpty(email, true) == false) {
            return false;
        }

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);

        currentString = email;

        return p.matcher(email).matches();
    }

    //判断是否全是数字
    public static boolean isNumer(String number) {
        if (isNotEmpty(number, true) == false) {
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(number);
        if (!isNum.matches()) {
            return false;
        }

        currentString = number;

        return true;
    }

    /**
     * 判断字符类型是否是号码或字母
     *
     * @param inputed
     * @return
     */
    public static boolean isNumberOrAlpha(String inputed) {
        if (inputed == null) {
            Log.e(TAG, "isNumberOrAlpha  inputed == null >> return false;");
            return false;
        }
        Pattern pNumber = Pattern.compile("[0-9]*");
        Matcher mNumber;
        Pattern pAlpha = Pattern.compile("[a-zA-Z]");
        Matcher mAlpha;
        for (int i = 0; i < inputed.length(); i++) {
            mNumber = pNumber.matcher(inputed.substring(i, i + 1));
            mAlpha = pAlpha.matcher(inputed.substring(i, i + 1));
            if (!mNumber.matches() && !mAlpha.matches()) {
                return false;
            }
        }

        currentString = inputed;
        return true;
    }

    /**
     * 判断字符类型是否是身份证号
     *
     * @param idCard
     * @return
     */
    public static boolean isIDCard(String idCard) {
        if (isNumberOrAlpha(idCard) == false) {
            return false;
        }
        idCard = getString(idCard);
        if (idCard.length() == 15) {
            Log.w(TAG, "isIDCard idCard.length() == 15 old IDCard");
            currentString = idCard;
            return true;
        }
        if (idCard.length() == 18) {
            currentString = idCard;
            return true;
        }

        return false;
    }

    public static final String HTTP = "http";
    public static final String URL_PREFIX = "http://";
    public static final String URL_PREFIXs = "https://";
    public static final String URL_STAFFIX = URL_PREFIX;
    public static final String URL_STAFFIXs = URL_PREFIXs;

    /**
     * 判断字符类型是否是网址
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        if (isNotEmpty(url, true) == false) {
            return false;
        } else if (!url.startsWith(URL_PREFIX) && !url.startsWith(URL_PREFIXs)) {
            return false;
        }

        currentString = url;
        return true;
    }

    public static final String FILE_PATH_PREFIX = "file://";

    /**
     * 判断文件路径是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFilePathExist(String path) {
        return StringUtil.isFilePath(path) && new File(path).exists();
    }

    /**
     * 判断字符类型是否是路径
     *
     * @param path
     * @return
     */
    public static boolean isFilePath(String path) {
        if (isNotEmpty(path, true) == false) {
            return false;
        }

        if (!path.contains(".") || path.endsWith(".")) {
            return false;
        }

        currentString = path;

        return true;
    }

    //判断字符类型 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //提取特殊字符<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 去掉string内所有非数字类型字符
     *
     * @param tv
     * @return
     */
    public static String getNumber(TextView tv) {
        return getNumber(getString(tv));
    }

    /**
     * 去掉string内所有非数字类型字符
     *
     * @param object
     * @return
     */
    public static String getNumber(Object object) {
        return getNumber(getString(object));
    }

    /**
     * 去掉string内所有非数字类型字符
     *
     * @param cs
     * @return
     */
    public static String getNumber(CharSequence cs) {
        return getNumber(getString(cs));
    }

    /**
     * 去掉string内所有非数字类型字符
     *
     * @param s
     * @return
     */
    public static String getNumber(String s) {
        if (isNotEmpty(s, true) == false) {
            return "";
        }

        String numberString = "";
        String single;
        for (int i = 0; i < s.length(); i++) {
            single = s.substring(i, i + 1);
            if (isNumer(single)) {
                numberString += single;
            }
        }

        return numberString;
    }

    //提取特殊字符>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //校正（自动补全等）字符串<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 获取网址，自动补全
     *
     * @param tv
     * @return
     */
    public static String getCorrectUrl(TextView tv) {
        return getCorrectUrl(getString(tv));
    }

    /**
     * 获取网址，自动补全
     *
     * @param url
     * @return
     */
    public static String getCorrectUrl(String url) {
        Log.i(TAG, "getCorrectUrl : \n" + url);
        if (isNotEmpty(url, true) == false) {
            return "";
        }

//		if (! url.endsWith("/") && ! url.endsWith(".html")) {
//			url = url + "/";
//		}

        if (isUrl(url) == false) {
            return URL_PREFIX + url;
        }
        return url;
    }

    /**
     * 获取去掉所有 空格 、"-" 、"+86" 后的phone
     *
     * @param tv
     * @return
     */
    public static String getCorrectPhone(TextView tv) {
        return getCorrectPhone(getString(tv));
    }

    /**
     * 获取去掉所有 空格 、"-" 、"+86" 后的phone
     *
     * @param phone
     * @return
     */
    public static String getCorrectPhone(String phone) {
        if (isNotEmpty(phone, true) == false) {
            return "";
        }

        phone = getNoBlankString(phone);
        phone = phone.replaceAll("-", "");
        if (phone.startsWith("+86")) {
            phone = phone.substring(3);
        }
        return phone;
    }


    /**
     * 获取邮箱，自动补全
     *
     * @param tv
     * @return
     */
    public static String getCorrectEmail(TextView tv) {
        return getCorrectEmail(getString(tv));
    }

    /**
     * 获取邮箱，自动补全
     *
     * @param email
     * @return
     */
    public static String getCorrectEmail(String email) {
        if (isNotEmpty(email, true) == false) {
            return "";
        }

        email = getNoBlankString(email);
        if (isEmail(email) == false && !email.endsWith(".com")) {
            email += ".com";
        }

        return email;
    }


    public static final int PRICE_FORMAT_DEFAULT = 0;
    public static final int PRICE_FORMAT_PREFIX = 1;
    public static final int PRICE_FORMAT_SUFFIX = 2;
    public static final int PRICE_FORMAT_PREFIX_WITH_BLANK = 3;
    public static final int PRICE_FORMAT_SUFFIX_WITH_BLANK = 4;
    public static final String[] PRICE_FORMATS = {
            "", "￥", "元", "￥ ", " 元"
    };

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @return
     */
    public static String getPrice(String price) {
        return getPrice(price, PRICE_FORMAT_DEFAULT);
    }

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @param formatType 添加单位（元）
     * @return
     */
    public static String getPrice(String price, int formatType) {
        if (isNotEmpty(price, true) == false) {
            return getPrice(0, formatType);
        }

        //单独写到getCorrectPrice? <<<<<<<<<<<<<<<<<<<<<<
        String correctPrice = "";
        String s;
        for (int i = 0; i < price.length(); i++) {
            s = price.substring(i, i + 1);
            if (".".equals(s) || isNumer(s)) {
                correctPrice += s;
            }
        }
        //单独写到getCorrectPrice? >>>>>>>>>>>>>>>>>>>>>>

        Log.i(TAG, "getPrice  <<<<<<<<<<<<<<<<<< correctPrice =  " + correctPrice);
        if (correctPrice.contains(".")) {
//			if (correctPrice.startsWith(".")) {
//				correctPrice = 0 + correctPrice;
//			}
            if (correctPrice.endsWith(".")) {
                correctPrice = correctPrice.replaceAll(".", "");
            }
        }

        Log.i(TAG, "getPrice correctPrice =  " + correctPrice + " >>>>>>>>>>>>>>>>");
        return isNotEmpty(correctPrice, true) ? getPrice(new BigDecimal(0 + correctPrice), formatType) : getPrice(0, formatType);
    }

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @return
     */
    public static String getPrice(BigDecimal price) {
        return getPrice(price, PRICE_FORMAT_DEFAULT);
    }

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @return
     */
    public static String getPrice(double price) {
        return getPrice(price, PRICE_FORMAT_DEFAULT);
    }

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @param formatType 添加单位（元）
     * @return
     */
    public static String getPrice(BigDecimal price, int formatType) {
        return getPrice(price == null ? 0 : price.doubleValue(), formatType);
    }

    /**
     * 获取价格，保留两位小数
     *
     * @param price
     * @param formatType 添加单位（元）
     * @return
     */
    public static String getPrice(double price, int formatType) {
        String s = new DecimalFormat("#########0.00").format(price);
        switch (formatType) {
            case PRICE_FORMAT_PREFIX:
                return PRICE_FORMATS[PRICE_FORMAT_PREFIX] + s;
            case PRICE_FORMAT_SUFFIX:
                return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX];
            case PRICE_FORMAT_PREFIX_WITH_BLANK:
                return PRICE_FORMATS[PRICE_FORMAT_PREFIX_WITH_BLANK] + s;
            case PRICE_FORMAT_SUFFIX_WITH_BLANK:
                return s + PRICE_FORMATS[PRICE_FORMAT_SUFFIX_WITH_BLANK];
            default:
                return s;
        }
    }


    //校正（自动补全等）字符串>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 把字符中的数字转化为汉字
     *
     * @param str
     * @return
     */
    public static String integerToString(String str) {
        return str.replace("0", "零").replace("1", "一").replace("2", "二").replace("3", "三").replace("4", "四").replace("5", "五").replace("6", "六").replace("7", "七").replace("8", "八").replace("9", "九");
    }

    static String[] units = {"", "十", "百", "千", "万", "十万", "百万", "千万", "亿",
            "十亿", "百亿", "千亿", "万亿"};
    static char[] numArray = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};

    public static String formatInteger(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if (val.length > 1) {
                    if ('0' == val[i - 1]) {
                        // not need process if the last digital bits is 0
                        continue;
                    } else {
                        // no unit for 0
                        sb.append(numArray[n]);
                    }
                } else {
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }
        return sb.toString().replace("一十零", "十").replace("一十一", "十一").replace("一十二", "十二").replace("一十三", "十三")
                .replace("一十四", "十四").replace("一十五", "十五").replace("一十六", "十六").replace("一十七", "十七").replace("一十八", "十八")
                .replace("一十九", "十九").replace("二十零", "二十").replace("三十零", "三十").replace("四十零", "四十").replace("五十零", "五十")
                .replace("六十零", "六十").replace("七十零", "七十").replace("八十零", "八十").replace("一百零", "一百").replace("二百零", "二百")
                .replace("三百零", "三百").replace("四百零", "四百").replace("五百零", "五百").replace("六百零", "六百").replace("七百零", "七百")
                .replace("八百零", "八百").replace("九百零", "九百").replace("一千零", "一千").replace("二千零", "二千").replace("三千零", "三千")
                .replace("四千零", "四千").replace("五千零", "五千").replace("六千零", "；六千").replace("七千零", "七千").replace("八千零", "八千")
                .replace("九千零", "九千").replace("一万零", "一万");
    }
}
