import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class readFile {

         // directory  要搜索的目录
          // extensions 文件扩展名, 如. {"java","xml"}. 如果为 null 则返回所有文件.
          // recursive  如果为true，则递归搜索所有子目录

        public static List<String> readLines(File file) {
            List<String> list = null;
            try {
                list = FileUtils.readLines(file, "UTF-8");

            } catch (IOException e) {
                e.printStackTrace();
            }
                return  list;
           /// return true;
        }
}
