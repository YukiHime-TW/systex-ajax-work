package com.systex.ajaxwork.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.systex.ajaxwork.model.LotteryForm;

@Service
public class LotteryService {

    private HashSet<Integer> excludeNumbers = new HashSet<>();

    public ArrayList<TreeSet<Integer>> getLottery(LotteryForm form) throws Exception {

        // 先清空 excludeNumbers
        excludeNumbers.clear();

        // 驗證輸入資料
        validate(form);

        ArrayList<TreeSet<Integer>> list = new ArrayList<>();

        for (int i = 0; i < form.getGroupCount(); i++) {
            TreeSet<Integer> group = new TreeSet<>();
            while (group.size() < 6) {
                int number = (int) (Math.random() * 49) + 1;
                if (!group.contains(number) && !excludeNumbers.contains(number)) {
                    group.add(number);
                }
            }
            // 將 group 加入 list
            list.add(group);
        }

        return list;
    }

    // 驗證輸入資料
    public void validate(LotteryForm form) throws Exception {

        // 1.組數必須大於0
        if (form.getGroupCount() <= 0) {
            throw new IllegalArgumentException("組數必須大於0");
        }

        // 如果有輸入排除號碼，才執行以下驗證
        if (form.getExcludeNumberString() != null || !form.getExcludeNumberString().isEmpty()) {
            // 2.排除號碼必須是數字且介於1~49
            String[] excludeNumberStrings = form.getExcludeNumberString().split(" ");
            Set<Integer> uniqueNumbers = new HashSet<>();

            for (String excludeNumber : excludeNumberStrings) {
                int number;
                try {
                    number = Integer.parseInt(excludeNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("排除號碼必須是數字");
                }

                if (number < 1 || number > 49) {
                    throw new IllegalArgumentException("排除號碼必須介於1~49");
                }

                if (!uniqueNumbers.add(number)) {
                    throw new IllegalArgumentException("排除號碼必須是唯一的");
                }
            }

            // 將唯一的號碼加入 excludeNumbers
            excludeNumbers.addAll(uniqueNumbers);

            // 3.排除號碼必須小於等於43個
            if (excludeNumbers.size() > 43) {
                throw new IllegalArgumentException("排除號碼必須小於等於43個");
            }
        }
    }
}
