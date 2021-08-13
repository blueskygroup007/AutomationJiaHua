package com.bluesky.automationjiahua.base;

/**
 * @author BlueSky
 * @date 2021/7/3
 * Description:全局常量
 */
public class AppConstant {
    //sharedpreference常量
    public static final String SP_NAME = "device_sp";
    //public static final String[] DOMAIN = App.getContext().getResources().getStringArray(R.array.spinner_query_domain_database);
    //public static final String[] COLUMN = App.getContext().getResources().getStringArray(R.array.spinner_query_column_database);

    public static final String[] DOMAIN = new String[]{"", "chuleng", "dianbujiaoyou", "gufengji", "jiaoyouanshui", "liuan", "tuoliu", "youku", "zhengan", "zhonglengxiben"};
    public static final String[] COLUMN = new String[]{"tag", "affect", "name", "standard", "type"};


}
