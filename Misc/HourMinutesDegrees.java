/**
 * Created by kevin on 6/16/2016.
 */
public class HourMinutesDegrees {
    public static void main(String[] args) {
        solve(12, 0);
        solve(12, 30);
        solve(4, 30);
    }

    public static void solve(int hour, int minutes) {
        float hourDegrees = (30f * hour) + ((minutes / 60f) * 30f);
        float minutesDegrees = (6f * minutes);

        float degrees = Math.abs(hourDegrees - minutesDegrees);
        System.out.println(Math.min(360 - degrees, degrees));
    }
}
