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
        //读JSON到List<String>
        List<String> list = null;
        try {
            //list = FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\2020-personal-java\\src\\main\\java\\test.json"), "UTF-8");//集合
            list = FileUtils.readLines(new File("C:\\Users\\Administrator\\Desktop\\2020-personal-java\\2015-01-01-15.json\\test.json"), "UTF-8");//集合

        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, numOfEvent> ans1 = new HashMap<String, numOfEvent>();
        Map<String, numOfEvent> ans2 = new HashMap<String, numOfEvent>();
        Map<personIdAndRepoId, numOfEvent> ans3 = new HashMap<personIdAndRepoId, numOfEvent>();

        for (var str : Objects.requireNonNull(list)) {
            JSONObject obj = JSON.parseObject(str);
            JSONObject person = obj.getJSONObject("actor");
            JSONObject repo = obj.getJSONObject("repo");

            String event = obj.getString("type");
            String personId = person.getString("id");
            String repoId = repo.getString("id");

            numOfEvent input1 = new numOfEvent();
            numOfEvent input2 = new numOfEvent();
            numOfEvent input3 = new numOfEvent();

            personIdAndRepoId personInRepoId = new personIdAndRepoId();
            personInRepoId.personId = personId;
            personInRepoId.repoId = repoId;

            if (ans1.get(personId) != null)
                input1 = ans1.get(personId);
            getDifferentEvents(input1, event);
            ans1.put(personId, input1);

            if (ans2.get(repoId) != null)
                input2 = ans2.get(repoId);
            getDifferentEvents(input2, event);
            ans2.put(repoId, input2);

            if (ans3.get(personInRepoId) != null)
                input3 = ans3.get(personInRepoId);
            getDifferentEvents(input3, event);
            ans3.put(personInRepoId, input3);
        }

        System.out.println("case1:");
        printAnswer(ans1, ans2, ans3, 1);
        System.out.println("case2:");
        printAnswer(ans1, ans2, ans3, 2);
        System.out.println("case3:");
        printAnswer(ans1, ans2, ans3, 3);

    }


    public static void printAnswer(Map<String, numOfEvent> tmp1,
                                   Map<String, numOfEvent> tmp2,
                                   Map<personIdAndRepoId, numOfEvent> tmp3,
                                   int flag) {
        int i = 0;
        Iterator iter = tmp1.entrySet().iterator();
        if (flag == 2)
            iter = tmp2.entrySet().iterator();
        else if (flag == 3)
            iter = tmp3.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.printf("%-12s", key);
            System.out.println(val);
        }

    }

    public static numOfEvent getDifferentEvents(numOfEvent input, String event) {
        if (event.equals("PushEvent"))
            input.PushEvent++;
        else if (event.equals("IssueCommentEvent"))
            input.IssueCommentEvent++;
        else if (event.equals("IssuesEvent"))
            input.IssuesEvent++;
        else
            input.PullRequestEvent++;
        return input;
    }
}


