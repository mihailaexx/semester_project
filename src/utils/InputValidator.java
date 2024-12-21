//package utils;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.regex.Pattern;
//
//public class InputValidator {
//
//    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
//    private static final String PHONE_NUMBER_REGEX = "^\\d{10,15}$"; // Adjust regex as needed
//    private static final String DATE_FORMAT = "yyyy-MM-dd";
//
//    public static boolean isValidEmail(String email) {
//        return Pattern.matches(EMAIL_REGEX, email);
//    }
//
//    public static boolean isValidPhoneNumber(String phoneNumber) {
//        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
//    }
//
//    public static boolean isValidString(String str, int minLength, int maxLength) {
//        return str != null && str.length() >= minLength && str.length() <= maxLength;
//    }
//
//    public static Date parseDate(String dateString) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
//        dateFormat.setLenient(false); // To enforce strict date format
//        return dateFormat.parse(dateString);
//    }
//
//    // Add other validation methods as needed:
//    // - validateYearOfStudy(int year)
//    // - validatePasswordComplexity(String password)
//    // - etc.
//}