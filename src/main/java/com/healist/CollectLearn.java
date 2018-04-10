package com.healist;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @Author healist
 * @Description
 * @Create 2018-04-10 上午9:58
 */
public class CollectLearn {
    @Data
    @AllArgsConstructor
    static class Dish {
        int calories;
        String name;
        int type;
        boolean vegetarian;
    }

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish(100, "aa", 1, true),
                new Dish(200, "bb", 2, true),
                new Dish(300, "cc", 3, false));

        long count = menu.stream().count();
        count = menu.stream().collect(Collectors.counting());

        // 找出菜单中的热量最高的菜
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishComparator));
        // 求和、平均等操作
        int total = menu.stream().collect(summingInt(Dish::getCalories));

        //连接字符,所有菜名连接起来
        String strMenu = menu.stream().map(Dish::getName).collect(joining());
        strMenu = menu.stream().map(Dish::getName).collect(joining(", "));

        // collect中的reduce操作
        total = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i+j));

        // 分组操作，按菜单类型进行分类， 多级分组，就是多个groupby嵌套
        Map<Integer, List<Dish>> dishedType = menu.stream().collect(groupingBy(Dish::getType));


        // 分区操作, 按boolean值划分区
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        // 以上很多方法都可以组合嵌套操作

    }


}
