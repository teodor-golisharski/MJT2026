package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.DisappointmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

import java.util.HashSet;
import java.util.Set;

public abstract sealed class AbstractSemesterPlanner implements SemesterPlannerAPI
        permits SoftwareEngineeringSemesterPlanner, ComputerScienceSemesterPlanner {

    protected void validateRequirements(SubjectRequirement[] reqs)
            throws InvalidSubjectRequirementsException {

        Set<Category> categories = new HashSet<>();
        for (SubjectRequirement r : reqs) {
            if (!categories.add(r.category())) {
                throw new InvalidSubjectRequirementsException("Duplicate categories in requirements");
            }
        }
    }

    @Override
    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) {
        if (subjects == null || maximumSlackTime <= 0 || semesterDuration <= 0) {
            throw new IllegalArgumentException("Invalid jar count arguments");
        }

        int totalStudy = 0;
        int totalRest = 0;

        for (UniversitySubject s : subjects) {
            totalStudy += s.neededStudyTime();
            totalRest += (int) Math.ceil(s.neededStudyTime() * s.category().getRestCoefficient());
        }

        if (totalRest > maximumSlackTime) {
            throw new DisappointmentException("Grandma is disappointed.");
        }

        int jars = totalStudy / 5;
        if (totalStudy % 5 != 0) {
            jars++;
        }

        int totalNeeded = totalStudy + totalRest;
        if (totalNeeded > semesterDuration) {
            return jars * 2;
        }

        return jars;
    }
}
