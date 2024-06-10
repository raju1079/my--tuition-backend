package com.snipe.myTuitionCenter.data.dto;


import com.snipe.myTuitionCenter.data.entity.Institution;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorDTO {

	private static final long serialVersionUID = -5240832578839898476L;

	private String experience;
	private AddressDTO address;
	private TutorProfileDTO profileDTO;
	boolean is_individual;
	private Institution institution;

}
