package bg.sofia.uni.fmi.mjt.burnout.plan;

import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

public record SemesterPlan(UniversitySubject[] subjects, SubjectRequirement[] subjectRequirements, int minimalAmountOfCredits) {

}

