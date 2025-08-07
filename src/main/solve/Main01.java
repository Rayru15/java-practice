package main.solve;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main01 {

    public static void main(String[] args) {
        int even = 0; //홀짝 인원 초기화
        int odd = 0;
        Scanner sc = new Scanner(System.in); //scanner로 예제를 입력받음.
        List<String> peopleList = new ArrayList<>(); //사원 저장해놓는 리스트

        //사원 수 입력
        System.out.print("사원 수 입력: ");
        int num = sc.nextInt();
        sc.nextLine(); //남아있는 줄바꿈 문자를 없애기 위해


        for (int i = 1; i <= num; i++) {
            System.out.print("사원 정보를(사원이름/사번/입사일(yyyymmdd)) 형태로 입력하세요: ");
            String people = sc.nextLine();

            peopleList.add(people); //peopleList에 추가

        }
        System.out.println("-------------------------------");

        for (String people : peopleList) { //peopleList에 있는 모든 요소를 순회
            String[] peopleSplit = people.split("/"); //"/"기준으로 나누어서 각각 저장

            String pname = peopleSplit[0];
            int pnum = Integer.parseInt(peopleSplit[1]); //정수로 변환해서 저장
            int pdate = Integer.parseInt(peopleSplit[2]);


            if (pdate >= 20240701) { //신규입사자 여부

                if (pnum % 2 == 0) { //짝수
                    System.out.println(pname + " / " + pnum + " / " + "even");
                    even++;
                } else { //홀수
                    System.out.println(pname + " / " + pnum + " / " + "odd");
                    odd++;
                }

            }


        }
        System.out.println();
        System.out.println("odd : " + odd);
        System.out.println("even : " + even);
        int sum = odd + even;
        System.out.println("sum : " + sum);
    }
}