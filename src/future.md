1. Finalize core classes (add all functionalities): Person, User, Employee, Teacher, Manager, Admin, Student
2. Authentication:
   Implement a login system where a User (Student, Teacher, Manager, etc.) can authenticate with ID and password.
   how?: Create a LoginManager that verifies credentials and returns a User object if successful
3. Course Registration & Enrollment logic:
   improve the logic in OrManager:
   a) store Request objects in queue that OrManager can approve or deny
   b) Update Student.registerForCourse to throw exceptions if rules are broken, and handle these in OrManager.
4. Marks, Grades & Transcript:
   a) Ensure that Teacher can update student marks ONLY for THEIR assigned courses.
   b) Add logic for final GPA calculation and transcript generation?
   c) Validate grading ranges and handle exceptions for invalid marks. 
   How?: 
   Add methods in Course to ensure that only enrolled students and authorized teachers can have their marks updated.
   Possibly maintain enrolledStudents in each Course, so that a Teacher can check if a student is enrolled before updating marks.
5. Research Functionality
    a) finish?
6. Messaging & Communication
    a) Maybe implement MessageService class to handle all messaging logic in a centralized manner.
7. News, Announcements & Logs
    a) News Management:
        i. OrManager or Admin can add, remove news.
        ii. University should store a list or map of news items.
        iii. Add methods to University to addNews, removeNews, pinNews, etc.
    b) Logs:
        i. Whenever critical actions are taken (e.g., course added, user logged in, marks updated), we should log them in University.log.
        ii. implement a simple logger? (print after method) or save them in file
8. Data Storage:
    a) Serialization:
       i. maybe implement serialization to save and load the state of the system (University, School, Students, Courses) to a file.
       ii. or we shoul consider using Java’s built-in serialization or external libraries (JSON, XML).
9. Design Patterns:
   Decorator: For Researcher or Teacher roles if we want to dynamically add research capabilities 
to a Teacher who is a Researcher (PROFESSOR - researcher, 4th year have advisor, etc.)
10. Build a simple interface (CLI or GUI) to interact with the system.


FOR NOW:
Finalize all classes (e.g., ensure Teacher, OrManager, FinanceManager, Student, Admin have complete, working logic).
Add search, filter, sort methods in University and managers.