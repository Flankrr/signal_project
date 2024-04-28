package com.cardio_generator.generators;

import java.util.Random;
import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    // renamed constant to UPPER_SNAKE_CASE and added final keyword
    public static final Random RANDOM_GENERATOR = new Random();

    // renamed variable to lowerCamelCase
    private boolean[] alertStates; // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                // introduced named constants
                final double RESOLUTION_PROBABILITY = 0.9; // 90% chance to resolve
                if (RANDOM_GENERATOR.nextDouble() < RESOLUTION_PROBABILITY) {
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // renamed variable to lowerCamelCase
                final double lambda = 0.1; // Average rate (alerts per period)
                double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
                boolean alertTriggered = RANDOM_GENERATOR.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
