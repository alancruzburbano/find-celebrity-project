package com.aacb.app.celebrity.service;

import com.aacb.app.celebrity.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReadFromFileTest {

    File csvOutputFile;

    @Autowired
    ReadFromFile readFromFile;

    @Before
    public void init(){
        readFromFile.setPeopleAmount(4);
    }

    @Test
    public void readItemsHappyNotSetCelebrityCase() {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]
                { "1 ", "0 ", "1 ", "0" });
        dataLines.add(new String[]
                { "0 ", "0 ", "1 ", "0" });
        dataLines.add(new String[]
                { "0 ", "0 ", "0 ", "0" });
        dataLines.add(new String[]
                { "0 ", "0 ", "1 ", "0" });
        try {
            convertDataInCSVFile(dataLines);
            readFromFile.readItems();
            Assert.assertEquals(-1, readFromFile.matrixProcessor.findCelebrity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertToCSV(String[] data){
        return Stream.of(data).collect(Collectors.joining(","));
    }

    public void convertDataInCSVFile(List<String[]> dataLines) throws IOException {
        csvOutputFile = new File(new File("").getAbsolutePath() + Constants.DEFAULT_MATRIX_FILE_PATH);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

}