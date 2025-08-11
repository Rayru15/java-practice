package main.solve;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Main02 {
    public static void main(String[] args) {
        System.out.println("--- Employee 및 Employees 클래스 기능 시연 ---");
        System.out.println("----------------------------------------------\n");

        // 1. Employee 객체 생성
        Employee emp1 = new Employee("00001", "홍길동", LocalDateTime.of(1990, 5, 15, 0, 0), LocalDateTime.of(2015, 3, 1, 0, 0), "개발팀", "과장");
        Employee emp2 = new Employee("00002", "김철수", LocalDateTime.of(1995, 10, 20, 0, 0), LocalDateTime.of(2018, 7, 20, 0, 0), "영업팀", "사원");
        Employee emp3 = new Employee("00003", "박영희", LocalDateTime.of(1988, 1, 10, 0, 0), LocalDateTime.of(2010, 1, 5, 0, 0), "마케팅팀", "부장");
        Employee emp4 = new Employee("00004", "이영미", LocalDateTime.of(1992, 3, 25, 0, 0), LocalDateTime.of(2017, 11, 11, 0, 0), "개발팀", "대리");
        Employee emp5 = new Employee("00005", "최지훈", LocalDateTime.of(1995, 10, 20, 0, 0), LocalDateTime.of(2020, 5, 1, 0, 0), "영업팀", "사원");

        // Employee 클래스 메서드 시연
        System.out.println("[Employee] " + emp1.getName() + "의 오늘 기준 나이: " + emp1.getAge() + "세");
        System.out.println("[Employee] " + emp2.getName() + "은 오늘이 생일인가? " + emp2.isBirthday());
        System.out.println("[Employee] " + emp1.getName() + "의 입사일로부터 " + emp1.getDays() + "일째\n");

        // 2. Employees 관리 객체 생성 및 사원 추가
        Employees employees = new Employees();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        employees.add(emp4);
        employees.add(emp5);

        // Employees 클래스 정렬 메서드 시연
        System.out.println("----- 사번 순 정렬 (오름차순) -----");
        List<Employee> sortedByEmpNum = employees.sortedByEmployeeNumAsc();
        sortedByEmpNum.forEach(emp -> System.out.println(emp.getEmployeeNum() + " - " + emp.getName()));

        System.out.println("\n----- 나이 순 정렬 (오름차순) -----");
        List<Employee> sortedByAge = employees.sortedByAgeAsc();
        sortedByAge.forEach(emp -> System.out.println(emp.getName() + " (" + emp.getAge() + "세)"));

        System.out.println("\n----- 직급 순 정렬 (내림차순) -----");
        List<Employee> sortedByRank = employees.sortedByRankDesc();
        sortedByRank.forEach(emp -> System.out.println(emp.getRank() + " - " + emp.getName()));

        System.out.println("\n----- 이름 순 정렬 (오름차순) -----");
        List<Employee> sortedByName = employees.sortedByNameAsc();
        sortedByName.forEach(emp -> System.out.println(emp.getName()));

        System.out.println("\n----- 특정 날짜(10월 20일)에 생일인 사원들 -----");
        LocalDate birthday = LocalDate.of(2025, 10, 20); // 예시 날짜
        List<Employee> birthdayEmployees = employees.findBirthdays(birthday);
        birthdayEmployees.forEach(emp -> System.out.println(emp.getName()));

        System.out.println("\n----- 입사일이 2017년 ~ 2018년 사이인 사원들 -----");
        LocalDateTime fromDttm = LocalDateTime.of(2017, 1, 1, 0, 0);
        LocalDateTime toDttm = LocalDateTime.of(2018, 12, 31, 23, 59);
        List<Employee> hiredEmployees = employees.findByJoinDateBetween(fromDttm, toDttm);
        hiredEmployees.forEach(emp -> System.out.println(emp.getName() + " (" + emp.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")"));

        System.out.println("\n----- 사번 '00005'인 사원 제거 후 -----");
        employees.removeByEmployeeNum("00005");
        sortedByEmpNum = employees.sortedByEmployeeNumAsc();
        sortedByEmpNum.forEach(emp -> System.out.println(emp.getEmployeeNum() + " - " + emp.getName()));

        System.out.println("\n\n--- Product 및 Products 클래스 기능 시연 ---");
        System.out.println("----------------------------------------------\n");

        // 3. Product 객체 생성 및 수정
        Product prod1 = new Product("10000001", "노트북", 1500000, LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(5), emp1, emp1);
        Product prod2 = new Product("10000002", "마우스", 50000, LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(1), emp2, emp2);
        Product prod3 = new Product("10000003", "키보드", 120000, LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(3), emp1, emp1);
        Product prod4 = new Product("10000004", "모니터", 300000, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(10), emp3, emp3);

        // 상품 수정
        prod3 = new Product("10000003", "키보드", 110000, prod3.getCreateDate(), LocalDateTime.now(), emp1, emp4);

        // 4. Products 관리 객체 생성 및 상품 추가
        Products products = new Products();
        products.add(prod1);
        products.add(prod2);
        products.add(prod3);
        products.add(prod4);

        // Products 클래스 정렬 메서드 시연
        System.out.println("----- 상품코드 순 정렬 (오름차순) -----");
        List<Product> sortedByProdCode = products.sortedByProductCodeAsc();
        sortedByProdCode.forEach(p -> System.out.println(p.getProductCode() + " - " + p.getProductName()));

        System.out.println("\n----- 판매가 순 정렬 (내림차순) -----");
        List<Product> sortedByPrice = products.sortedByProductPriceDesc();
        sortedByPrice.forEach(p -> System.out.println(p.getProductName() + " - " + p.getProductPrice() + "원"));

        System.out.println("\n----- 등록일 순 정렬 (내림차순) -----");
        List<Product> sortedByRegDate = products.sortedByCreateDateDesc();
        sortedByRegDate.forEach(p -> System.out.println(p.getProductName() + " - " + p.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        System.out.println("\n----- 수정자 이름 순 정렬 (오름차순) -----");
        List<Product> sortedByModName = products.sortedByUpdatePersonAsc();
        sortedByModName.forEach(p -> System.out.println(p.getProductName() + " (수정자: " + p.getUpdatePerson().getName() + ")"));

        System.out.println("\n----- 전체 상품 판매가 합계: " + products.totalPrice() + "원 -----");

        System.out.println("\n----- 등록일이 특정 날짜 이후인 상품들 -----");
        LocalDateTime afterDate = LocalDateTime.now().minusDays(2);
        List<Product> registeredAfter = products.findCreatedOnOrAfter(afterDate);
        registeredAfter.forEach(p -> System.out.println(p.getProductName() + " - " + p.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

        System.out.println("\n----- 판매가 10만원 ~ 50만원 사이인 상품들 -----");
        List<Product> priceRangeProducts = products.findByPriceBetween(100000, 500000);
        priceRangeProducts.forEach(p -> System.out.println(p.getProductName() + " - " + p.getProductPrice() + "원"));

        System.out.println("\n----- 등록자별 상품 목록 -----");
        Map<String, String> productsByRegistrant = products.joinNamesByCreator();
        productsByRegistrant.forEach((name, items) -> System.out.println("등록자: " + name + " -> 상품: " + items));

        System.out.println("\n----- 상품코드 '10000004' 제거 후 -----");
        products.removeByProductCode("10000004");
        sortedByProdCode = products.sortedByProductCodeAsc();
        sortedByProdCode.forEach(p -> System.out.println(p.getProductCode() + " - " + p.getProductName()));
    }
}