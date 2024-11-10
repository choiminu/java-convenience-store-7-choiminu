package store.utils.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileLoader {
    private static final int HEADER = 1;

    public static List<String> loadFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.lines().skip(HEADER).toList();
        } catch (IOException e) {
            throw new IllegalArgumentException("파일을 읽는 도중 에러가 발생 하였습니다.");
        }
    }
}
