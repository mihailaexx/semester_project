public class Request {
    private REQUESTSTATUS status;
    private String message;

}

enum REQUESTSTATUS {
    NEW, VIEWED, ACCEPTED, REJECTED;
}