package subo.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 手动实现单层文件查找过滤器
 *
 * @author YiJie  2017/7/14
 **/
public class MyFileFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入查找文件目录：");
        String directoryPath = scanner.nextLine().replace("\\", File.separator);
        System.out.println("请输入查找文件所匹配的的正则表达式：");
        String regex = "^"+scanner.nextLine()+"$";
        File file = new File(directoryPath);
        if(!file.isDirectory()){
            System.out.println("输入路径名不是一个目录！");
            return;
        }
        for (String name:file.list()){
            if(name.matches(regex))
                System.out.println(name);
        }

    }

}
