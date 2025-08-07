package main.editorial;

import java.io.*;
import java.util.List;

/**
 * <a href="https://nsm-cloud-git.nsmall.com/jjhoo8936/java-ds-algo-practice/src/branch/main/problem/01.problem.md">문제01</a>풀이
 * <pre>
 * 문제01의 에디토리얼 코드
 * </pre>
 *
 * @author jjhoo8936
 * @since 2025-08-07
 */
public class Main01 {

    /**
     * 사원이 짝수 사원인가?
     *
     * @param employee name/empCd/yyyymmdd
     * @return 짝수사원이면 true
     */
    static boolean isOddEmployee(String employee) {
        /**
         * String.split() 보다 StringTokenizer를 활용하면 성능에 이점이 있지만 활용하기 편한건 split() 같습니다.
         */
        return Integer.parseInt(employee.split("/")[1]) % 2 == 1;
    }

    /**
     * 사원이 신규입사자인가?
     *
     * @param employee name/empCd/yyyymmdd
     * @return 신규입사자면 true
     */
    static boolean isNewEmployee(String employee) {
        return employee.split("/")[2].compareTo("202407") > 0;
    }

    /**
     * 사원 출력 형식
     *
     * @param employee name/empCd/yyyymmdd
     * @return name / empCd / even|odd
     */
    static String employeeFormat(String employee) {
        String[] split = employee.split("/");
        StringBuilder sb = new StringBuilder();
        return sb.append(split[0])            // name
                .append(" / ")
                .append(split[1])             // empCd
                .append(" / ")
                .append(isOddEmployee(employee) ? "odd" : "even")       // odd | even
                .toString();
    }

    public static void main(String[] args) throws IOException {

        /**
         * FAST I/O
         *
         * <pre>
         * 문자열 입출력 크기가 작을 경우에는 Scanner/System.out 으로도 충분하지만,
         * 입출력 양이 많아지게 되면 BufferedReader/Writer와의 성능 차이가 확연합니다.
         * </pre>
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /** 출력도 BufferedReader로 하려고 했으나, 밑의 stream에서 try / catch가 오히려 가독성을 더 해치기 때문에 출력은 sout */
        /** 아니면 StringBuilder에 append하고 그것을 출력해도 좋습니다. */
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        /**
         * 입력 처리
         *
         * <pre>
         * Stream.forEach문은 적응되면 가독성에는 좋으나, 성능에서는 오히려 for loop 보다 떨어집니다.
         * 입력 완료 처리(EOF)는 (IntelliJ 기준) Ctrl + D
         * </pre>
         */
        List<String> employees = br.lines().toList();

        // 홀수 신규입사자 필터링
        List<String> oddNewEmployees = employees.stream()
                .filter(Main01::isNewEmployee)
                .filter(Main01::isOddEmployee)
                .toList();

        // 짝수 신규입사자 필터링
        List<String> evenNewEmployees = employees.stream()
                .filter(Main01::isNewEmployee)
                .filter(s -> !isOddEmployee(s))
                .toList();

        // 신규입사자 출력
        employees
                .stream()
                .filter(Main01::isNewEmployee)
                .map(Main01::employeeFormat)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("odd : " + oddNewEmployees.size());
        System.out.println("even : " + evenNewEmployees.size());
        // 연산자 우선순위 주의!!
        System.out.println("sum : " + (oddNewEmployees.size() + evenNewEmployees.size()));
    }
}
