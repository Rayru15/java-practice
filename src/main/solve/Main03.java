package main.solve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Stack;

import static javax.swing.text.html.HTML.Attribute.N;


/**
 * 문제 3 / 조직의 계급도
 *
 * @author minju
 * @since 2025-08-24
 */
public class Main03 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> map = new HashMap<>();
        String root = null;

        // 첫 줄에는 주어질 상하관계의 수 N이 주어집니다.
        int n = Integer.parseInt(br.readLine());

        // 다음 N 줄에는 상사-부하 관계가 주어집니다.
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input, "-");
            String parent = st.nextToken();
            String child = st.nextToken();

            if (i == 0) root = parent;

            map.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
        }

        br.close();

        Stack<String> nodeStack = new Stack<>(); // 노드
        Stack<Integer> depthStack = new Stack<>(); // 깊이

        nodeStack.push(root); // 조사장
        depthStack.push(0); // 깊이 0

        while (!nodeStack.isEmpty()) { // 노드스택이 비어있지 않을 동안
            String node = nodeStack.pop(); // 노드 꺼냄
            int depth = depthStack.pop(); // 깊이 꺼냄

            System.out.println("--".repeat(depth) + node);

            // 입력 순서대로 출력되도록 역순 push (stack은 LIFO)
            List<String> children = map.getOrDefault(node, Collections.emptyList());
            for (int i = children.size() - 1; i >= 0; i--) {
                nodeStack.push(children.get(i));
                depthStack.push(depth + 1);
            }

        }
    }

}
