package bg.sofia.uni.fmi.mjt.burnout.semester;

import bg.sofia.uni.fmi.mjt.burnout.exception.CryToStudentsDepartmentException;
import bg.sofia.uni.fmi.mjt.burnout.exception.InvalidSubjectRequirementsException;
import bg.sofia.uni.fmi.mjt.burnout.plan.SemesterPlan;
import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;
import bg.sofia.uni.fmi.mjt.burnout.util.SubjectSorter;

public final class ComputerScienceSemesterPlanner extends AbstractSemesterPlanner {
    @Override
    public UniversitySubject[] calculateSubjectList(SemesterPlan semesterPlan)
            throws InvalidSubjectRequirementsException {

        if (semesterPlan == null) {
            throw new IllegalArgumentException("Plan cannot be null");
        }

        validateRequirements(semesterPlan.subjectRequirements());

        UniversitySubject[] sorted = semesterPlan.subjects().clone();
        SubjectSorter.sortByRatingDescending(sorted);

        int credits = 0;
        int count = 0;

        for (UniversitySubject s : sorted) {
            credits += s.credits();
            count++;
            if (credits >= semesterPlan.minimalAmountOfCredits()) {
                UniversitySubject[] res = new UniversitySubject[count];
                System.arraycopy(sorted, 0, res, 0, count);
                return res;
            }
        }

        throw new CryToStudentsDepartmentException("Cannot cover semester credits");
    }
}
