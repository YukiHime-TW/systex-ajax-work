package com.systex.ajaxwork.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.systex.ajaxwork.model.LotteryForm;

@Service
public class LotteryService {

    private HashSet<Integer> excludeNumbers = new HashSet<>();

    private Random random = new Random();

    public List<TreeSet<Integer>> getLottery(LotteryForm form) throws IllegalArgumentException {
        // 先清空 excludeNumbers
        excludeNumbers.clear();

        // 驗證輸入資料
        validate(form);

        List<TreeSet<Integer>> lotteryGroups = new ArrayList<>();

        for (int i = 0; i < form.getGroupCount(); i++) {
            lotteryGroups.add(generateLotteryGroup());
        }

        return lotteryGroups;
    }

    private TreeSet<Integer> generateLotteryGroup() {
    TreeSet<Integer> group = new TreeSet<>();
    while (group.size() < 6) {
        int number = random.nextInt(49) + 1; // Generates numbers from 1 to 49
        if (!group.contains(number) && !excludeNumbers.contains(number)) {
            group.add(number);
        }
    }
    return group;
}


    // 驗證輸入資料
    public void validate(LotteryForm form) throws IllegalArgumentException {
        validateGroupCount(form.getGroupCount());
        validateExcludeNumbers(form.getExcludeNumberString());
    }

    private void validateGroupCount(int groupCount) {
        if (groupCount <= 0) {
            throw new IllegalArgumentException("組數必須大於0");
        }
    }

    private void validateExcludeNumbers(String excludeNumberString) {
        if (excludeNumberString == null || excludeNumberString.isEmpty()) {
            return; // 如果沒有排除號碼，則不需進行驗證
        }

        String[] excludeNumberStrings = excludeNumberString.split(" ");
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (String excludeNumber : excludeNumberStrings) {
            int number = parseExcludeNumber(excludeNumber);
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException("排除號碼必須是唯一的");
            }
        }

        // 將唯一的號碼加入 excludeNumbers
        excludeNumbers.addAll(uniqueNumbers);
        validateExcludeNumbersLimit();
    }

    private int parseExcludeNumber(String excludeNumber) {
        try {
            int number = Integer.parseInt(excludeNumber);
            if (number < 1 || number > 49) {
                throw new IllegalArgumentException("排除號碼必須介於1~49");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("排除號碼必須是數字");
        }
    }

    private void validateExcludeNumbersLimit() {
        if (excludeNumbers.size() > 43) {
            throw new IllegalArgumentException("排除號碼必須小於等於43個");
        }
    }
}
