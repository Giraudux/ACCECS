package fr.univ.nantes.alma.accecs.generator;


import fr.univ.nantes.alma.accecs.model.Machine;

import java.io.File;
import java.io.OutputStream;

/**
 *Interface of the IMachineGenerator
 */
public interface IMachineGenerator {
    void generate(Machine machine, File templateFile, OutputStream outputStream);
}
