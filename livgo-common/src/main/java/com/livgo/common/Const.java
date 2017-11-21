package com.livgo.common;

/**
 * Description:
 * Author:     Livgo
 * Date:       2017/3/31
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class Const {
    public static final String ENCODE = "UTF-8";
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String BUT = "but";

    public static final int SECOND_DAY = 86400;//一天的秒数
    public static final int SECOND_HOUR = 3600;//一个小时的秒数
    public static final int SESSION_KEEP_LOGIN = 20 * 60;//默认保持20分钟

    public static final int PAGE_SIZE = 10;//分页大小
    public static final int PAGE_SIZE_MIN = 5;//分页大小
    public static final int REQUEST_TIMES_TIME = 1; //控制请求次数的时间（秒）
    public static final int REQUEST_TIMES_SUSPICIOUS = 10; //控制请求可疑次数
    public static final int SECOND_WEEK = 7*24*60*60; //一周的秒数

}
