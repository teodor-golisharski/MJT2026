package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowestRatingEliminationRule implements EliminationRule {
    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int minRating = Integer.MAX_VALUE;
        int countMinRating = 0;
        for (Ergenka e : ergenkas) {
            if (e.getRating() <= minRating) {
                minRating = e.getRating();
                countMinRating++;
            }
        }

        Ergenka[] res = new Ergenka[ergenkas.length - countMinRating];
        int index = 0;
        for (Ergenka e : ergenkas) {
            if (e.getRating() != minRating) {
                res[index++] = e;
            }
        }

        return res;
    }
}
