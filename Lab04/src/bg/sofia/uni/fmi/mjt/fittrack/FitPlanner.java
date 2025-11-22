package bg.sofia.uni.fmi.mjt.fittrack;

import bg.sofia.uni.fmi.mjt.fittrack.exception.OptimalPlanImpossibleException;
import bg.sofia.uni.fmi.mjt.fittrack.utils.Constants;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.WorkoutFilter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FitPlanner implements FitPlannerAPI {

    private final Set<Workout> availableWorkouts;

    public FitPlanner(Collection<Workout> availableWorkouts) {
        if (availableWorkouts == null) {
            throw new IllegalArgumentException(Constants.NULL_WORKOUTS);
        }
        this.availableWorkouts = new HashSet<>(availableWorkouts);
    }

    @Override
    public List<Workout> findWorkoutsByFilters(List<WorkoutFilter> filters) {
        if (filters == null) {
            throw new IllegalArgumentException(Constants.NULL_WORKOUTS);
        }
        List<Workout> results = new ArrayList<>();
        for (Workout w : availableWorkouts) {
            boolean matches = true;

            for (WorkoutFilter filter : filters) {
                if (!filter.matches(w)) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                results.add(w);
            }
        }
        return Collections.unmodifiableList(results);
    }

    private List<Workout> knapsackSelect(List<Workout> w, int totalMinutes) {
        int n = w.size();
        int[][] dp = new int[n + 1][totalMinutes + 1];

        for (int i = 1; i <= n; i++) {
            Workout cur = w.get(i - 1);
            int dur = cur.getDuration();
            int cal = cur.getCaloriesBurned();

            for (int t = 0; t <= totalMinutes; t++) {
                dp[i][t] = (dur <= t) ? Math.max(dp[i - 1][t], dp[i - 1][t - dur] + cal) : dp[i - 1][t];
            }
        }

        List<Workout> result = new ArrayList<>();
        for (int i = n, t = totalMinutes; i > 0; i--)
            if (dp[i][t] != dp[i - 1][t]) {
                result.add(w.get(i - 1));
                t -= w.get(i - 1).getDuration();
            }

        return result;
    }

    private boolean canFitAnyWorkout(List<Workout> w, int totalMinutes) {
        for (Workout ww : w) if (ww.getDuration() <= totalMinutes) return true;
        return false;
    }

    @Override
    public List<Workout> generateOptimalWeeklyPlan(int totalMinutes)
            throws OptimalPlanImpossibleException {
        if (totalMinutes < 0) throw new IllegalArgumentException(Constants.INVALID_TOTAL_MINUTES);
        if (totalMinutes == 0) return Collections.emptyList();

        List<Workout> w = new ArrayList<>(availableWorkouts);
        if (!canFitAnyWorkout(w, totalMinutes))
            throw new OptimalPlanImpossibleException(Constants.NO_OPTIMAL_PLAN);

        List<Workout> chosen = knapsackSelect(w, totalMinutes);
        chosen.sort((a, b) -> {
            int c = Integer.compare(b.getCaloriesBurned(), a.getCaloriesBurned());
            return c != 0 ? c : Integer.compare(b.getDifficulty(), a.getDifficulty());
        });

        return Collections.unmodifiableList(chosen);
    }

    @Override
    public Map<WorkoutType, List<Workout>> getWorkoutsGroupedByType() {
        Map<WorkoutType, List<Workout>> map = new HashMap<>();

        for (Workout w : availableWorkouts) {
            if (!map.containsKey(w.getType())) {
                map.put(w.getType(), new ArrayList<>());
            }
            map.get(w.getType()).add(w);
        }
        map.replaceAll((t, v) -> Collections.unmodifiableList(map.get(t)));

        return Collections.unmodifiableMap(map);
    }

    @Override
    public List<Workout> getWorkoutsSortedByCalories() {
        List<Workout> list = new ArrayList<>(availableWorkouts);

        list.sort((a, b) -> Integer.compare(a.getCaloriesBurned(), b.getCaloriesBurned()));

        return Collections.unmodifiableList(list);
    }

    @Override
    public List<Workout> getWorkoutsSortedByDifficulty() {
        List<Workout> list = new ArrayList<>(availableWorkouts);

        list.sort((a, b) -> Integer.compare(a.getDifficulty(), b.getDifficulty()));

        return Collections.unmodifiableList(list);
    }

    @Override
    public Set<Workout> getUnmodifiableWorkoutSet() {
        return Collections.unmodifiableSet(availableWorkouts);
    }
}
