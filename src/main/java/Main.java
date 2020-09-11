import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import com.alibaba.fastjson.JSON;

import java.io.*;

public class Main {
    public static void main(String[] args) {
//        String str = null;
//        try {
//            str = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\2020-personal-java\\src\\main\\java\\test.json"), "utf-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //读JSON到List<String>
        List<String> list = null;
        try {
            list = FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\2020-personal-java\\src\\main\\java\\test.json"), "UTF-8");//集合
            //list = FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\2020-personal-java\\2015-01-01-15.json\\test.json"), "UTF-8");//集合

        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, numOfEvent> ans1 = new HashMap<String, numOfEvent>();

        for (var str : Objects.requireNonNull(list)) {
            JSONObject obj = JSON.parseObject(str);
            String event = obj.getString("type");
            JSONObject person = obj.getJSONObject("actor");
            String personId = person.getString("id");
            numOfEvent input = new numOfEvent();
            if (ans1.get(personId) != null) {
                input = ans1.get(personId);
            }
            switch (event) {
                case "PushEvent" -> input.PushEvent++;
                case "IssueCommentEvent" -> input.IssueCommentEvent++;
                case "IssuesEvent" -> input.IssuesEvent++;
                case "PullRequestEvent" -> input.PullRequestEvent++;
            }
            ans1.put(personId, input);
        }
        Iterator iter = ans1.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.printf("%-9s",key);
            System.out.println(val);
        }
        //System.out.println(str);

    }

}

