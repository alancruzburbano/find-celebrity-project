package com.aacb.app.celebrity.service;

import com.aacb.app.celebrity.model.MatrixProcessor;
import com.aacb.app.celebrity.utils.Constants;
import com.aacb.app.celebrity.utils.TextVerifiable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This is the concrete implementation of SourceReadable for
 * file method of lecture, this class reads the application.properties file
 * and perform the creation of matrix data validating binary values 0 or 1
 *
 * @author Alvaro Andres Cruz Burbano
 */
@Slf4j
@Service
public class ReadFromFile implements SourceReadable {

    @Autowired
    MatrixProcessor matrixProcessor;

    @Value("${app.celebrity.list.file.token.separator}")
    private String tokenSeparator;

    @Value("${app.celebrity.list.file.people.amount}")
    private int peopleAmount;

    //Verifies if value is 0 or 1
    TextVerifiable verifyBinary = (text) -> {
        String expression = Constants.VALIDATE_BINARY_REGULAR_EXPRESSION;
        return text.matches(expression);
    };

    /**
     *Read data, executes the algorithm to find celebrity and prints the result
     */
    @Override
    public void readItems() {
        try {
            List<String> linesFromFile = Files.readAllLines(Paths.get(readPath(Constants.DEFAULT_MATRIX_FILE_PATH)));
            List<int[]> rowList = new ArrayList<>();
            for (int i = 0; i < linesFromFile.size(); i++) {
                rowList.add(buildArrayFromTokens(linesFromFile.get(i), i));
            }
            matrixProcessor.setRowList(rowList);
            log.info("\n"+ createMessageForResult(matrixProcessor.findCelebrity()));
        } catch (IOException e) {
            log.error("The system cannot read the file message: {}", e.getLocalizedMessage());
        }
    }

    private String createMessageForResult(int result) {
        String message;
        if (result == -1) {
            message = "Celebrity Not Found!";
        } else {
            try {
                String celebrityName = Files.readAllLines(Paths.get(readPath(Constants.DEFAULT_NAMES_FILE_PATH))).get(result);
                message = "Celebrity found in position: " + (result + 1) + ", name: " + celebrityName;
            } catch (IOException e) {
                message = "Something were wrong: " + e.getLocalizedMessage();
                log.error("The system cannot read the file name in source: {}", e.getLocalizedMessage());
            }
        }
        return message;
    }

    /**
     * This method Iterates tokens, searching for a url token, assuming that previous tokens are part of contact name,
     * This is because there are contact registers using ',' as part of the name.
     *
     * @param line       String tokenizer object that will be used to create a contact object
     * @param lineNumber String tokenizer object that will be used to create a contact object
     * @return Contact object from tokens
     */
    private int[] buildArrayFromTokens(String line, int lineNumber) {
        StringTokenizer st = new StringTokenizer(line, tokenSeparator);
        String binaryToken;
        int index = 0;
            int[] rowValues = new int[peopleAmount];
            while (st.hasMoreTokens()) {
                binaryToken = st.nextToken().trim();
                if (verifyBinary.verify(binaryToken)) {
                    rowValues[index] = Integer.valueOf(binaryToken);
                } else {
                    log.error("The reader found a not valid character in the data: {}, line number {}", binaryToken, lineNumber);
                }
                index++;
            }
            return rowValues;
    }

    /**
     * The method verifies if the file in pathFile property exist in the system,
     * else the systems will use the CSV file inside the project as a default alternative
     *
     * @return Custom path or default path.
     */
    private String readPath(String filePath) {
        File directory = new File(""); //Retrieve the root project path
        log.info("Using default file in project folder: {}", directory.getAbsolutePath() + filePath);
        return directory.getAbsolutePath() + filePath;
    }

}
