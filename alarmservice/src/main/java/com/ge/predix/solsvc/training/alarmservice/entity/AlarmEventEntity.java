package com.ge.predix.solsvc.training.alarmservice.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ge.predix.solsvc.training.alarmservice.dto.AlarmEvent;
import com.ge.predix.solsvc.training.alarmservice.dto.Patient;




@Entity
@Table(name = "ALARM_EVENT")
public class AlarmEventEntity
        implements Serializable
{
    /**
     * 
     */
    private static final long                                                             serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "EVENT_ID") private long                                           eventId;
    @Column(name = "ALARM") private String                                                alarm;
    @Column(name = "ALARM_CLASSIFICATION") private String                                 alarmClassification;
    @Column(name = "PATIENT_ID") private long                                             patientId;
    @Column(name = "PRIORITY") private int                                                priority;
    @Transient @Column(name = "NUMBER_OF_ALARMS") private int                             numberOfAlarms;

    //the fetch type of PatientEntity and AlarmEventEntity cannot be both EAGER, which will cause infinity loop.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH) @JoinColumn(name = "PATIENT_ID", referencedColumnName = "PATIENT_ID", insertable = false, updatable = false) private PatientEntity patient;

    /**
     * @return the numberOfAlarms
     */
    public int getNumberOfAlarms()
    {
        return this.numberOfAlarms;
    }

    /**
     * @param numberOfAlarms
     *            the numberOfAlarms to set
     */
    public void setNumberOfAlarms(int numberOfAlarms)
    {
        this.numberOfAlarms = numberOfAlarms;
    }

    /**
     * 
     */

    public AlarmEventEntity()
    {
    }

    /**
     * @param alarm
     *            as input
     * @param classification
     *            as input
     * @param patientId
     *            as input
     * @param eventId
     *            as input
     * @param priority
     *            as input
     */
    public AlarmEventEntity(String alarm, String classification, long patientId, long eventId, int priority)
    {
        super();
        this.alarm = alarm;
        this.alarmClassification = classification;
        this.patientId = patientId;
        this.eventId = eventId;
        this.priority = priority;
    }
    //event_id, patient_id, alarm, alarm_classification, priority
    public AlarmEventEntity(long patientId, String alarm, String classification,int priority)
    {
        super();
        this.alarm = alarm;
        this.alarmClassification = classification;
        this.patientId = patientId;
        this.priority = priority;
    }
    /**
     * @return the alarm
     */
    public String getAlarm()
    {
        return this.alarm;
    }

    /**
     * @param alarm
     *            the alarm to set
     */
    public void setAlarm(String alarm)
    {
        this.alarm = alarm;
    }

    /**
     * @return the patientId
     */
    public long getPatientId()
    {
        return this.patientId;
    }

    /**
     * @param patientId
     *            the patientId to set
     */
    public void setPatientId(long patientId)
    {
        this.patientId = patientId;
    }

    /**
     * @return the eventId
     */
    public long getEventId()
    {
        return this.eventId;
    }

    /**
     * @param eventId
     *            the eventId to set
     */
    public void setEventId(long eventId)
    {
        this.eventId = eventId;
    }

    /**
     * @return the priority
     */
    public int getPriority()
    {
        return this.priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    /**
     * @return the alarmClassification
     */
    public String getAlarmClassification()
    {
        return this.alarmClassification;
    }

    /**
     * @param alarmClassification
     *            the alarmClassification to set
     */
    public void setAlarmClassification(String alarmClassification)
    {
        this.alarmClassification = alarmClassification;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof AlarmEventEntity);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @SuppressWarnings("javadoc")
    public AlarmEvent toAlarmEvent()
    {
        AlarmEvent alarmEvent = new AlarmEvent();
        alarmEvent.setAlarm(this.alarm);
        alarmEvent.setAlarmClassification(this.alarmClassification);
        alarmEvent.setEventId(this.eventId);
        alarmEvent.setNumberOfAlarms(this.numberOfAlarms);
        Patient p = getPatient().toPatient();
        alarmEvent.setPatient(p);
        alarmEvent.setPriority(this.priority);

        return alarmEvent;
    }

    /**
     * @return the patient
     */
    public PatientEntity getPatient()
    {
        return this.patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(PatientEntity patient)
    {
        this.patient = patient;
    }
}