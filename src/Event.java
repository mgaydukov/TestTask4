public class Event {
    private final int senderId;
    private final String type;
    private final Object data;

    public Event(int senderId, String type, Object data) {
        this.senderId = senderId;
        this.type = type;
        this.data = data;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
