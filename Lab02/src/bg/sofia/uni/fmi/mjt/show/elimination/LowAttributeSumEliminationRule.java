package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowAttributeSumEliminationRule implements EliminationRule {

    private final int threshold;

    public LowAttributeSumEliminationRule(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        if (ergenkas == null) {
            return null;
        }
        if (ergenkas.length == 0) {
            return ergenkas;
        }

        int counter = 0;
        for (Ergenka e : ergenkas) {
            if (e != null) {
                if (e.getHumorLevel() + e.getRomanceLevel() < threshold) {
                    counter++;
                }
            }
        }

        if (ergenkas.length > 0) {
            Ergenka[] res = new Ergenka[ergenkas.length - counter];
            int index = 0;
            for (Ergenka e : ergenkas) {
                if (e != null) {
                    if (e.getHumorLevel() + e.getRomanceLevel() >= threshold) {
                        res[index++] = e;
                    }
                }
            }
            return res;
        }
        return ergenkas;
    }
}
