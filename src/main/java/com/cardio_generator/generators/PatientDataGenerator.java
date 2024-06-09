package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * The PatientDataGenerator interface provides a contract for generating patient data.
 * Implementations of this interface are responsible for generating specific types of data
 * for a given patient and outputting it using the specified output strategy.
 * 
 * @see OutputStrategy
 */
public interface PatientDataGenerator {

    /**
     * Generates patient data for the specified patient ID and outputs it using the provided output strategy.
     *
     * @param patientId The ID of the patient for whom data is to be generated.
     * @param outputStrategy The strategy used to output the generated data.
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}
