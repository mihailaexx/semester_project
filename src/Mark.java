public class Mark {
    private int int_mark;
    private String string_mark;

    public String int_to_string(int int_mark) {
        if (int_mark < 0 || int_mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100");
        }
        if (int_mark < 50) return "F";
        else if (int_mark < 55) return "D-";
        else if (int_mark < 60) return "D+";
        else if (int_mark < 65) return "C-";
        else if (int_mark < 70) return "C";
        else if (int_mark < 75) return "C+";
        else if (int_mark < 80) return "B-";
        else if (int_mark < 85) return "B";
        else if (int_mark < 90) return "B+";
        else if (int_mark < 95) return "A-";
        else return "A";
    }

    public Mark(int int_mark) {
        this.int_mark = int_mark;
        this.string_mark = int_to_string(int_mark);
    }
}