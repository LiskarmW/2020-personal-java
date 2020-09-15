import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import com.alibaba.fastjson.JSON;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("--init") || args[0].equals("-i")) {
                JSONFilesToMap(args[1]);
            } else if (args[0].equals("--user") || args[0].equals("-u")) {
                String userName = args[1];
                if (args[2].equals("--event") || args[2].equals("-e")) {
                    String event = args[3];
                    event = event.substring(0,1).toLowerCase() + event.substring(1);
                    findInType1(userName, event);
                } else {
                    String repoName = args[3];
                    String event = args[5];
                    event = event.substring(0,1).toLowerCase() + event.substring(1);
                    findInType3(userName + repoName, event);
                }
            } else {
                String repoName = args[1];
                String event = args[3];
                event = event.substring(0,1).toLowerCase() + event.substring(1);
                findInType2(repoName, event);
            }
        }
//        JSONFilesToMap("C:\\Users\\Administrator\\Desktop\\bianli\\json");
//        String userName = "waleko";
//        String event = "PushEvent";
//        event = event.substring(0,1).toLowerCase() + event.substring(1);
//        System.out.println(event);
//        findInType1(userName,event);
//        findInType2("katzer/cordova-plugin-background-mode", "pushdent");
//        findInType3("cdupuisatomist/automation-client", "pushEvent");
    }

    //个人的 4 种事件的数量
    public static void findInType1(String userName, String event) {
        String str = null;
        try {
            str = FileUtils.readFileToString(new File("map1.json"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(str);
        JSONObject jsonObj = JSONObject.parseObject(str);
        JSONObject user = jsonObj.getJSONObject(userName);
        Integer answer;
        answer = user.getInteger(event);
        System.out.println(answer);

    }

    //（基础）每一个项目的 4 种事件的数量。
    public static void findInType2(String repoName, String event) {
        String str = null;
        try {
            str = FileUtils.readFileToString(new File("map2.json"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = JSONObject.parseObject(str);
        JSONObject repo = jsonObj.getJSONObject(repoName);
        System.out.println(repo.getInteger(event));

    }

    //（提高）每一个人在每一个项目的 4 种事件的数量。
    public static void findInType3(String userAndRepoName, String event) {
        String str = null;
        try {
            str = FileUtils.readFileToString(new File("map3.json"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObj = JSONObject.parseObject(str);
        JSONObject userAndRepo = jsonObj.getJSONObject(userAndRepoName);
        System.out.println(userAndRepo.getInteger(event));
    }

    public static void JSONFilesToMap(String path) {
        Map<String, numOfEvent> type1 = new HashMap<String, numOfEvent>();
        Map<String, numOfEvent> type2 = new HashMap<String, numOfEvent>();
        Map<String, numOfEvent> type3 = new HashMap<String, numOfEvent>();
        List<File> fileList = (List<File>) FileUtils.listFiles(new File(path), new String[]{"json"}, true);

        fileList.forEach(readFile::readLines);
        for (File filePath : fileList) {
            List<String> list = null;
            try {
                list = FileUtils.readLines(filePath, "UTF-8");//集合
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String str : Objects.requireNonNull(list)) {
                JSONObject obj = JSON.parseObject(str);
                JSONObject user = obj.getJSONObject("actor");
                JSONObject repo = obj.getJSONObject("repo");

                String event = obj.getString("type");
                String userName = user.getString("login");
                String repoName = repo.getString("name");
                String userAndRepoName = userName + repoName;

                numOfEvent input1 = new numOfEvent();
                numOfEvent input2 = new numOfEvent();
                numOfEvent input3 = new numOfEvent();


                if (type1.get(userName) != null)
                    input1 = type1.get(userName);
                getDifferentEvents(input1, event);
                type1.put(userName, input1);

                if (type2.get(repoName) != null)
                    input2 = type2.get(repoName);
                getDifferentEvents(input2, event);
                type2.put(repoName, input2);


                if (type3.get(userAndRepoName) != null)
                    input3 = type3.get(userAndRepoName);
                getDifferentEvents(input3, event);
                type3.put(userAndRepoName, input3);
            }
        }

        OutputFile(type1, type2, type3);
    }


    public static void OutputFile(Map<String, numOfEvent> t1,
                                  Map<String, numOfEvent> t2,
                                  Map<String, numOfEvent> t3) {

        String s1 = JSONObject.toJSONString(t1);
        String s2 = JSONObject.toJSONString(t2);
        String s3 = JSONObject.toJSONString(t3);
//        System.out.println(222);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
        try {
            FileUtils.writeStringToFile(new File("map1.json"), s1, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeStringToFile(new File("map2.json"), s2, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeStringToFile(new File("map3.json"), s3, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getDifferentEvents(numOfEvent input, String event) {
        switch (event) {
            case "PushEvent" -> input.PushEvent++;
            case "IssueCommentEvent" -> input.IssueCommentEvent++;
            case "IssuesEvent" -> input.IssuesEvent++;
            case "PullRequestEvent" -> input.PullRequestEvent++;
        }
    }

}


