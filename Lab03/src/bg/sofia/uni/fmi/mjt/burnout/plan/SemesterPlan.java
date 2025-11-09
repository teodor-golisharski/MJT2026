package bg.sofia.uni.fmi.mjt.burnout.plan;

import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public record SemesterPlan(UniversitySubject[] subjects, SubjectRequirement[] subjectRequirements,
                           int minimalAmountOfCredits) {
    public SemesterPlan {
        if (subjects == null) {
            throw new IllegalArgumentException("Subjects cannot be null");
        }

        if (subjectRequirements == null) {
            throw new IllegalArgumentException("Subject requirements cannot be null");
        }

        if (minimalAmountOfCredits < 0) {
            throw new IllegalArgumentException("Minimal amount credits must be positive");
        }
    }
}

