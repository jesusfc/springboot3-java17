package com.jesusfc.springboot3java17.scratches;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

class Scratch {


    private static final List listOne = Arrays.asList("Jack", "Tom", "Sam", "John", "James", "Jack");
    private static final List listTwo = Arrays.asList("Jack", "Daniel", "Sam", "Alan", "James", "George");

    public static final String FILE_SERVER_LOCATION = "https://f002.backblazeb2.com/file/infinityride-audio";
    public static final String[] VALUES = new String[]{"http://", "https://", "HTTP://", "HTTPS://"};

    public static void main(String[] args) {


        //String st = "https://infinityride-audio.s3.us-west-002.backblazeb2.com/16669525039471666949036558Tarrasa%20mp3.mp3";
        //String result = Arrays.asList("http://", "https://").stream().anyMatch(st.toLowerCase()::contains) ? st : FILE_SERVER_LOCATION + st;
        //System.out.println(result);

        //aa();
        //checkDominio();

        long timestamp = new Date().getTime();

        System.out.println("Timestamp: " + timestamp);

        Instant instant = Instant.now();
        long timestamp2 = instant.toEpochMilli();

        System.out.println("Timestamp2: " + timestamp2);
    }

    private static void checkDominio() {
        String domain = "etenon.tv";
        if (domain.equals("myclub.tv")) {
            System.out.println("IGUALES");
        } else {
            System.out.println("DIFERENTES");
        }
    }

    private static void replaceSubString() {
        String domain = "www.dominio.com";
        if (domain.contains("www."))
            domain = domain.replace("www.", "");
        System.out.println(domain);
    }

    private static void checkList() {

        List<String> differences = (List<String>) listOne.stream().filter(element -> !listTwo.contains(element)).collect(Collectors.toList());

        System.out.println(differences);

        List<String> differences_2 = (List<String>) listTwo.stream().filter(element -> !listOne.contains(element)).collect(Collectors.toList());
        System.out.println(differences_2);
    }

    private static void aa() {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 58, 59, 60, 61, 62, 63, 64, 65, 68, 69, 70, 74, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 109, 110, 111, 112, 114, 115, 116, 117, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134};
        int[] b = {9357, 9358, 9359, 9360, 9361, 9362, 9363, 9364, 9365, 9366, 9367, 9368, 9369, 9370, 9371, 9372, 9373, 9374, 9375, 9376, 9377, 9378, 9379, 9380, 9381, 9382, 9383, 9384, 9385, 9386, 9387, 9388, 9389, 9390, 9391, 9392, 9393, 9394, 9395, 9396, 9397, 9398, 9399, 9400, 9401, 9402, 9403, 9404, 9405, 9406, 9407, 9408, 9409, 9410, 9411, 9412, 9413, 9414, 9415, 9416, 9417, 9418, 9419, 9420, 9421, 9422, 9423, 9424, 9425, 9426, 9427, 9428, 9429, 9430, 9431, 9432, 9433, 9434, 9435, 9436, 9437, 9438, 9439, 9440, 9441, 9442, 9443, 9444, 9445, 9446, 9447, 9448, 9449, 9450, 9451, 9452, 9453, 9454, 9455, 9456, 9457, 9458, 9459, 9460};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= a.length - 1; i++) {
            map.put(a[i], b[i]);
        }
        int[] c = {1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 13, 17, 18, 19, 20, 21, 22, 23, 2, 3, 24, 25, 26, 27, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 58, 59, 60, 61, 62, 63, 64, 65, 68, 69, 70, 74, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 98, 99, 100, 101, 102, 103, 104, 105, 106, 109, 110, 111, 112, 114, 115, 116, 117, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134};
        for (int i = 0; i <= c.length - 1; i++) {
            System.out.println(map.get(c[i]));
        }
    }
}