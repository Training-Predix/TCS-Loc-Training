package com.ge.predix.solsvc.training.alarmservice.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jean Lau
 * 
 */

@XmlRootElement
public class AlarmEvent
        implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String            alarm;
    private String            alarmClassification;
    private Patient           patient;
    private long              eventId;
    private int               priority;
    private int               numberOfAlarms;

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

    public AlarmEvent()
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
    public AlarmEvent(String alarm, String classification, Patient patient, long eventId, int priority)
    {
        super();
        this.alarm = alarm;
        this.alarmClassification = classification;
        this.setPatient(patient);
        this.eventId = eventId;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}