package com.cardio_generator.outputs;

/**
 * The OutputStrategy interface defines a strategy for outputting patient data.
 * Implementations of this interface are responsible for defining how the data
 * for a given patient should be outputted.
 */
public interface OutputStrategy {

    /**
     * Outputs patient data with the specified parameters.
     *
     * @param patientId The ID of the patient.
     * @param timestamp The timestamp of the data.
     * @param label The label describing the type of data.
     * @param data The actual data to be outputted.
     */
    void output(int patientId, long timestamp, String label, String data);
}
