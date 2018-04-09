package com.healist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @Author healist
 * @Description
 * @Create 2018-04-09 上午9:28
 */
public class StreamPractice {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Trader {
        private String name;
        private String city;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Transaction {
        private Trader trader;
        private int year;
        private int value;
    }

    public static List<Transaction> init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");


        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }

    public static void main(String[] args) {

        List<Transaction> transactions = init();

        // 找出2011年的所有交易并按交易额排序
        List<Transaction> list_one = transactions.stream()
                .filter(item -> item.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(list_one);

        //交易员在哪些城市呆过
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities);

        // 查找所有来自剑桥的交易员，并按姓名排序
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders);

        // 返回所有交易员的姓名字符串，按照字母排序
        String names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .collect(joining());
        System.out.println(names);

        // 有没有交易员在米兰工作的
        boolean exist = transactions.stream()
                .anyMatch(transaction -> "milan".equals(transaction.getTrader().getCity()));

        // 打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 所有交易中，最高的交易额
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max.get());

        // 所有交易中，最小的交易额
        Optional<Transaction> minTransaction = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(minTransaction.get().getValue());
    }


}
