package com.recruitkart.common.util;

import org.springframework.stereotype.Service;

@Service
public class EmployeeIdGenerator {

    // Generate next ID based on last saved ID
    public static String generateNextId(String lastId) {
        if (lastId == null || lastId.isEmpty()) {
            return "EMP-RKAAA-001"; // start default
        }

        // lastId example: EMP-RKAAB-045
        String[] parts = lastId.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Employee ID format: " + lastId);
        }

        String middleCode = parts[1];
        int number;

        try {
            number = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid sequence number in ID: " + lastId, e);
        }

        number++; // increment number

        if (number > 999) {
            number = 1;
            middleCode = nextCode(middleCode);
        }

        return String.format("EMP-%s-%03d", middleCode, number);
    }

    private static String nextCode(String code) {
        char[] chars = code.toCharArray();

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != 'Z') {
                chars[i]++;  // increment character
                break;
            } else {
                chars[i] = 'A'; // reset and carry over
            }
        }
        return new String(chars);
    }
}
