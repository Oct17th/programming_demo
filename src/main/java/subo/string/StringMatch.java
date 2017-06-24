
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 实训06.01作业：模式匹配（查找子串、替换串）
 * <p>
 * 1.查找子串
 * 输入：
 * 父串：
 * 子串：
 * 输出：
 * 子串在父串首次出现的位置为：
 * 子串在父串出现的所有位置为：
 * <p>
 * 2.替换串
 * 输入：
 * 替换前的字符串：
 * 被替换串：
 * 替换串：
 * 输出：
 * 替换后的字符串输出：
 * <p>
 * 要求【不能使用String类API文档的方法，如：replaceOf、indexOf ,可以使用substring方法】
 *
 * @author YiJie  2017/6/1
 **/
public class StringMatch {

    /**
     * 查找子串在父串出现的所有位置
     *
     * @param parentString 父串
     * @param subString    子串
     * @return 出现的所有位置（设定目标索引从1开始）
     */
    public ArrayList<Integer> indexSub(String parentString, String subString) {
        return indexSub0(parentString, subString, false);
    }

    /**
     * 查找子串在父串出现的所有位置
     *
     * @param parentString    父串
     * @param subString       子串
     * @param isStartWithZero 索引是否从O开始
     * @return
     */
    private ArrayList<Integer> indexSub0(String parentString, String subString, boolean isStartWithZero) {
        ArrayList<Integer> indexs = new ArrayList<Integer>();
        int parentSize = parentString.length();
        int subSize = subString.length();
        int startIndex = isStartWithZero ? 0 : 1;//起始下标
        for (int i = 0; i < parentSize - subSize + 1; i++) {//从父串第一个字符起逐一与子串循环匹配
            for (int j = 0; j < subSize; j++) {
                if (parentString.charAt(i + j) != subString.charAt(j)) {//匹配失败则从此次匹配起始位置开始再次寻找
                    break;
                }
                if (j == subSize - 1) {
                    indexs.add(i + startIndex);
                    i += subSize - 1;//匹配成功则从此次匹配串后开始再次寻找
                }
            }
        }
        return indexs;
    }

    /**
     * 替换字符串
     *
     * @param aimString        目标串
     * @param beReplacedString 被替换子串
     * @param replaceString    替换子串
     * @return
     */
    public String replaceSub(String aimString, String beReplacedString, String replaceString) {
        ArrayList<Integer> indexs = indexSub0(aimString, beReplacedString, true);//查找原字符中存在的替换子串位置
        if (indexs.size() == 0) {
            return null;
        }
        //new一个StringBuffer方便后续替换修改字符串，赋初值为原字符串起始位置到第一个替换子串结束
        StringBuffer stringBuffer = new StringBuffer(aimString.substring(0, indexs.get(0))).append(replaceString);
        int replaceSize = beReplacedString.length();
        int lastIndex = indexs.get(0);
        for (int i = 1; i < indexs.size(); i++) {
            stringBuffer.append(aimString.substring(lastIndex + replaceSize, indexs.get(i)));//追加从上一次替换结束到此次替换起始间的字符
            stringBuffer.append(replaceString);//追加替换子串
            lastIndex = indexs.get(i);//标记这一个替换的起始索引
        }
        stringBuffer.append(aimString.substring(lastIndex + replaceSize));
        return new String(stringBuffer);
    }
}

/**
 * 测试类
 */
class Test {
    private static StringMatch stringMatch = new StringMatch();

    /**
     * 查找子串测试
     */
    public void indexTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("父串：");
        String parentString = scanner.nextLine();
        System.out.print("子串：");
        String subString = scanner.nextLine();
        ArrayList<Integer> indexs = stringMatch.indexSub(parentString, subString);
        if (indexs.size() == 0) {
            System.out.println("该子串没有在父串出现过！");
        } else {
            System.out.println("子串在父串首次出现的位置为：" + indexs.get(0));
            System.out.println("子串在父串出现的所有位置为：" + indexs);
        }
    }

    /**
     * 替换串测试
     */
    public void replaceTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("替换前的字符串：");
        String aimString = scanner.nextLine();
        System.out.print("被替换串：");
        String beRepalcedString = scanner.nextLine();
        System.out.print("替换串：");
        String replaceString = scanner.nextLine();
        String resultString = stringMatch.replaceSub(aimString, beRepalcedString, replaceString);
        if (resultString == null) {
            System.out.println("没有找到该被替换子串！");
        } else {
            System.out.println("替换后的字符串输出：" + resultString);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------进入程序--------------");
        while (true) {
            System.out.println("--------------选择方法--------------");
            System.out.print("index:查找子串\nreplace:替换串\nexit:退出程序\n输入：");
            String in = scanner.nextLine();
            switch (in) {
                case "index":
                    System.out.println("------------进入index方法-----------");
                    test.indexTest();
                    System.out.println("------------退出index方法-----------");
                    break;
                case "replace":
                    System.out.println("-----------进入replace方法----------");
                    test.replaceTest();
                    System.out.println("-----------退出replace方法----------");
                    break;
                case "exit":
                    System.out.println("--------------退出程序--------------");
                    System.exit(0);
                default:
                    System.out.println("输入错误!");
                    break;
            }
        }
    }
}
