package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowAttributeSumEliminationRule implements EliminationRule {

    private final int threshold;

    public LowAttributeSumEliminationRule(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int counter = 0;
        for (Ergenka e : ergenkas) {
            if (e.getHumorLevel() + e.getRomanceLevel() < threshold) {
                counter++;
            }
        }

        Ergenka[] res = new Ergenka[ergenkas.length - counter];
        int index = 0;
        for (Ergenka e : ergenkas) {
            if (e.getHumorLevel() + e.getRomanceLevel() >= threshold) {
                res[index++] = e;
            }
        }

        return  res;
    }
}
