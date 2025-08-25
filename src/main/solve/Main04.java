//package main.solve;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
///**
// * 문제 4 / 친구 초대 이벤트
// * @author minjua
// * @since 2025-08-24
// *
// */
//public class Main04 {
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        int[] coupon = new int[N];
//        boolean[] registered = new boolean[N + 1];
//
//        for (int i = 1; i < M; i++) {
//            StringTokenizer st1 = new StringTokenizer(br.readLine());
//            int invite = Integer.parseInt(st1.nextToken());
//            int get = Integer.parseInt(st1.nextToken());
//
//            if (!registered[get]) {               // 첫 초대 → 가입 처리
//                registered[get] = true;
//                coupon[invite]++;
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int id = 1; id <= N; id++) {
//            sb.append(id).append(' ').append(coupon[id]).append('\n');
//        }
//        System.out.print(sb.toString());
//        br.close();
//    }
//}