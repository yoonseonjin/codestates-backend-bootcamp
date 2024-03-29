package com.codestates.section2week4.coffee;

import com.codestates.section2week4.DependencyConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CoffeeTest {   // 커피 로직 테스트 구현
    public static void main(String[] args) {
        // 스프링 컨테이너에 등록된 빈을 가져온다
        ApplicationContext ac = new AnnotationConfigApplicationContext(DependencyConfig.class);
        CoffeeService coffeeService = ac.getBean("coffeeService", CoffeeService.class);

        Coffee coffee = new Coffee(0L, "바닐라 라떼", "Vanilla Latte", 5500);
        coffeeService.createCoffee(coffee);

        if (coffeeService.getCoffee(0L).getKorName().equals(coffee.getKorName())) {
            System.out.println("바닐라 라떼가 등록되었습니다.");
        }

        coffeeService.editCoffee(0L, "바닐라 라떼", 3000);   // 수정

        if (coffeeService.getCoffee(0L).getPrice() == 3000) {
            System.out.println("바닐라 라떼의 금액이 정상적으로 변경되었습니다.");
        }

        coffeeService.deleteCoffee(0L); // 삭제

        if (coffeeService.getCoffee(0L) == null) {
            System.out.println("바닐라 라떼가 정상적으로 삭제되었습니다.");
        }
    }
}
