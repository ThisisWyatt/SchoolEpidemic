package com.smart.go;

import com.smart.go.domain.SingleLog;
import com.smart.go.domain.Teacher;
import com.smart.go.service.BuildMoveInfo;
import com.smart.go.service.ReadAndExactDataService;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.ClasspathResourceSelector;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.annotation.Resource;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class GoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void helloWorld() {
        System.out.println("hello future");
    }

    @Test
    void testRegex() {
        String str = "(a)(b)(c)(d)(e)";
        String regex = "\\(.*\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println("replace: " + str.replaceAll(regex, "O"));
        while (matcher.find()) {
            System.out.println("matcher: " + matcher.group(0));
        }
    }

    @Test
    void TestReadLog() throws IOException {
        FileReader fr = new FileReader("X:\\test.log");
        BufferedReader br = new BufferedReader(fr);
        while (br.readLine() != null) {
            String s = br.readLine();
            System.out.println(s);
            System.out.println("----------");
        }
        br.close();
    }

    @Resource
    private ReadAndExactDataService readAndExactDataService;

    @Test
    void TestReadExtract() throws IOException, ParseException {
        readAndExactDataService.TestReadLog();
    }

    @Test
    void Read() throws IOException {

        InputStream stream = getClass().getClassLoader().getResourceAsStream("test.log");
        assert stream != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        System.out.println(br.readLine());

    }

    @Test
    void testDateChange() throws ParseException {

        Date date=new Date();
        System.out.println("date"+date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//Date指定格式：yyyy-MM-dd HH:mm:ss
        String s=simpleDateFormat.format(date);
        System.out.println(simpleDateFormat.format(date));
        Date d=simpleDateFormat.parse(s);
        System.out.println(d);

    }

    @Test
    void TestTranscoding(){
        String s = "";
        byte[] b = s.getBytes(StandardCharsets.UTF_8);
        String sa = new String(b, StandardCharsets.UTF_8);
        System.out.println(sa);

    }


    @Resource
    private BuildMoveInfo buildMoveInfo;
    @Test
    void TestBuildMoveInfo(){
        long currentTime = System.currentTimeMillis();
        buildMoveInfo.buildMoveInfo();
        System.out.println("All  cost: " + (System.currentTimeMillis() - currentTime) + " ms");
    }

    @Test
    void mergeString(){
        String a="Hello ";
        String b="World";
        String c=a+b;
        System.out.println(c);
    }
}



