package com.healist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author healist
 * @Description java8中已经提供了filter，这里简单演示一下自定义filter的新老版本的实现
 * @Create 2018-03-16 上午10:46
 */
public class AppleFilter {

    // 老的写法，以下会造成代码重复

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight()>100) {
                result.add(apple);
            }
        }
        return result;
    }

    // java8 合并成一个

    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        // ... 省略添加苹果的操作
        inventory.add(new Apple(120, "green"));
        inventory.add(new Apple(90, "red"));
        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, Apple::isHeavyApple);

        //还有一种比较简便的写法
        filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
        filterApples(inventory, (Apple a) -> a.getWeight()>100);
        inventory.sort(Comparator.comparing(Apple::getWeight));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Apple {
        int weight;
        String color;

        public static boolean isGreenApple(Apple apple) {
            return "green".equals(apple.getColor());
        }

        public static boolean isHeavyApple(Apple apple) {
            return apple.getWeight()>100;
        }
    }
}
