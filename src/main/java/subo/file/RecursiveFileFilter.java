package subo.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

/**
 * 递归查找文件过滤器
 *
 * @author YiJie  2017/7/14
 **/
public class RecursiveFileFilter implements FilenameFilter {

    private String regex;

    public RecursiveFileFilter(String regex) {
        this.regex = regex;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入查找文件目录：");
        String dirPath = scanner.nextLine().replace("\\", File.separator);
        System.out.println("请输入查找文件所匹配的的正则表达式：");
        String regex = "^" + scanner.nextLine() + "$";
        File file = new File(dirPath);
        if (!file.isDirectory()) {
            System.out.println("输入路径名不是一个目录！");
            return;
        }
        file.list(new RecursiveFileFilter(regex));
    }

    @Override
    public boolean accept(File dir, String name) {
        File f = new File(dir,name);
        if (f.isDirectory()) {
            f.list(new RecursiveFileFilter(regex));
        } else if (name.matches(regex)) {
            System.out.println(name);
            return true;
        }
        return false;
    }
}
