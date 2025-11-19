package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowestRatingEliminationRule implements EliminationRule {
    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        if (ergenkas == null) {
            return null;
        }
        if (ergenkas.length == 0) {
            return ergenkas;
        }

        int minRating = Integer.MAX_VALUE;
        for (Ergenka e : ergenkas) {
            if (e != null && e.getRating() < minRating) {
                minRating = e.getRating();
            }
        }

        int countMinRating = 0;
        for (Ergenka e : ergenkas) {
            if (e == null || e.getRating() == minRating) {
                countMinRating++;
            }
        }

        if (ergenkas.length > 0) {
            Ergenka[] res = new Ergenka[ergenkas.length - countMinRating];
            int index = 0;
            for (Ergenka e : ergenkas) {
                if (e != null) {
                    if (e.getRating() != minRating) {
                        res[index++] = e;
                    }
                }
            }
            return res;
        }
        return ergenkas;
    }
}
