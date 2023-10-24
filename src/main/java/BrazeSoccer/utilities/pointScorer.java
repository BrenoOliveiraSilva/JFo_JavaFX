package BrazeSoccer.utilities;

import java.util.Random;

public class pointScorer {
    public static int pointAttempts(int chance) {
        Random random = new Random();

        return random.nextInt(5 + chance);
    }
    public static int pointsScored(int chance) {
        Random random = new Random();
        int pointAttempts = pointAttempts(chance);
        int pointsScored = 0;

        for (int i = 0; i <= pointAttempts; i++) {
            int kick = random.nextInt(100) + 1;
            if (kick >= 75) {
                pointsScored++;
            }
        }
        return pointsScored;
    }
}
