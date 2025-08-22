package main.solve;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Products {

    // 내부 저장소
    private List<Product> productStore = new ArrayList<>();

    // 생성자
    public Products() {
    }

    // ----- 메서드 -----

    // 상품코드 오름차순
    public List<Product> sortedByProductCodeAsc() {
        return productStore.stream()
                /* product 객체에서 상품코드를 꺼내서 오름차순 정렬
                 * (product 타입 객체에서 getProductCode 메서드 호출해라) 람다식 product -> product.getProductCode() */
                .sorted(Comparator.comparing(Product::getProductCode))
                .collect(Collectors.toList());
    }

    // 판매가 내림차순
    public List<Product> sortedByProductPriceDesc() {
        return productStore.stream()
                // product 객체에서 판매가 꺼내어 비교 (기본 오름차순이라 reverse해서 내림차순으로 정렬)
                .sorted(Comparator.comparingInt(Product::getProductPrice).reversed())
                .collect(Collectors.toList());
    }

    // 등록일 내림차순
    public List<Product> sortedByCreateDateDesc() {
        return productStore.stream()
                // product 객체에서 등록일 꺼내어 비교
                .sorted(Comparator.comparing(Product::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    // 수정자 이름 오름차순
    public List<Product> sortedByUpdatePersonAsc() {
        return productStore.stream()
                // product 객체에서 수정자 꺼내고, 이름 비교해서 정렬
                .sorted(Comparator.comparing(p -> p.getUpdatePerson().getName()))
                .collect(Collectors.toList());
    }

    // 전체 상품 판매가 반환
    public long totalPrice() {
        long sum = 0L; //stream 1
        // productStore 안에있는 모든 요소 순서대로 읽기
        for (Product p : productStore) {
            sum += p.getProductPrice();
        }
        return sum;
    }

    // 등록일이 기준일 이후(포함)인 상품만 반환
    public List<Product> findCreatedOnOrAfter(LocalDateTime th) {
        return productStore.stream()
                // 등록일이 null 이 아니거나, 기준일보다 같거나, 이후 날짜만
                .filter(p -> p.getCreateDate() != null && !p.getCreateDate().isBefore(th))
                .collect(Collectors.toList());
    }

    // 판매가가 from ~ to 원 사이인 상품들 반환
    public List<Product> findByPriceBetween(int from, int to) {
        int lo = Math.min(from, to); // 순서 뒤집혀 들어와도 괜찮게끔
        int hi = Math.max(from, to);
        return productStore.stream()
                .filter(p -> {
                    int price = p.getProductPrice(); // Product 객체에서 판매가 가져오기
                    return price >= lo && price <= hi; // lo 이상이고 hi 이하인 가격만 통과
                })
                .collect(Collectors.toList());
    }


    // 상품 등록자별로 상품명을 ','로 결합하여 반환
    public Map<String, String> joinNamesByCreator() {
        // 등록자 이름을 key, 상품명을 value 리스트로 담는 임시 맵
        // 등록자별로 상품들을 묶는 작업이 필요해서,
        Map<String, List<String>> temp = new HashMap<>();

        // 모든 상품 순회
        for (Product p : productStore) {
            String key = p.getCreatePerson().getName(); // 이름 가져오기
            // 키가 없으면 새 arraylist를 만들어서 그 리스트 반환
            // 매번 if문 쓰는것보단, computeIfAbsent를 쓰면 간결함
            temp.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(p.getProductName());
        }
        // temp에 있는 (등록자 -> 상품명) 리스트를 (등록자 -> 상품명) 문자열로 변환
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, List<String>> e : temp.entrySet()) {
            result.put(e.getKey(), String.join(",", e.getValue()));
        }

        return result;
    }





    // 상품 추가
    public boolean add(Product p) {
        Objects.requireNonNull(p, "상품은 null X");
        return productStore.add(p);
    }

    // 상품코드로 제거
    public boolean removeByProductCode(String code) {
        Iterator<Product> it = productStore.iterator();
        while (it.hasNext()) {
            Product p = it.next();
            if (Objects.equals(p.getProductCode(), code)) {
                it.remove();
                return true;
            }
        }
        return false;
    }


}

