package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import bg.sofia.uni.fmi.mjt.burnout.subject.SubjectRequirement;
import bg.sofia.uni.fmi.mjt.burnout.util.SubjectSorter;

import java.util.ArrayList;
import java.util.Arrays;

public final class SoftwareEngineeringSemesterPlanner extends AbstractSemesterPlanner {
    @Override
    public UniversitySubject[] calculateSubjectList(SemesterPlan plan)
            throws InvalidSubjectRequirementsException {

        if (plan == null) {
            throw new IllegalArgumentException("Plan cannot be null");
        }

        validateRequirements(plan.subjectRequirements());

        ArrayList<UniversitySubject> result = new ArrayList<>();

        for (SubjectRequirement req : plan.subjectRequirements()) {

            ArrayList<UniversitySubject> categorySubjects = new ArrayList<>();

            for (UniversitySubject s : plan.subjects()) {
                if (s.category() == req.category()) {
                    categorySubjects.add(s);
                }
            }

            SubjectSorter.sortByCreditsDescending(categorySubjects);

            int toTake = req.minAmountEnrolled();
            for (int i = 0; i < toTake; i++) {
                result.add(categorySubjects.get(i));
            }
        }

        int credits = result.stream().mapToInt(UniversitySubject::credits).sum();
        if (credits >= plan.minimalAmountOfCredits()) {
            return result.toArray(new UniversitySubject[0]);
        }

        ArrayList<UniversitySubject> all = new ArrayList<>(Arrays.asList(plan.subjects()));
        all.removeAll(result);

        SubjectSorter.sortByCreditsDescending(all);

        for (int i = all.size() - 1; i >= 0; i--) {
            result.add(all.get(i));
            credits += all.get(i).credits();
            if (credits >= plan.minimalAmountOfCredits()) {
                return result.toArray(new UniversitySubject[0]);
            }
        }

        throw new CryToStudentsDepartmentException("Cannot cover semester credits");
    }
}
