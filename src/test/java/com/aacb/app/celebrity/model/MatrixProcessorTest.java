package com.aacb.app.celebrity.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MatrixProcessorTest {

    @Autowired
    MatrixProcessor matrixProcessor;

    @Test
    public void findCelebrityHappyPath() {
        List<int[]> matrix = new ArrayList<>();
        matrix.add(new int[] {0,1,0,0});
        matrix.add(new int[] {0,0,0,0});
        matrix.add(new int[] {0,1,0,0});
        matrix.add(new int[] {0,1,0,0});
        matrixProcessor.setPeopleAmount(4);
        matrixProcessor.setRowList(matrix);
        Assert.assertNotEquals(-1, matrixProcessor.findCelebrity());
    }

    @Test
    public void findCelebrityKnownByHimself() {
        List<int[]> matrix = new ArrayList<>();
        matrix.add(new int[] {0,1,0,0});
        matrix.add(new int[] {0,1,0,0});
        matrix.add(new int[] {0,1,0,0});
        matrix.add(new int[] {0,1,0,0});
        matrixProcessor.setPeopleAmount(4);
        matrixProcessor.setRowList(matrix);
        matrixProcessor.findCelebrity();
        Assert.assertNotEquals(-1, matrixProcessor.findCelebrity());
    }

}