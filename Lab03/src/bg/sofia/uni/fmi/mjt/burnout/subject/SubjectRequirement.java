package bg.sofia.uni.fmi.mjt.burnout.subject;

public record SubjectRequirement(Category category, int minAmountEnrolled) {
    public SubjectRequirement {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if (minAmountEnrolled < 0) {
            throw new IllegalArgumentException("Minimum amount for enrollment must be positive");
        }
    }
}
