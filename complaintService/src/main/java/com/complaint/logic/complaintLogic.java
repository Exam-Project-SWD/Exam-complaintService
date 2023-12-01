package com.complaint.logic;

import java.util.Arrays;

public class complaintLogic {
    public static boolean validComplaint(String complaint) {
        int minComplaintLength = 10;
        String[] bannedWords = {"fuck", "frick", "shit", "ass"};

        boolean badWords = !Arrays.stream(bannedWords).anyMatch(complaint::contains);
        boolean complaintLength = complaint.length() > minComplaintLength;

        return ((badWords & complaintLength) ? true : false);
    }
}