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
            String event = obj.getString("type");
            JSONObject person = obj.getJSONObject("actor");
            String personId = person.getString("id");
            numOfEvent input = new numOfEvent();
            if (ans1.get(personId) != null) {
                input = ans1.get(personId);
            }
            if (event.equals("PushEvent"))
                input.PushEvent++;
            else if (event.equals("IssueCommentEvent"))
                input.IssueCommentEvent++;
            else if (event.equals("IssuesEvent"))
                input.IssuesEvent++;
            else
                input.PullRequestEvent++;

            ans1.put(personId, input);
        }
        Iterator iter = ans1.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
//             System.out.printf("%-12s", key);
//             System.out.println(val);
        }
        //System.out.println(str);


        for (var str : Objects.requireNonNull(list)) {
            JSONObject obj = JSON.parseObject(str);
            String event = obj.getString("type");
            JSONObject repo = obj.getJSONObject("repo");
            String repoId = repo.getString("id");
            numOfEvent input = new numOfEvent();
            //System.out.println("repoId:"+repoId);
            if (ans2.get(repoId) != null) {
                input = ans2.get(repoId);
            }
            if (event.equals("PushEvent"))
                input.PushEvent++;
            else if (event.equals("IssueCommentEvent"))
                input.IssueCommentEvent++;
            else if (event.equals("IssuesEvent"))
                input.IssuesEvent++;
            else
                input.PullRequestEvent++;

            ans2.put(repoId, input);
        }

        Iterator iter2 = ans2.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry entry = (Map.Entry) iter2.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            // System.out.printf("%-12s", key);
            // System.out.println(val);
        }

        for (var str : Objects.requireNonNull(list)) {
            JSONObject obj = JSON.parseObject(str);
            String event = obj.getString("type");
            JSONObject person = obj.getJSONObject("actor");
            String personId = person.getString("id");
            JSONObject repo = obj.getJSONObject("repo");
            String repoId = repo.getString("id");
            personIdAndRepoId personInRepoId = new personIdAndRepoId();
            numOfEvent input = new numOfEvent();
            personInRepoId.personId = personId;
            personInRepoId.repoId = repoId;
            if (ans3.get(personInRepoId) != null) {
                input = ans3.get(personInRepoId);
            }
            if (event.equals("PushEvent"))
                input.PushEvent++;
            else if (event.equals("IssueCommentEvent"))
                input.IssueCommentEvent++;
            else if (event.equals("IssuesEvent"))
                input.IssuesEvent++;
            else
                input.PullRequestEvent++;
            ans3.put(personInRepoId, input);
        }

        Iterator iter3 = ans3.entrySet().iterator();
        while (iter3.hasNext()) {
            Map.Entry entry = (Map.Entry) iter3.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.printf("%-48s", key);
            System.out.println(val);
        }
    }

}

