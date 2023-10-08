import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeConversionExample {
    public static void main(String[] args) {
        
        ZoneId targetTimeZone = ZoneId.of("Europe/Paris"); 

        ZonedDateTime currentTimeInTargetZone = ZonedDateTime.now(targetTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTimeInTargetZone.format(formatter);
        System.out.println("Current time in " + targetTimeZone + ": " + formattedTime);
    }
}
