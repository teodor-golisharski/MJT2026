package bg.sofia.uni.fmi.mjt.burnout.subject;

import static bg.sofia.uni.fmi.mjt.burnout.subject.Constants.MAX_RATING;
import static bg.sofia.uni.fmi.mjt.burnout.subject.Constants.MIN_RATING;

public record UniversitySubject(String name, int credits, int rating, Category category, int neededStudyTime) {
    public UniversitySubject {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Subject name cannot be null or blank");
        }

        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }

        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (neededStudyTime <= 0) {
            throw new IllegalArgumentException("Needed study time must be positive");
        }
    }
}
