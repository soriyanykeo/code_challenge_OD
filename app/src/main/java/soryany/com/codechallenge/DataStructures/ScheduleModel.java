package soryany.com.codechallenge.DataStructures;

import java.util.Comparator;
/**
 * Created by soriyanykeo on 6/16/16.
 */

public class ScheduleModel implements Comparable<ScheduleModel>{
    public int id;
    public String idString;
    public int fromHour;
    public int untilHour;
    public String fromMin;
    public String untilMin;
    public String fromMode;
    public String untilMode;
    public String title;
    public int temp;
    public String tempMode;
    public boolean isOn;
    public String checksum;


    @Override
    public int compareTo(ScheduleModel o) {
        return Comparators.FromModeFromHour.compare(this, o);
    }
    public static class Comparators {

        public static Comparator<ScheduleModel> FromMode = new Comparator<ScheduleModel>() {
            @Override
            public int compare(ScheduleModel o1, ScheduleModel o2) {
                return o1.fromMode.compareTo(o2.fromMode);
            }
        };
        public static Comparator<ScheduleModel> FromHour = new Comparator<ScheduleModel>() {
            @Override
            public int compare(ScheduleModel o1, ScheduleModel o2) {
                return o1.fromHour - o2.fromHour;
            }
        };
        public static Comparator<ScheduleModel> FromModeFromHour = new Comparator<ScheduleModel>() {
            @Override
            public int compare(ScheduleModel o1, ScheduleModel o2) {
                int i = o1.fromMode.compareTo(o2.fromMode);
                if (i == 0) {
                    i = o1.fromHour - o2.fromHour;
                }
                return i;
            }
        };
    }
}
