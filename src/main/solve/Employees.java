package main.solve;

import java.text.Collator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Employees {

    // 사원 저장 리스트
    private List<Employee> store = new ArrayList<>();

    /* 직급 : "사원" < "대리" < "과장" < "차장" < "부장" 순으로 정렬해야함
     *  모든 객체가 같은 서열표 기준으로 정렬되서, final 고정된 규칙으로 선언함
     *  추가로 static 선언으로 클래스 생성시 한번만 생성되게 해서 메모리 절약 */
    private static final Map<String, Integer> RANK_ORDER =
            Map.of(
                    "사원", 10,
                    "대리", 20,
                    "과장", 30,
                    "차장", 40,
                    "부장", 50
            );

    // 생성자
    public Employees() {
    }

    // 사번 오름차순으로 정렬
    public List<Employee> sortedByEmployeeNumAsc() {
        // 원본 리스트 변경하지 않기 위해 stream 사용
        return store.stream()
                .sorted(Comparator.comparing(Employee::getEmployeeNum))
                .collect(Collectors.toList());  // 정렬 후 리스트로 다시 만들어서 반환
    }

    // 나이 오름차순으로 정렬 (오늘기준_ ( 나이 어린 -> 나이 많은)
    public List<Employee> sortedByAgeAsc() {
        return sortedByAgeAsc(LocalDate.now());
    }

    // 나이 오름차순으로 정렬 (특정날짜기준_ ( 나이 어린 -> 나이 많은)
    public List<Employee> sortedByAgeAsc(LocalDate targetDate) {
        Objects.requireNonNull(targetDate, "특정일은 null이 안됩니다."); //null 예외처리
        // 원본 리스트 변경하지 않기 위해 stream 사용
        return store.stream()
                // Employee e (stream에서 하나씩 꺼낸 Employee객체)를 받아서 getAge(targetDate) 반환,
                // comparator 는 객체를 비교하는 규칙을 정의하는 인터페이스
                .sorted(Comparator.comparingInt(e -> e.getAge(targetDate)))
                .collect(Collectors.toList());
    }

    // 직급 내림차순 (직급이 같다면, 이름으로 오름차순)
    public List<Employee> sortedByRankDesc() {
        return store.stream()
                .sorted(Comparator
                        // Employee 객체에서 직급을 꺼내서 숫자로 변환. (RANK_ORDER MAP에서 숫자 가져오기), 직급 없다면 기본값 0
                        .comparingInt((Employee e) -> RANK_ORDER.getOrDefault(e.getRank(), 0))
                        .reversed() // comparator는 기본이 오름차순이기 때문에, reverse 해주기
                        // 직급이 같다면 이름 오름차순으로 정렬해주기. KOREAN은 collator 기반 한글 정렬 comparator
                        .thenComparing(Employee::getName,
                                Comparator.nullsLast(Collator.getInstance(Locale.KOREAN))
                        ))
                .collect(Collectors.toList());
    }

    // 이름 오름차순 정렬
    public List<Employee> sortedByNameAsc() {
        return store.stream()
                .sorted(Comparator.comparing(
                        Employee::getName,
                        Comparator.nullsLast(Collator.getInstance(Locale.KOREAN))
                ))
                .collect(Collectors.toList());
    }

    // 특정 날짜에 생일인 사원 반환
    public List<Employee> findBirthdays(LocalDate date) {
        Objects.requireNonNull(date, "date는 null 안됨");
        return store.stream()
                // 생일 같은 사원만 통과
                .filter(e -> e.isBirthday(date))
                .collect(Collectors.toList());
    }

    // 입사일이 fromDttm ~ toDttm 사이의 사원들 반환
    public List<Employee> findByJoinDateBetween(LocalDateTime fromDttm, LocalDateTime toDttm) {
        Objects.requireNonNull(fromDttm, "null X"); // fromDttm, toDttm null 안되게끔 예외처리
        Objects.requireNonNull(toDttm, "null X");
        return store.stream()
                //
                .filter(e -> {
                    LocalDateTime j = e.getJoinDate();
                    // 입사일이 null 인 사원 제외, fromDttm <= 입사일 <= toDttm
                    return j != null && !j.isBefore(fromDttm) && !j.isAfter(toDttm);
                })
                .collect(Collectors.toList());
    }

    // 사원 추가, add()메서드가 boolean으로 반환함.
    public boolean add(Employee e) {
        if (store.stream().anyMatch(emp -> emp.getEmployeeNum().equals(e.getEmployeeNum()))) {
            return false; // 중복이면 추가 안 함
        }
        return store.add(e);
    }

    // 사번으로 사원 제거
    public boolean removeByEmployeeNum(String employeeNum) {
        /* Iterator 반복자로 store 리스트 순회. hasNext(다음 요소 있는지 확인)*/
        for (Iterator<Employee> iterator = store.iterator(); iterator.hasNext(); ) {
            Employee e = iterator.next();
            if (Objects.equals(e.getEmployeeNum(), employeeNum)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}

