package store.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileReadHelper {
    private static final int HEADER = 1;

    public static List<String> readLines(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.lines().skip(HEADER).toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("에러 발생");
        }
    }
}
