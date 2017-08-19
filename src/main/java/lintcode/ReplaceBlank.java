package lintcode;

/**
 * 将字符串空格装转换为"%20"
 * @author YiJie 2017/8/18.
 */
public class ReplaceBlank {
    /*
    * @param string: An array of Char
    * @param length: The true length of the string
    * @return: The true length of new string
    */
    public int replaceBlank(char[] string, int length) {
        int count = 0;
        char[] temp = new char[length];
        for (int i = 0; i < length; i++) {
            temp[i] = string[i];
        }
        for (int si = 0, ti = 0; ti < length; ti++) {
            if (temp[ti] == ' ') {
                string[si] = '%';
                string[si + 1] = '2';
                string[si + 2] = '0';
                si = si + 3;
                count++;
            } else {
                string[si] = temp[ti];
                si++;
            }
        }
        return length + count * 2;
    }
}
