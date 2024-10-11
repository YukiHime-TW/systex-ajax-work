package com.systex.ajaxwork.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.systex.ajaxwork.model.LotteryForm;

@Service
public class LotteryService {

    private final HashSet<Integer> excludeNumbers = new HashSet<>();

    public ArrayList<TreeSet<Integer>> getLottery(LotteryForm form) throws Exception {
        // 清空 excludeNumbers 並驗證輸入資料
        excludeNumbers.clear();
        validate(form);

        ArrayList<TreeSet<Integer>> lotteryGroups = new ArrayList<>();
        generateLotteryNumbers(form, lotteryGroups);

        return lotteryGroups;
    }

    private void generateLotteryNumbers(LotteryForm form, ArrayList<TreeSet<Integer>> lotteryGroups) {
        for (int i = 0; i < form.getGroupCount(); i++) {
            TreeSet<Integer> group = createLotteryGroup();
            lotteryGroups.add(group);
        }
    }

    private TreeSet<Integer> createLotteryGroup() {
        TreeSet<Integer> group = new TreeSet<>();
        while (group.size() < 6) {
            int number = generateRandomNumber();
            if (isValidNumber(number, group)) {
                group.add(number);
            }
        }
        return group;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 49) + 1;
    }

    private boolean isValidNumber(int number, TreeSet<Integer> group) {
        return !group.contains(number) && !excludeNumbers.contains(number);
    }

    // 驗證輸入資料
    public void validate(LotteryForm form) throws Exception {
        validateGroupCount(form);
        if (form.getExcludeNumberString() != null && !form.getExcludeNumberString().isEmpty()) {
            validateExcludeNumbers(form);
        }
    }

    private void validateGroupCount(LotteryForm form) {
        if (form.getGroupCount() <= 0) {
            throw new IllegalArgumentException("組數必須大於0");
        }
    }

    private void validateExcludeNumbers(LotteryForm form) {
        String[] excludeNumberStrings = form.getExcludeNumberString().split(" ");
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (String excludeNumber : excludeNumberStrings) {
            int number = parseExcludeNumber(excludeNumber);
            validateSingleExcludeNumber(number, uniqueNumbers);
        }

        // 將唯一的號碼加入 excludeNumbers
        excludeNumbers.addAll(uniqueNumbers);

        // 檢查排除號碼的數量
        if (excludeNumbers.size() > 43) {
            throw new IllegalArgumentException("排除號碼必須小於等於43個");
        }
    }

    private int parseExcludeNumber(String excludeNumber) {
        try {
            return Integer.parseInt(excludeNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("排除號碼必須是數字");
        }
    }

    private void validateSingleExcludeNumber(int number, Set<Integer> uniqueNumbers) {
        if (number < 1 || number > 49) {
            throw new IllegalArgumentException("排除號碼必須介於1~49");
        }

        if (!uniqueNumbers.add(number)) {
            throw new IllegalArgumentException("排除號碼必須是唯一的");
        }
    }
}
