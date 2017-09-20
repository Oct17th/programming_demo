package u51;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 51信用卡
 *
 * @author YiJie 2017/9/18.
 */
public class CountScore {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, Person> map = new HashMap<>();
        String[] i = in.nextLine().split(" ");
        String[] k = in.nextLine().split(" ");
        for (int j = 0; j < i.length; j++) {
            String key = i[j];
            if (map.containsKey(i[j])) {
                Person p = map.get(i[j]);
                p.count++;
                p.score += Integer.parseInt(k[j]);
                map.put(key, p);
            } else {
                map.put(key, new Person(key, 1, Integer.parseInt(k[j])));
            }
        }
        Person max = new Person("", -1, -1);
        for (Person p : map.values()) {
            if (p.compareTo(max) > 0) max = p;
        }
//        Arrays.sort(arrayList);
        System.out.println(max.id);
    }

    static class Person implements Comparable<Person> {
        String id;
        int count;
        int score;

        public Person(String id, int count, int score) {
            this.id = id;
            this.count = count;
            this.score = score;
        }

        @Override
        public int compareTo(Person o) {
            if (score > o.score) return 1;
            if (score < o.score) return -1;
            if (count > o.count) return 1;
            if (count < o.count) return -1;
            return 0;
        }
    }
}
