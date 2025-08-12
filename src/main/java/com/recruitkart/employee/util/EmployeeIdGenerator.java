package com.recruitkart.employee.util;

public class EmployeeIdGenerator {

    public static String generateNextEmployeeCode(String lastCode) {
        if (lastCode == null || lastCode.isEmpty()) {
            return "EMPAAA0001"; // First employee ID
        }

        // Extract letter and number parts
        String letterPart = lastCode.substring(3, 6); // AAA
        String numberPart = lastCode.substring(6);    // 0001

        int number = Integer.parseInt(numberPart);
        number++;

        // If number part exceeds 9999, reset and increment letters
        if (number > 9999) {
            number = 1;
            letterPart = incrementLetters(letterPart);
        }

        return String.format("EMP%s%04d", letterPart, number);
    }

    // Helper to increment letter sequence AAA -> AAB -> AAC ...
    private static String incrementLetters(String letters) {
        char[] arr = letters.toCharArray();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < 'Z') {
                arr[i]++;
                for (int j = i + 1; j < arr.length; j++) {
                    arr[j] = 'A';
                }
                return new String(arr);
            }
        }

        throw new IllegalStateException("Letter overflow: " + letters);
    }
}
