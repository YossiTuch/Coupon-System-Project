package app.core.responses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CouponSystemResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        String timestampString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timestampString = timestamp.format(formatter);
        return timestampString;
    }

    @Override
    public String toString() {
        return
                "status=" + status +
                ", message='" + message + '\'' +
                ", timestamp=" + getTimestamp();
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


