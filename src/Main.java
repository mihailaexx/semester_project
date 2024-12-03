import java.util.Date;

public class Main {
    public static void main(String[] args) {
        University KBTU = new University("Kazakh-British Technical University");

        School GEOLOGY = new School("School of Geology", KBTU);
        School SEPI = new School("School of Energy, Oil and Gas", KBTU);
        School SITE = new School("School of IT and Engineering", KBTU);
        School NSS = new School("School of Natural and Social Sciences", KBTU);
        School BS = new School("Business School", KBTU);
        School ISE = new School("International School of Economics", KBTU);
        School KMA = new School("Kazakh Maritime Academy", KBTU);
        School SAM = new School("School of Applied Mathematics", KBTU);
        School SCE = new School("School of Chemical Engineering", KBTU);
        School SMGT = new School("School of Materials Science and Green Technologies", KBTU);

        Government rector = new Government("710101450234", "Maratbek", "Gabdullin", SEX.male, new Date(1971, 1, 1), "+77771234567", "Kazakhstan", "12345678", 10000000, GOVROLE.RECTOR);
        KBTU.addRector(rector);
        Teacher teacher1 = new Teacher("950303450234", "Azamat", "Imanbaev", SEX.male, new Date(1980, 3, 3), "+77771234567", "Kazakhstan", "12345678", 1111111, SITE, TEACHERDEGREE.ASSISTANT_PROFESSOR);
        SITE.addDean(teacher1);
        Student student1 = new Student("050327550234", "Mikhail", "Bulushev", SEX.male, new Date(2005, 3, 27), "+77078547658", "Kazakhstan", "12345678", "23B031079", SITE, STUDENTDEGREE.BACHELOR, 2);
        Researcher researcher1 = new Researcher("930202450234", "Alexander", "Mezin", SEX.male, new Date(1993, 2, 2), "+77771234567", "Kazakhstan", "12345678", 1000000, RESEARCHERDEGREE.SENIOR);

        Discipline pp1 = new Discipline("CSCI1103", "Programming Principles 1", SITE, 6, 2, 0, 2, 2023, 1);
        Discipline math1 = new Discipline("MATH1102", "Calculus 1", SITE, 5, 2, 0, 2, 2023, 1);
        Discipline discrete = new Discipline("CSCI1102", "Discrete Structures", SAM, 5, 2, 0, 1, 2023, 1);
        Discipline engA2 = new Discipline("LAN1182", "English Pre-Intermediate (A2)", NSS, 10, 0, 0, 3, 2023, 1);

        Schedule schedule1 = new Schedule();
        schedule1.addDiscipline(pp1, DISCIPLINETYPE.LECTURE, 0, 10); // 10 - 12 lecture
        schedule1.addDiscipline(pp1, DISCIPLINETYPE.LECTURE, 0, 11);
        schedule1.addDiscipline(pp1, DISCIPLINETYPE.LAB, 0, 14); // 14 - 16 lab
        schedule1.addDiscipline(pp1, DISCIPLINETYPE.LAB, 0, 15);
        schedule1.addDiscipline(math1, DISCIPLINETYPE.LAB, 1, 12); // 12 - 14 lab
        schedule1.addDiscipline(math1, DISCIPLINETYPE.LAB, 1, 13);
        schedule1.addDiscipline(math1, DISCIPLINETYPE.LECTURE, 6, 8); // 8 - 10 lecture
        schedule1.addDiscipline(math1, DISCIPLINETYPE.LECTURE, 6, 9);
        schedule1.addDiscipline(discrete, DISCIPLINETYPE.LECTURE, 2, 10); // 10 - 12 lecture
        schedule1.addDiscipline(discrete, DISCIPLINETYPE.LECTURE, 2, 11);
        schedule1.addDiscipline(discrete, DISCIPLINETYPE.LAB, 3, 14); // 14 - 15 lab
        // Eng every mon, wed, fri 9 - 10 lab
        schedule1.addDiscipline(engA2, DISCIPLINETYPE.LAB, 0, 9);
        schedule1.addDiscipline(engA2, DISCIPLINETYPE.LAB, 2, 9);
        schedule1.addDiscipline(engA2, DISCIPLINETYPE.LAB, 4, 9);
        Course course1 = new Course(2023, 1);
    }
}
//
// TODO: Оценки, расписание, курс
// TODO: Админка, привелегии (несколько уровней), лог
// TODO: Регистрация на курс студента - запрос для менержера
// TODO: Перенести enum'ы в отдельные файлы