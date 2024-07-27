package com.goit.GoIT2_HW_15.note;

import java.util.Random;

public class NoteIdGenerator {
    private static final Random random = new Random();
    public static long generateId() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            result.append(random.nextInt(0,9));
        }
        return Long.parseLong(result.toString());
    }
}
