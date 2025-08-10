package main.solve;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Product {

    private String productCode; // 상품코드
    private String productName; // 상품명
    private int productPrice; // 판매가
    private LocalDateTime createDate; // 등록일
    private LocalDateTime updateDate; // 수정일
    private Employee createPerson; // 등록자
    private Employee updatePerson; // 수정자


    // 생성자
    public Product(String productCode, String name, int price,
                   LocalDateTime createDate, LocalDateTime updateDate,
                   Employee createPerson, Employee updatePerson) {

        // 상품코드: 숫자 8자리
        if (productCode == null || !productCode.matches("\\d{8}")) {
            throw new IllegalArgumentException("상품코드는 8자리 숫자여야 함.");
        }
        this.productCode = productCode;
        /* 상품명, 등록일, 수정일, 등록자, 수정자가 null 이 아니면 object 그대로 반환, null이면 메세지 던짐*/
        this.productName = Objects.requireNonNull(name, "상품명은 null일 수 없음.");
        setPrice(price); // 0 이상 검증
        this.createDate = Objects.requireNonNull(createDate, "등록일은 null일 수 없음.");
        this.updateDate = Objects.requireNonNull(updateDate, "수정일은 null일 수 없음.");
        this.createPerson = Objects.requireNonNull(createPerson, "등록자는 null일 수 없음.");
        this.updatePerson = Objects.requireNonNull(updatePerson, "수정자는 null일 수 없음.");
    }

    // 상품코드
    public String getProductCode() {
        return productCode;
    }

    // 상품명
    public String getProductName() {
        return productName;
    }

    // 판매가
    public int getProductPrice() {
        return productPrice;
    }

    // 등록일
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    // 수정일
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    // 등록자
    public Employee getCreatePerson() {
        return createPerson;
    }

    // 수정자
    public Employee getUpdatePerson() {
        return updatePerson;
    }

    // 가격 변경 (0 미만 안됨)
    public void setPrice(int price) { // 리턴 값 필요없어서 void
        if (price < 0) throw new IllegalArgumentException("판매가는 0 이상이어야 함.");
        this.productPrice = price;
    }


}
