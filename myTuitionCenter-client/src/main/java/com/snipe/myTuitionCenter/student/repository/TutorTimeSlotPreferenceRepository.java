package com.snipe.myTuitionCenter.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.data.entity.TutorTimeSlotPreference;

@Repository
public interface TutorTimeSlotPreferenceRepository extends JpaRepository<TutorTimeSlotPreference,Long> {
	
	public TutorTimeSlotPreference findByTimeSlotId(long timeslotId);
}
