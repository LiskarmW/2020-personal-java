# (一) 命名规约

1. 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。
2. 代码中的命名统一采取英文形式，不能用拼音，但尽量要简洁易懂。
3. 类名使用 UpperCamelCase 风格，即大驼峰形式
4. 方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格，即小驼峰形式。
5. 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
6. 中括号是数组类型的一部分，数组定义如下：String[]   args

# (二) 格式规约

1. 大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：

   * 左大括号前不换行。
   * 左大括号后换行。
   * 右大括号前换行。
   * 右大括号后还有else等代码则不换行；表示终止右大括号后必须换行。

2. 左括号和后一个字符之间不出现空格；同样，右括号和前一个字符之间也不出现空格。

3. 任何运算符左右必须加一个空格

4. 缩进采用4个空格，禁止使用tab字符。

5. if / for / while / switch / do 等保留字与左右括号之间都必须加空格。

6. 如参数有相同类型，或者变量命名相似时尽量放在一起且换行，如：

   ```java
   				JSONObject obj = JSON.parseObject(str);
                   JSONObject user = obj.getJSONObject("actor");
                   JSONObject repo = obj.getJSONObject("repo");
   
                   String event = obj.getString("type");//事件名
                   String userName = user.getString("login");
                   String repoName = repo.getString("name");
                   String userAndRepoName = userName + repoName;
   ```

   ```java
   public static void OutputFile(Map<String, numOfEvent> t1,
                                 Map<String, numOfEvent> t2,
                                 Map<String, numOfEvent> t3)
   ```

7. 单行字符数限制不超过  120个，超出需要换行，换行时遵循如下原则：

   * 第二行相对第一行缩进   4个空格，从第三行开始，不再继续缩进。
   * 运算符与下文一起换行。
   * 方法调用的点符号与下文一起换行。

8. 函数的最大行数为 80 行。

9. 空行规则：

   - 函数与函数之间可空行
   - 不同类型的变量定义之间可空行
   - 导入包语句之间可空行
   - 相似语句之间可空行

# (三) 注释规约

1. 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/* */注释，注意与代码对齐。
2. 与其“半吊子”英文来注释，不如用中文注释把问题说清楚。专有名词与关键字保持英文原文即可。
3. 代码修改的同时，注释也要进行相应的修改。
4. 好的代码结构是自解释的，避免写过多无用的注释。