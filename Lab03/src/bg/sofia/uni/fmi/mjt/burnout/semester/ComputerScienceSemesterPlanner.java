package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.Category;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import bg.sofia.uni.fmi.mjt.burnout.util.SubjectSorter;

import java.util.ArrayList;
import java.util.List;

public final class ComputerScienceSemesterPlanner implements SemesterPlannerAPI {
    @Override
    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan)
            throws InvalidSubjectRequirementsException {

        SubjectSorter sorter = null;
        UniversitySubject[] universitySubjects = semesterPlan.subjects();
        SubjectSorter.sortByRatingDescending(universitySubjects);

        List<UniversitySubject> result = new ArrayList<>();

        SubjectRequirement[] requirements = semesterPlan.subjectRequirements();
        for (var r : requirements) {
            int minAmount = r.minAmountEnrolled();

            for (var subject : universitySubjects) {
                if (r.category() == subject.category()) {
                    result.add(subject);

                }
            }

        }

        return new UniversitySubject[0];
    }

    @Override
    public int calculateJarCount(UniversitySubject[] subjects, int maximumSlackTime, int semesterDuration) {
        return 0;
    }
}
