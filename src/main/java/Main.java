import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import com.alibaba.fastjson.JSON;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {// 如果输入为空则什么也不执行
            if (args[0].equals("--init") || args[0].equals("-i")) {
                JSONFilesToMap(args[1]);// args[1]是文件夹路径，调用此函数输出 3 个JSON文件这样就不用每次都遍历处理原 JSON 文件，而是通过查找输出的，整理过的 JSON 文件来获得对应的值
                //以下是分别对应 3 个命令的查询判断
            } else if (args[0].equals("--user") || args[0].equals("-u")) {
                String userName = args[1];
                if (args[2].equals("--event") || args[2].equals("-e")) {
                    String event = args[3];
                    event = event.substring(0, 1).toLowerCase() + event.substring(1);// 将事件单词的首字母转化成小写，否则无法查询
                    findInType1(userName, event);
                } else {
                    String repoName = args[3];
                    String event = args[5];
                    event = event.substring(0, 1).toLowerCase() + event.substring(1);
                    findInType3(userName + repoName, event);
                }
            } else {
                String repoName = args[1];
                String event = args[3];
                event = event.substring(0, 1).toLowerCase() + event.substring(1);
                findInType2(repoName, event);
            }
        }
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
        if (answer == null)
            System.out.println(0);
        else
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
        Integer answer = repo.getInteger(event);
        if (answer == null)
            System.out.println(0);
        else
            System.out.println(answer);

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
        Integer answer = userAndRepo.getInteger(event);
        if (answer == null)
            System.out.println(0);
        else
            System.out.println(userAndRepo.getInteger(event));
    }

    public static void JSONFilesToMap(String path) {
        //定义3个 Map 容器，其中 numOfEvent 是自己定义的类，其中的属性值是 4 个事件的值
        Map<String, numOfEvent> type1 = new HashMap<String, numOfEvent>();
        Map<String, numOfEvent> type2 = new HashMap<String, numOfEvent>();
        Map<String, numOfEvent> type3 = new HashMap<String, numOfEvent>();
        List<File> fileList = (List<File>) FileUtils.listFiles(new File(path), new String[]{"json"}, true);//获得文件路径
        fileList.forEach(readFile::readLines);
        //遍历同一个文件夹下所有 JSON 文件
        for (File filePath : fileList) {
            List<String> list = null;
            try {
                list = FileUtils.readLines(filePath, "UTF-8");//将 JSON 文件按行读取并存入 List<String>list 中
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String str : Objects.requireNonNull(list)) {
                // 3 个不同的 JSON 对象分别对应不同的要求
                JSONObject obj = JSON.parseObject(str);
                JSONObject user = obj.getJSONObject("actor");
                JSONObject repo = obj.getJSONObject("repo");

                String event = obj.getString("type");//事件名
                String userName = user.getString("login");
                String repoName = repo.getString("name");
                String userAndRepoName = userName + repoName;//简单的将用户名和事件名拼接在一起当做第三个 Map 的 Key 值，用于唯一确定一个用户在一个项目中的事件
                // 将需要的内容存入 Map
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
        //调用 OutputFile 函数将 3 个 Map 输出成 JSON 文件
        OutputFile(type1, type2, type3);
    }


    public static void OutputFile(Map<String, numOfEvent> t1,
                                  Map<String, numOfEvent> t2,
                                  Map<String, numOfEvent> t3) {

        String s1 = JSONObject.toJSONString(t1);
        String s2 = JSONObject.toJSONString(t2);
        String s3 = JSONObject.toJSONString(t3);

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
            case "PushEvent":
                input.PushEvent++;
                break;
            case "IssueCommentEvent":
                input.IssueCommentEvent++;
                break;
            case "IssuesEvent":
                input.IssuesEvent++;
                break;
            case "PullRequestEvent":
                input.PullRequestEvent++;
                break;
            default:
                break;
        }
    }

}


