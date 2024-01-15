package usuallyalgorithm.common;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataSamePartMerge {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(CollUtil.newArrayList(1, 3));
        lists.add(CollUtil.newArrayList(2, 3));
        lists.add(CollUtil.newArrayList(3, 4));
        lists.add(CollUtil.newArrayList(4, 5));
        lists.add(CollUtil.newArrayList(5, 50));
        lists.add(CollUtil.newArrayList(9, 10));
        lists.add(CollUtil.newArrayList(15, 20));
        lists.add(CollUtil.newArrayList(1000, 2000));

        sameDataDuanPartMerge(lists, null);
        System.out.println("lists = " + lists);
    }

    private static void sameDataDuanPartMerge(List<List<Integer>> lists, List<Integer> toAddDuan) {
        if (CollUtil.isNotEmpty(toAddDuan)) {
            Optional<List<Integer>> findAnyData = lists.stream().filter(item -> pledgeIsLianxu(item, toAddDuan)).findFirst();
            if (!findAnyData.isPresent()) {
                lists.add(toAddDuan);
                return;
            }
            //lists.add(toAddDuan);
            // 出口
            //return;
            for (int i = 0; i < lists.size(); i++) {
                Boolean b = pledgeIsLianxu(lists.get(i), toAddDuan);
                // 应该是一个集合里的元素都没有连续性才添加
                // 续上
                if (b.equals(Boolean.TRUE)) {
                    System.out.println("toAddDuan = " + toAddDuan);
                    //lists.get(i).addAll(toAddDuan);
                    // tempAddDuan,续上之后，丢进去重新校验
                    List<Integer> tempAddDuan = new ArrayList<>();
                    tempAddDuan.addAll(lists.get(i));
                    tempAddDuan.addAll(toAddDuan);
                    lists.remove(i);
                    // 只剩一段了
                    if (CollUtil.isEmpty(lists)) {
                        lists.add(tempAddDuan);
                        return;
                    }
                    sameDataDuanPartMerge(lists, tempAddDuan);
                    // 保证在循环中只执行一遍 坑一很难发现
                    //return;
                }

            }
        }
        if (CollUtil.isNotEmpty(toAddDuan)) {
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.size(); j++) {
                // 数据发生变化？？？？？还不太清楚根本
                //if (i == j) {
                //    continue;
                //}
                Boolean b = pledgeIsLianxu(lists.get(i), lists.get(j));
                if (b.equals(Boolean.FALSE)) {

                } else {
                    //续上
                    System.out.println("跟别人合并的段" + lists.get(i));
                    //lists.get(j).addAll(lists.get(i));
                    List<Integer> tempAddDuan = new ArrayList<>();
                    tempAddDuan.addAll(lists.get(i));
                    tempAddDuan.addAll(lists.get(j));
                    //移除  移除之后下标就不对了
                    lists.remove(i);
                    if (j - 1 >= 0) {
                        lists.remove(j - 1);
                    }
                    sameDataDuanPartMerge(lists, tempAddDuan);
                }
            }
        }
    }

    private static Boolean pledgeIsLianxu(List<Integer> integers, List<Integer> toAddDuan) {
        if (CollUtil.isEmpty(integers) || CollUtil.isEmpty(toAddDuan)) {
            throw new RuntimeException("pledgeIsLianxu error");
        }
        List<Integer> collect = integers.stream().sorted().collect(Collectors.toList());
        List<Integer> collect2 = toAddDuan.stream().sorted().collect(Collectors.toList());
        if ((collect2.get(0).intValue() >= collect.get(0).intValue()) && (collect2.get(0).intValue() <= collect.get(collect.size() - 1).intValue())) {
            return Boolean.TRUE;
        } else if ((collect2.get(collect2.size() - 1).intValue() >= collect.get(0).intValue()) && (collect2.get(collect2.size() - 1).intValue() <= collect.get(collect.size() - 1).intValue())) {
            return Boolean.TRUE;
        } else if ((collect.get(0).intValue() >= collect2.get(0).intValue()) && (collect.get(0).intValue() <= collect2.get(collect2.size() - 1).intValue())) {
            return Boolean.TRUE;
        } else if ((collect.get(collect.size() - 1).intValue() >= collect2.get(0).intValue()) && (collect.get(collect.size() - 1).intValue() <= collect2.get(collect2.size() - 1).intValue())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
