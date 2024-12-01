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
    }
}
