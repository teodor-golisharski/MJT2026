package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class PublicVoteEliminationRule implements EliminationRule {

    private final String[] votes;

    public PublicVoteEliminationRule(String[] votes) {
        this.votes = votes;
    }

    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        if (ergenkas == null) {
            return null;
        }
        if (ergenkas.length == 0) {
            return ergenkas;
        }
        if (votes == null || votes.length == 0) {
            return ergenkas;
        }

        int[] counts = new int[ergenkas.length];

        for (String vote : votes) {
            for (int i = 0; i < ergenkas.length; i++) {
                if (ergenkas[i] != null && ergenkas[i].getName() != null &&
                        ergenkas[i].getName().equals(vote)) {
                    counts[i]++;
                }
            }
        }

        int majority = votes.length / 2 + 1;
        int eliminateIndex = -1;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= majority) {
                eliminateIndex = i;
                break;
            }
        }

        if (eliminateIndex == -1) {
            return ergenkas;
        }

        Ergenka[] res = new Ergenka[ergenkas.length - 1];
        int index = 0;
        for (int i = 0; i < ergenkas.length; i++) {
            if (i != eliminateIndex) {
                res[index++] = ergenkas[i];
            }
        }

        return res;
    }
}
