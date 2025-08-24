package main.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main03 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> map = new HashMap<>();

        // 첫 줄에는 주어질 상하관계의 수 N이 주어집니다.
        int n = Integer.parseInt(br.readLine());

        // 첫 줄은 트리의 루트(사장)임이 보장됩니다.
        String first = br.readLine();
        String[] split1 = first.split("-");
        String root = split1[0];
        String firstChild = split1[1];

        map.computeIfAbsent(root, k -> new ArrayList<>()).add(firstChild);

        // 다음 N 줄에는 상사-부하 관계가 주어집니다.
        for (int i = 1; i < n; i++) {
            String input = br.readLine();
            String[] split2 = input.split("-");
            String parents = split2[0];
            String childs = split2[1];

            map.computeIfAbsent(parents, k -> new ArrayList<>()).add(childs);
        }
        br.close();

        System.out.println(map);


    }
}
