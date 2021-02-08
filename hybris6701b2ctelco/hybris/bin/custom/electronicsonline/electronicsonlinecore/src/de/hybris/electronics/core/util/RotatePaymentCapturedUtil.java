package de.hybris.electronics.core.util;

/**
 * Rotate Payment Captured Utils
 */
public class RotatePaymentCapturedUtil {
    private static int count = 0;

    /**
     * We will rotate return the success flag
     *
     * @return
     */
    public static boolean rotateCaptured() {
        count++;
        return count % 2 == 0;
    }
}
