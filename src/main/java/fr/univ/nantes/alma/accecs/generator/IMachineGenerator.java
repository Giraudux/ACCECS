package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.model.Machine;

import java.io.OutputStream;

/**
 *
 */
public interface IMachineGenerator {
    void generate(Machine machine, String templateLocation, OutputStream outputStream);
}
