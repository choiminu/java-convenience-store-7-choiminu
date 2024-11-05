package store.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileReadHelperTest {

    private FileReadHelper fileReadHelper;

    @TempDir
    Path tempDir;

    private Path sampleFile;

    @BeforeEach
    void setUp() throws IOException {
        fileReadHelper = new FileReadHelper();

        sampleFile = tempDir.resolve("sample.csv");
        try (FileWriter writer = new FileWriter(sampleFile.toFile())) {
            writer.write("Header\n");
            writer.write("Line 1\n");
            writer.write("Line 2\n");
            writer.write("Line 3\n");
        }
    }

    @Test
    void 파일에서_헤더를_제외한_라인들을_읽어들인다() throws IOException {
        // given
        List<String> lines = fileReadHelper.readLines(sampleFile.toString());

        // when then
        assertEquals(3, lines.size());
        assertEquals("Line 1", lines.get(0));
        assertEquals("Line 2", lines.get(1));
        assertEquals("Line 3", lines.get(2));
    }

    @Test
    void 빈_파일을_읽으면_빈_리스트를_반환한다() throws IOException {
        // given
        Path emptyFile = tempDir.resolve("empty.csv");
        new File(emptyFile.toString()).createNewFile();

        // when
        List<String> lines = fileReadHelper.readLines(emptyFile.toString());

        // then
        assertTrue(lines.isEmpty());
    }

}