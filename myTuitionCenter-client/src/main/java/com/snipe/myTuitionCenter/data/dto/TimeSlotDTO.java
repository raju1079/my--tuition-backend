package com.snipe.myTuitionCenter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDTO {
	
	private long timeSlotId;
	private String startTime;
	private String endTime;
	private String daysOfWeek;

}
