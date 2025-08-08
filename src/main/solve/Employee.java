package main.solve;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Employee {
    // 사번
    private String employeeNum;
    // 이름
    private String name;
    // 생년월일
    private LocalDateTime born;
    // 입사일
    private LocalDateTime joinDate;
    // 팀명
    private String teamName;
    // 직급
    private String rank;


    // 특정 날짜 기준 나이
    public int getAge(LocalDate date) {
        LocalDate birth = born.toLocalDate(); // 시간은 필요없으니 LocalDate로 변환
        Period period = Period.between(birth, date); // 두 날짜 차이 계산
        return (int) period.getYears(); // "년" 만 꺼냄 (int)
    }

    // 오늘 기준 나이 계산
    public int getAge() {
        return getAge(LocalDate.now());
    }

    // 특정 날짜가 생일인지 확인
    public boolean isBirthday(LocalDate date) {
        MonthDay monthDay = MonthDay.from(date); // 현재 월,일
        MonthDay birthday = MonthDay.from(born.toLocalDate()); // 생일의 월,일
        return monthDay.equals(birthday); // 생일 월,일과 현재 월,일이 같으면
    }

    // 오늘이 생일인지 확인
    public boolean isBirthday() {
        return isBirthday(LocalDate.now());
    }

    // 입사한지 몇 일째
    public int getDays() {
        return (int) ChronoUnit.DAYS.between(joinDate.toLocalDate(), LocalDate.now());
    }


}