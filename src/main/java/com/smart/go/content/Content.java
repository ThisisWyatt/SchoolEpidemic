package com.smart.go.content;


public class Content {

    public static final int TYPE_1 = 1;//USER_STACHANGE
    public static final int TYPE_2 = 2;//USER_ROAM
    public static final int TYPE_3 = 3;//USER_STAUPDT
    public static final int TYPE_4 = 4;//USER_ADD
    public static final int TYPE_5 = 5;//USER_DEL


    /**
     * 用户漫游
     */
    public static final String USER_ROAM = "%ROAMING-6-ROAM_EVENT";
    /**
     * 用户ap切换
     */
    public static final String USER_STACHANGE = "%APMG-6-STA_CHANGE";

    /**
     * 用户ADD
     */
    public static final String USER_ADD = "%APMG-6-STA_ADD";
    /**
     * 用户DEL
     */
    public static final String USER_DEL = "%APMG-6-STA_DEL";
    /**
     * 用户更新
     */
    public static final String USER_STAUPDT = "%APMG-6-STA_UPDT";

}
