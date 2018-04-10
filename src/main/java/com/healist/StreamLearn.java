package com.healist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author healist
 * @Description
 * @Create 2018-04-08 上午9:38
 */
public class StreamLearn {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Apple {
        int weight;
        String color;

        boolean isBad() {
            return false;
        }
    }

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple(22, "red"),
                new Apple(18, "red"),
                new Apple(25, "green"),
                new Apple(26, "red"),
                new Apple(28, "red"),
                new Apple(15, "green") );

        //java8中的便利过滤操作
        List<String> result = list.stream().filter(item -> item.getWeight()>18)
                .distinct()
                .sorted(Comparator.comparing(Apple::getWeight))
                .map(Apple::getColor)
                .skip(1)
                .limit(4)
                .collect(toList());

        // map主要用于映射

        // flatMap可用于整合归纳多个stream流为一个总流，实现扁平化处理
        List<String> words = new ArrayList<>();
        List<String> uniqueCharacterws = words.stream().map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        result.forEach(System.out::println);

        // 查找和匹配  查找有findAny和findFirst
        if(list.stream().anyMatch(Apple::isBad)) {
            System.out.println("all apple is bad !");
        }

        if(list.stream().allMatch(Apple::isBad)) {
            System.out.println("all apple is bad !");
        }

        if(list.stream().noneMatch(Apple::isBad)) {
            System.out.println("this will happen !");
        }

        //reduce 迭代操作
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        int sum = numbers.stream().reduce(1, (a, b) -> a*b);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(sum + "," + max.get());


        // stream的装箱和拆箱
        int cal = list.stream().mapToInt(Apple::getWeight).sum();

        IntStream intStream = list.stream().mapToInt(Apple::getWeight);
        Stream<Integer> stream = intStream.boxed();


        // Stream 的创建

        // 由值创建流
        Stream<String> stringStream = Stream.of("Java8", "Lamdas", "In", "Action");
        //空流
        Stream<String> emptyStream = Stream.empty();

        // 数组创建流
        int[] arr = {2,3,5,7,11,13};
        int sum2 = Arrays.stream(arr).sum();

        // 文件生成流
        try {
                Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset());
                long uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                        .distinct()
                        .count();
                System.out.println(uniqueWords);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // 创建无限流 iterate 和 generate函数方法


    }
