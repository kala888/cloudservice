package scd.core.quartz;

import lombok.Data;

/**
 * The persistent class for the acs_carrier database table.
 *
 */
@Data
public class ScheduleJob  {
    private String            id;
    private String            name;
    private String            group;
    private String            objectName;
    private String            schedule;
    private String            active;
}