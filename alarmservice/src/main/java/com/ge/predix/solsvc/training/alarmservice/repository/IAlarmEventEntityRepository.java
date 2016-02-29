package com.ge.predix.solsvc.training.alarmservice.repository;

/** Copyright (c) 2013 GE Global Research. All rights reserved.
*
* The copyright to the computer software herein is the property of
* GE Global Research. The software may be used and/or copied only
* with the written permission of GE Global Research or in accordance
* with the terms and conditions stipulated in the agreement/contract
* under which the software has been supplied.
*/

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ge.predix.solsvc.training.alarmservice.entity.AlarmEventEntity;

/**
* This class models the spring-data repository for alarmevent entity. Apart form the standard operations supported by
* CRUD Repository, this class also supports customized named queries ,pagination, sorting and type safe queries using query-dsl.
* 
* @author 212350258
*/
@Repository
public interface IAlarmEventEntityRepository extends PagingAndSortingRepository<AlarmEventEntity, Integer>
{
	
	@SuppressWarnings("javadoc")
	String FIND_PATIENT_PRIORITY = "select a.priority, count(a) as number_of_alarms, a.patientId from AlarmEventEntity a group by a.priority, a.patientId"; //$NON-NLS-1$
	
	@SuppressWarnings("javadoc")
	String FIND_ALARM_BY_CLASSIFICATION =  "select a.alarm, count(a) as number_of_alarms, a.alarmClassification " + //$NON-NLS-1$
			" from AlarmEventEntity a where a.alarmClassification=?1" +   //$NON-NLS-1$
			" group by a.priority, a.alarm, a.alarmClassification"; //$NON-NLS-1$
	
   @SuppressWarnings("javadoc")
	String GET_EVENTS_BY_GUAGE = "select a.priority, count(a) as number_of_alarms, a.patientId " + //$NON-NLS-1$
   		" from AlarmEventEntity a group by a.priority, a.patientId"; //$NON-NLS-1$
	
	@SuppressWarnings("javadoc")
	String GET_EVENTS_BY_BAR = "select a.alarm, count(a) as number_of_alarms, a.alarmClassification " + //$NON-NLS-1$
			" from AlarmEventEntity a where a.alarm=?1 and a.alarmClassification=?2 " + //$NON-NLS-1$
			" group by a.priority, a.alarm, a.alarmClassification"; //$NON-NLS-1$
	
   /**
    * Get alarm events by guage
    */
   @SuppressWarnings("javadoc")
	@Query(GET_EVENTS_BY_GUAGE)
   List<Object []> getEventsByGague();
	
	/**
	 * Get alarm events by bar
	 */
   @SuppressWarnings("javadoc")
	@Query(GET_EVENTS_BY_BAR)
   List<Object []> getEventsByBar(String alarm, String alarmClarification);
   
   /**
    * Get all alarm events
    */
   @Override
	List<AlarmEventEntity> findAll();
   
	/**
    * Get alarms by classification. Propagation.REQUIRED is used
    * 
    * @param classification alarm classification
    * @return list of alarm, number of alarms and classification per priority, alarm and classification
    */
	@Query(FIND_ALARM_BY_CLASSIFICATION)
	List<Object []> getAlarmsByClassification(String classification);
	
	/**
    * Get patient priority. Propagation.REQUIRED is used
    * 
    * @return priority and number of alarms per priority and patient
    */
	@Query(FIND_PATIENT_PRIORITY)
   List<Object []> getPatientPriority();
	
	
}