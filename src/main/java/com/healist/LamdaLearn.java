package com.healist;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author healist
 * @Description
 * @Create 2018-04-07 上午9:54
 */
public class LamdaLearn {

    /**
     * 函数接口 Function、Supplier、Predicate、Consumer等
     */


    // consumer
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i : list){
            c.accept(i);
        }
    }



    public static void main(String[] args) {
        //实例化
        Supplier<AppleFilter> filterSupplier = AppleFilter::new;

        // consumer
        forEach(Arrays.asList(1,2,3,4), (Integer i) -> System.out.println(i));

    }

}
