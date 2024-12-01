import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date date = new Date(2005, 3, 27);
        String ID = "050327550234";
        System.out.println(String.valueOf(date.getYear()).substring(2,4)+String.format("%02d", date.getMonth())+String.format("%02d", date.getDate()));
        System.out.println(ID.substring(0, 6));
    }
}