package com.gql.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author gql
 * @date 2020/10/17
 **/
public class GitCommitTest {
    public static void main(String[] args) {
        TestSort sort3 = new TestSort("20200104","0404221","kkk3");
        TestSort sort6 = new TestSort("20200103","0404221","kkk2");
        TestSort sort7 = new TestSort("20200203","0404221","kkk2");
        TestSort sort8 = new TestSort("20200303","0404221","kkk2");
        TestSort sort9 = new TestSort("20200403","0404221","kkk2");
        TestSort sort10 = new TestSort("20200503","0404221","kkk2");
        TestSort sort11 = new TestSort("20200603","0404221","kkk2");

        TestSort sort4 = new TestSort("20200102","0404222","kkk1");
        TestSort sort5 = new TestSort("20200103","0404222","kkk2");
        TestSort sort1 = new TestSort("20191230","0404221","kkk1");

        List<TestSort> listLoops = new ArrayList<>();

        listLoops.add(sort1);
        listLoops.add(sort7);
        listLoops.add(sort8);
        listLoops.add(sort9);
        listLoops.add(sort10);
        listLoops.add(sort11);
        listLoops.add(sort6);
        listLoops.add(sort3);
        listLoops.add(sort4);
        listLoops.add(sort5);
        Map<String, List<TestSort>> collect = listLoops.stream().collect(Collectors.groupingBy(TestSort::getCode));
        System.out.println(collect.toString());
        List<TestSort> testSorts = new ArrayList<>();
        for (Map.Entry<String, List<TestSort>> value: collect.entrySet()){
            System.out.println(value.getValue());
            List<TestSort> collect1 = value.getValue().stream().sorted(Comparator.comparing(TestSort::getDate)).collect(Collectors.toList());
            testSorts.addAll(collect1);
        }
        //排序后输出
        System.out.println("排序后输出"+testSorts.toString());
    }
}


class TestSort{
    private String date ;

    private String code;

    private String context;

    public TestSort(String date, String code, String context) {
        this.date = date;
        this.code = code;
        this.context = context;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "TestSort{" + "date='" + date + '\'' + ", code='" + code + '\'' + ", context='" + context + '\'' + '}';
    }
}



class TestMerggin{
    public static void main(String[] args) {
        String str = "3458790er6789456789456789045780";

        AtomicInteger integer = new AtomicInteger(0);
        System.out.println("456789");

    }
}