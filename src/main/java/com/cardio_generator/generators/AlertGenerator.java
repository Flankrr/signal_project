package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * The AlertGenerator class generates simulated alert data for patients.
 * It implements the PatientDataGenerator interface and manages the alert state for each patient.
 * 
 * @see PatientDataGenerator
 */
public class AlertGenerator implements PatientDataGenerator {

    public static final Random RANDOM_GENERATOR = new Random();
    private boolean[] alertStates; // false = resolved, true = pressed

    /**
     * Constructs an AlertGenerator for the specified number of patients.
     *
     * @param patientCount The number of patients to simulate.
     */
    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    /**
     * Generates alert data for the specified patient and outputs it using the provided output strategy.
     * The method randomly triggers or resolves alerts based on predefined probabilities.
     *
     * @param patientId The ID of the patient.
     * @param outputStrategy The strategy used to output the generated data.
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                final double RESOLUTION_PROBABILITY = 0.9; // 90% chance to resolve
                if (RANDOM_GENERATOR.nextDouble() < RESOLUTION_PROBABILITY) {
                    alertStates[patientId] = false;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                final double lambda = 0.1; // Average rate (alerts per period)
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
