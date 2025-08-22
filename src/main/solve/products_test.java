//package main.solve;
//
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class Products_test {
//
//    // DB 연결 정보
//    private static final String URL = "jdbc:mysql://112.184.113.85:3306/java_project";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Ofpdlfnfn0341!";
//
//    // 내부 저장소
//    private List<Product> productStore = new ArrayList<>();
//
//    // 생성자
//    public Products_test() {
//        loadFromDatabase();
//    }
//
//    /**
//     * DB에서 products 테이블 데이터를 불러와 productStore에 저장
//     */
//    private void loadFromDatabase() {
//        productStore.clear();
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products");
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                // Employee 생성 (null 방지 + 기본값 적용)
//                Employee creator = new Employee(
//                        "00001", // 사번: 5자리 숫자
//                        Optional.ofNullable(rs.getString("create_person")).orElse("김민주"),
//                        LocalDateTime.of(2000, 1, 1, 0, 0),
//                        LocalDateTime.now(),
//                        "개발팀",
//                        "사원"
//                );
//
//                Employee updater = new Employee(
//                        "00002",
//                        Optional.ofNullable(rs.getString("update_person")).orElse("김민주"),
//                        LocalDateTime.of(2000, 1, 1, 0, 0),
//                        LocalDateTime.now(),
//                        "개발팀",
//                        "사원"
//                );
//
//                Product p = new Product(
//                        rs.getString("product_code"),
//                        rs.getString("product_name"),
//                        rs.getInt("product_price"),
//                        rs.getTimestamp("create_date").toLocalDateTime(),
//                        rs.getTimestamp("update_date").toLocalDateTime(),
//                        creator,
//                        updater
//                );
//
//                productStore.add(p);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // ===== 기존 기능 ===== //
//
//    public List<Product> sortedByProductCodeAsc() {
//        return productStore.stream()
//                .sorted(Comparator.comparing(Product::getProductCode))
//                .collect(Collectors.toList());
//    }
//
//    public List<Product> sortedByProductPriceDesc() {
//        return productStore.stream()
//                .sorted(Comparator.comparingInt(Product::getProductPrice).reversed())
//                .collect(Collectors.toList());
//    }
//
//    public List<Product> sortedByCreateDateDesc() {
//        return productStore.stream()
//                .sorted(Comparator.comparing(Product::getCreateDate).reversed())
//                .collect(Collectors.toList());
//    }
//
//    public List<Product> sortedByUpdatePersonAsc() {
//        return productStore.stream()
//                .sorted(Comparator.comparing(p -> p.getUpdatePerson().getName()))
//                .collect(Collectors.toList());
//    }
//
//    public long totalPrice() {
//        long sum = 0L;
//        for (Product p : productStore) {
//            sum += p.getProductPrice();
//        }
//        return sum;
//    }
//
//    public List<Product> findCreatedOnOrAfter(LocalDateTime th) {
//        return productStore.stream()
//                .filter(p -> p.getCreateDate() != null && !p.getCreateDate().isBefore(th))
//                .collect(Collectors.toList());
//    }
//
//    public List<Product> findByPriceBetween(int from, int to) {
//        int lo = Math.min(from, to);
//        int hi = Math.max(from, to);
//        return productStore.stream()
//                .filter(p -> {
//                    int price = p.getProductPrice();
//                    return price >= lo && price <= hi;
//                })
//                .collect(Collectors.toList());
//    }
//
//    public Map<String, String> joinNamesByCreator() {
//        Map<String, List<String>> temp = new HashMap<>();
//        for (Product p : productStore) {
//            String key = p.getCreatePerson().getName();
//            temp.computeIfAbsent(key, k -> new ArrayList<>())
//                    .add(p.getProductName());
//        }
//        Map<String, String> result = new HashMap<>();
//        for (Map.Entry<String, List<String>> e : temp.entrySet()) {
//            result.put(e.getKey(), String.join(",", e.getValue()));
//        }
//        return result;
//    }
//
//    public boolean add(Product p) {
//        Objects.requireNonNull(p, "상품은 null X");
//        return productStore.add(p);
//    }
//
//    public boolean removeByProductCode(String code) {
//        Iterator<Product> it = productStore.iterator();
//        while (it.hasNext()) {
//            Product p = it.next();
//            if (Objects.equals(p.getProductCode(), code)) {
//                it.remove();
//                return true;
//            }
//        }
//        return false;
//    }
//}
