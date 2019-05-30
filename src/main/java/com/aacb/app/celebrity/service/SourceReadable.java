package com.aacb.app.celebrity.service;

/**
 * Contract with common abstraction to read information
 *
 * @author Alvaro Andres Cruz Burbano
 */
@FunctionalInterface
public interface SourceReadable {
    void readItems();
}