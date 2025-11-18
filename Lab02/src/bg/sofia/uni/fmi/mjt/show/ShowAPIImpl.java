package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.util.Objects;

public class ShowAPIImpl implements ShowAPI {
    private Ergenka[] ergenkas;
    private final EliminationRule[] defaultEliminationRules;

    public ShowAPIImpl(Ergenka[] ergenkas, EliminationRule[] defaultEliminationRules) {
        this.ergenkas = ergenkas;
        this.defaultEliminationRules = defaultEliminationRules;
    }

    @Override
    public Ergenka[] getErgenkas() {
        if (ergenkas.length > 0) {
            return ergenkas;
        }
        return new Ergenka[0];

    }

    @Override
    public void playRound(DateEvent dateEvent) {
        for (Ergenka e : ergenkas) {
            e.reactToDate(dateEvent);
        }
    }

    @Override
    public void eliminateErgenkas(EliminationRule[] eliminationRules) {
        if (eliminationRules != null) {
            if (eliminationRules.length > 0) {
                for (EliminationRule er : eliminationRules) {
                    ergenkas = er.eliminateErgenkas(ergenkas);
                }
            } else {
                for (EliminationRule er : defaultEliminationRules) {
                    ergenkas = er.eliminateErgenkas(ergenkas);
                }
            }
        }
    }

    @Override
    public void organizeDate(Ergenka ergenka, DateEvent dateEvent) {
        for (Ergenka e : ergenkas) {
            if (Objects.equals(e.getName(), ergenka.getName())) {
                e.reactToDate(dateEvent);
            }
        }
    }
}
