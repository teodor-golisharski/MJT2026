package bg.sofia.uni.fmi.mjt.burnout.util;

import bg.sofia.uni.fmi.mjt.burnout.subject.UniversitySubject;

import java.util.ArrayList;

public final class SubjectSorter {

    private SubjectSorter() {
    }

    public static void sortByCreditsDescending(ArrayList<UniversitySubject> subjects) {
        for (int i = 0; i < subjects.size() - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < subjects.size(); j++) {
                if (subjects.get(j).credits() > subjects.get(maxIndex).credits()) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                UniversitySubject temp = subjects.get(i);
                subjects.set(i, subjects.get(maxIndex));
                subjects.set(maxIndex, temp);
            }
        }
    }

    public static void sortByRatingDescending(UniversitySubject[] subjects) {
        for (int i = 0; i < subjects.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < subjects.length; j++) {
                if (subjects[j].rating() > subjects[maxIndex].rating()) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                UniversitySubject temp = subjects[i];
                subjects[i] = subjects[maxIndex];
                subjects[maxIndex] = temp;
            }
        }
    }

    public static void sortByStudyTimeAscending(ArrayList<UniversitySubject> subjects) {
        for (int i = 0; i < subjects.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < subjects.size(); j++) {
                if (subjects.get(j).neededStudyTime() < subjects.get(minIndex).neededStudyTime()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                UniversitySubject temp = subjects.get(i);
                subjects.set(i, subjects.get(minIndex));
                subjects.set(minIndex, temp);
            }
        }
    }
}