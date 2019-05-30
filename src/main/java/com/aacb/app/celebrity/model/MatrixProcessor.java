package com.aacb.app.celebrity.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class MatrixProcessor {

    private List<int[]> rowList;

    @Value("${app.celebrity.list.file.people.amount}")
    private int peopleAmount;

    public int findCelebrity() {
        // Initializing two pointers
        int pointerA = 0;
        int pointerB = peopleAmount - 1;

        // Keep moving while the two pointers don't become same.
        while (pointerA < pointerB) {
            if (isBKnownByA(pointerA, pointerB))
                pointerA++;
            else
                pointerB--;
        }

        // Check if pointerA is actually pointerA celebrity or not
        for (int i = 0; i < peopleAmount; i++) {
            // If any person doesn't know 'pointerA' or 'pointerA' doesn't know any person, return -1
            if (i != pointerA && (isBKnownByA(pointerA, i) ||
                    !isBKnownByA(i, pointerA)))
                return -1;
        }
        return pointerA;
    }

    private boolean isBKnownByA(int personA, int personB) {
        return (rowList.get(personA)[personB] == 1) ? true : false;
    }

}