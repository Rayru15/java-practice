package main.solve;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main01 {

    public static void main(String[] args) {
        int even=0;
        int odd=0;
        Scanner sc = new Scanner(System.in); //scanner로 예제를 입력받음.
        List<String> peopleList= new ArrayList<>(); //사원 저장해놓는 리스트

        //사원 수 입력
        System.out.print("사원 수 입력: ");
        int num = sc.nextInt();
        sc.nextLine();


        for (int i = 1; i <= num; i++) {
            System.out.print("사원 정보를(사원이름/사번/입사일(yyyymmdd)) 형태로 입력하세요: ");
            String people = sc.nextLine();

            peopleList.add(people);

        }
        System.out.println("-------------------------------");

        for(String people:peopleList){
            String[] peopleSplit = people.split("/");

            String pname = peopleSplit[0];
            int pnum = Integer.parseInt(peopleSplit[1]);
            int pdate = Integer.parseInt(peopleSplit[2]);



            if(pdate>=20240701){

                if (pnum % 2 == 0){
                    System.out.println(pname+" / "+pnum+" / "+"even");
                    even++;
                }
                else  {
                    System.out.println(pname+" / "+pnum+" / "+"odd");
                    odd++;
                }

            }


        }
        System.out.println();
        System.out.println("odd : "+odd);
        System.out.println("even : "+even);
        int sum=odd+even;
        System.out.println("sum : "+sum);
    }
}