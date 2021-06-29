package com.smart.go.util;

import com.smart.go.content.Content;
import com.smart.go.domain.SingleLog;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description 提取日志信息的工具类
 * Author wyatt
 * Date 2020/4/22 9:32
 **/
@Component
public class ExtractData {

    /**
     * 根据每一条日志中所包含的特殊标记指定对应的方法对其进行处理
     *
     * @param messages 日志数据
     * @return 返回含有相应数据的信息对象
     * @throws ParseException 解析错误异常处理
     */
    public SingleLog dispose(String messages) throws ParseException {
        SingleLog singleLog = null;
        if (messages.contains(Content.USER_ADD)) {
            singleLog = disposeAdd(messages);
        } else if (messages.contains(Content.USER_DEL)) {
            singleLog = disposeDelete(messages);
        } else if (messages.contains(Content.USER_STACHANGE)) {
            singleLog = disposeChange(messages);
        } else if (messages.contains(Content.USER_ROAM)) {
            singleLog = disposeRemove(messages);
        }
        return singleLog;
    }

    /**
     * AP信息变化方式为被用户连接上
     *
     * @param message 日志数据
     * @return 返回含有相应数据的信息对象
     * @throws ParseException 解析错误异常处理
     */
    private static SingleLog disposeAdd(String message) throws ParseException {
        //正则表示式 后面用来提取mac地址和apName
        String reg = "Client\\(([a-z0-9.]{1,})\\) notify: attach to AP \\((.{1,})\\).";

        message = message.replace("  ", " ");
        String[] messageInfo = message.split(Content.USER_ADD + ":");
        String[] dateInfo0 = messageInfo[0].split(" ");

        //获取时间
        Calendar now = Calendar.getInstance();
        String recordStr = now.get(Calendar.YEAR) + "-" + dateInfo0[0].trim() + "-" + dateInfo0[1].trim() + " " + dateInfo0[2].trim();
        DateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
        Date recordDate = format1.parse(recordStr);

        //获取其他数据
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(messageInfo[1]);

        //初始化一条日志对象
        SingleLog singleLog = null;

        if (mat.find() && mat.groupCount() == 2) {
            String userMac = mat.group(1).replace(".", "").toUpperCase();
            String apName = mat.group(2);

            singleLog = new SingleLog(recordDate, userMac, apName, null, null, 3);
        }
        return singleLog;
    }

    /**
     * AP信息变化方式为被用户断开
     *
     * @param messages 日志数据
     * @return 返回含有相应数据的信息对象
     * @throws ParseException 解析错误异常处理
     */
    private static SingleLog disposeDelete(String messages) throws ParseException {
        messages = messages.replace("  ", " ");
        String reg = "Client\\(([a-z0-9.]{1,})\\) notify: leave AP \\((.{1,})\\).";
        String[] messageInfo = messages.split(Content.USER_DEL + ": ");
        String[] dateInfo0 = messageInfo[0].split(" ");


        Calendar now = Calendar.getInstance();
        String recordStr = now.get(Calendar.YEAR) + "-" + dateInfo0[0] + "-" + dateInfo0[1].trim() + " " + dateInfo0[2].trim();
        DateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
        Date recordDate = format1.parse(recordStr);

        long recordTime = recordDate.getTime();
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(messageInfo[1]);

        SingleLog singleLog = null;
        if (mat.find() && mat.groupCount() == 2) {
            String userMac = mat.group(1).replace(".", "").toUpperCase();
            String apName = mat.group(2);

            singleLog = new SingleLog(recordDate, userMac, apName, null, null, 4);
        }
        return singleLog;
    }

    /**
     * AP信息变化方式为 用户切换到另外一个AP上
     *
     * @param messages 日志数据
     * @return 返回含有相应数据的信息对象
     * @throws ParseException 解析错误异常处理
     */
    private static SingleLog disposeChange(String messages) throws ParseException {
        messages = messages.replace("  ", " ");
        String reg = "Client\\(([a-z0-9.]{1,})\\) move from AP\\((.{1,})\\) radio [0-9]{1,} to AP\\((.{1,})\\) radio [0-9]{1,}.";
        String[] messageInfo = messages.split(Content.USER_STACHANGE + ": ");
        String[] dateInfo0 = messageInfo[0].split(" ");


        Calendar now = Calendar.getInstance();
        String recordStr = now.get(Calendar.YEAR) + "-" + dateInfo0[0] + "-" + dateInfo0[1].trim() + " " + dateInfo0[2].trim();
        DateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
        Date recordDate = format1.parse(recordStr);

        long recordTime = recordDate.getTime();
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(messageInfo[1]);

        SingleLog singleLog = null;
        if (mat.find() && mat.groupCount() == 3) {
            String userMac = mat.group(1).replace(".", "").toUpperCase();
            String apNameFrom = mat.group(2);
            String apNameTo = mat.group(3);
            singleLog = new SingleLog(recordDate, userMac, null, apNameFrom, apNameTo, 1);
        }
        return singleLog;
    }

    /**
     * AP信息变化方式为 用户漫游
     *
     * @param messages 日志数据
     * @return 返回含有相应数据的信息对象
     * @throws ParseException 解析错误异常处理
     */
    private static SingleLog disposeRemove(String messages) throws ParseException {
        messages = messages.replace("  ", " ");
        String reg = "Client\\(([a-z0-9.]{1,})\\) notify \\(Intra-AC Roaming\\):Roaming from AP\\((.{1,})\\) to AP\\((.{1,})\\).";
        String[] messageInfo = messages.split(Content.USER_ROAM + ": ");
        String[] dateInfo0 = messageInfo[0].split(" ");


        Calendar now = Calendar.getInstance();
        String recordStr = now.get(Calendar.YEAR) + "-" + dateInfo0[0] + "-" + dateInfo0[1].trim() + " " + dateInfo0[2].trim();
        DateFormat format1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss", Locale.ENGLISH);
        Date recordDate = format1.parse(recordStr);
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(messageInfo[1]);

        SingleLog singleLog = null;
        if (mat.find() && mat.groupCount() == 3) {

            String userMac = mat.group(1).replace(".", "").toUpperCase();
            String apNameFrom = mat.group(2);
            String apNameTo = mat.group(3);
            singleLog = new SingleLog(recordDate, userMac, null, apNameFrom, apNameTo, 2);
        }
        return singleLog;
    }

}
