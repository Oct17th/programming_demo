package iflytek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 科大讯飞
 * 球赛
 *
 * @author YiJie 2017/9/16.
 */
public class BallGame {
    public static void main(String[] args) {
        ArrayList<String> r = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt()) {
            int n = Integer.parseInt(in.nextLine());
            Team[] teams = new Team[n];
            for (int i = 0; i < n; i++) {
                teams[i] = new Team(in.nextLine());
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    String s = in.nextLine();
                    String[] tem = s.split(" ");
                    s = tem[1];
                    tem = s.split(":");
                    int s1 = Integer.parseInt(tem[0]);
                    int s2 = Integer.parseInt(tem[1]);
                    teams[i].ball += s1;
                    teams[j].ball += s2;
                    if (s1 > s2) {
                        teams[i].score += 3;
                        teams[j].score += 0;
                    } else if (s1 == s2) {
                        teams[i].score += 1;
                        teams[j].score += 1;
                    } else {
                        teams[i].score += 0;
                        teams[j].score += 3;
                    }
                }
            }
            Arrays.sort(teams);
            String[] result = new String[n / 2];
            for (int i = 0, j = teams.length - 1; i < n / 2; i++, j--) {
                result[i] = teams[j].name;
            }
            Arrays.sort(result);
            for (int i = 0; i < result.length; i++) {
                r.add(result[i]);
            }
        }
        for (int i = 0; i < r.size(); i++) {
            System.out.println(r.get(i));
        }

    }

    static class Team implements Comparable<Team> {
        String name;//队名
        int score;//得分
        int ball;//进球数

        public Team(String name) {
            this.name = name;
            this.score = 0;
            this.ball = 0;
        }

        @Override
        public int compareTo(Team o) {
            if (this.score > o.score) return 1;
            if (this.score < o.score) return -1;
            if (this.ball > o.ball) return 1;
            if (this.ball < o.ball) return -1;
            return 0;//剩下的即score与ball都相等
        }
    }

}


