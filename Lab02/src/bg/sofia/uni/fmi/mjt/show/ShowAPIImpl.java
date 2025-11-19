package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.elimination.LowestRatingEliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class ShowAPIImpl implements ShowAPI {
    private Ergenka[] ergenkas;
    private final EliminationRule[] defaultEliminationRules;

    public ShowAPIImpl(Ergenka[] ergenkas, EliminationRule[] defaultEliminationRules) {
        this.ergenkas = ergenkas;
        if (defaultEliminationRules == null || defaultEliminationRules.length == 0) {
            this.defaultEliminationRules = new EliminationRule[]{new LowestRatingEliminationRule()};
        } else {
            this.defaultEliminationRules = defaultEliminationRules;
        }
    }

    @Override
    public Ergenka[] getErgenkas() {
        return ergenkas;
    }

    @Override
    public void playRound(DateEvent dateEvent) {
        if (ergenkas == null) {
            return;
        }
        for (Ergenka e : ergenkas) {
            if (e != null) {
                e.reactToDate(dateEvent);
            }
        }
    }

    @Override
    public void eliminateErgenkas(EliminationRule[] eliminationRules) {
        if (ergenkas == null) {
            return;
        }

        if (eliminationRules == null || eliminationRules.length == 0) {
            eliminationRules = defaultEliminationRules;
            if (eliminationRules == null || eliminationRules.length == 0) {
                eliminationRules = new EliminationRule[]{new LowestRatingEliminationRule()};
            }
        }

        for (EliminationRule rule : eliminationRules) {
            if (rule != null) {
                ergenkas = rule.eliminateErgenkas(ergenkas);
            }
        }
    }

    @Override
    public void organizeDate(Ergenka ergenka, DateEvent dateEvent) {
        if (ergenkas == null) {
            return;
        }

        for (Ergenka e : ergenkas) {
            if (e != null && e.getName().equals(ergenka.getName())) {
                e.reactToDate(dateEvent);
            }
        }
    }
}
