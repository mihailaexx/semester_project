public class Mark {
    private Double mark;
    private Double first_attestation;
    private Double second_attestation;
    private Double final_exam;
    private String string_mark;

    public String int_to_string(double int_mark) {
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

    public Mark() {
        this.mark = null;
        this.first_attestation = null;
        this.second_attestation = null;
        this.final_exam = null;
        this.string_mark = null;
    }

    public Mark(double first_attestation, double second_attestation, double final_exam) {
        this.first_attestation = first_attestation;
        this.second_attestation = second_attestation;
        this.final_exam = final_exam;
        this.mark = (first_attestation + second_attestation + final_exam);
        this.string_mark = int_to_string(mark.intValue());
    }

    public Mark(double mark, int i) {
        updateMark(mark, i);
    }

    public void updateMark(double mark, int i) {
        switch (i) {
            case 1:
                this.first_attestation = mark;
                break;
            case 2:
                this.second_attestation = mark;
                break;
            case 3:
                this.final_exam = mark;
                break;
        }
        this.mark = (first_attestation + second_attestation + final_exam);
        this.string_mark = int_to_string(mark);
    }
}