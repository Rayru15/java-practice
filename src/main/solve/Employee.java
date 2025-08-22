package main.solve;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Employee {
    // ----- 프로퍼티 -----
    private String employeeNum; // 사번 (앞에 0붙을수 있으니 string으로?)
    private String name;  // 이름
    private LocalDateTime born; // 생년월일
    private LocalDateTime joinDate; // 입사일
    private String teamName; // 팀명
    private String rank;  // 직급

    // 생성자 constructor
    public Employee(String employeeNum, String name, LocalDateTime born, LocalDateTime joinDate, String teamName, String rank) {
        /* 사번 유효성 검사 (숫자 5자리)
         *  사번이 null, 5자리 숫자가 아닐 시 예외처리 (정규식 사용. */
        if (employeeNum == null || employeeNum.length() != 5 || !employeeNum.matches("\\d{5}")) {
            throw new IllegalArgumentException("사번은 5자리 숫자로 이루어져야 함.");
        }
        this.employeeNum = employeeNum;
        /* 이름, 생년월일, 입사일, 팀명, 직급이 null 이 아니면 object 그대로 반환, null이면 메세지 던짐*/
        this.name = Objects.requireNonNull(name, "이름은 null일 수 없음.");
        this.born = Objects.requireNonNull(born, "생년월일은 null일 수 없음.");
        this.joinDate = Objects.requireNonNull(joinDate, "입사일은 null일 수 없음.");
        this.teamName = Objects.requireNonNull(teamName, "팀명은 null일 수 없음.");
        this.rank = Objects.requireNonNull(rank, "직급은 null일 수 없음.");
    }

    // 사번
    public String getEmployeeNum() {
        return employeeNum;
    }

    // 이름
    public String getName() {
        return name;
    }

    // 생년월일
    public LocalDateTime getBorn() {
        return born;
    }

    // 입사일
    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    // 팀명
    public String getTeamName() {
        return teamName;
    }

    // 직급
    public String getRank() {
        return rank;
    }

    // ----- 메서드 -----

    /* 특정 날짜 기준 나이
     *  date : 기준날짜, return : 나이(int)*/
    public int getAge(LocalDate date) {
        // Period : LocalDate 객체 간 계산할 때 사용. (뒤 - 앞)
        return (int) Period.between(born.toLocalDate(), date).getYears();
    }

    // 오늘 기준 나이 계산
    public int getAge() {
        return getAge(LocalDate.now());
    }


    /* 특정 날짜가 생일인지 확인
     * date : 특정날짜, return : 생일 (ture) 아니면 (false)*/
    public boolean isBirthday(LocalDate date) {
        // Monthday로 월,일 만 빼오고, 같은지 비교
        return MonthDay.from(born).equals(MonthDay.from(date));
    }

    // 오늘이 생일인지 확인
    public boolean isBirthday() {
        return isBirthday(LocalDate.now());
    }


    // 입사한지 몇 일째
    public int getDays() {
        /* ChronoUnit은 시간 단위를 나타내는 열거형. (년,월,일,시간,분,초 등)
         *  Days는 일 단위로 계산. */
        return (int) ChronoUnit.DAYS.between(joinDate.toLocalDate(), LocalDate.now());
    }


}