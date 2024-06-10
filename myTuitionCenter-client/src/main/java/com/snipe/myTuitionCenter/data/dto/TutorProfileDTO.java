package com.snipe.myTuitionCenter.data.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorProfileDTO implements Serializable {
	
	private static final long serialVersionUID = 2090319302352885578L;
	
	private String educational_background;
	private String certification;
	private List<String> subjects;
}
