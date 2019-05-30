package com.aacb.app.celebrity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This Class implements a simple contract
 * Is a service that  returns a SourceReadable (contract)
 * that could be from file or database
 *
 * @author Alvaro Andres Cruz Burbano
 */

@Component
public class ReadMatrixDataSet implements CommandLineRunner {

    @Autowired
    SourceReadable readFromFile;

    @Override
    public void run(String... args) {
        readFromFile.readItems();
    }

}
