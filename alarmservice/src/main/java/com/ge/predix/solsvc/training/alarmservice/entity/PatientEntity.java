package com.ge.predix.solsvc.training.alarmservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ge.predix.solsvc.training.alarmservice.dto.Patient;

/**
 * Patient Entity
 *
 */
@Entity
@Table(name = "PATIENT")
//@TableGenerator(name="PATIENT_SEQUENCE", table="ALARM_SEQUENCE", pkColumnName = "ID", valueColumnName = "SEQUENCE_VALUE", pkColumnValue = "0", allocationSize=10)
public class PatientEntity
        implements Serializable
{

    /**
     * 
     */
    private static final long                                                                            serialVersionUID = 1L;

    
    @Id @Column(name = "PATIENT_ID") private long  patientId;

    @Column(name = "TITLE") private String                                                               title;
    @Column(name = "FIRST_NAME") private String                                                          firstName;
    @Column(name = "MID_INIT") private String                                                            midInit;
    @Column(name = "LAST_NAME") private String                                                           lastName;
    @Column(name = "EMAIL") private String                                                               email;
    @Column(name = "GENDER") private String                                                              gender;
    @Column(name = "BIRTH_DATE") private Date                                                            birthDate;

    //the fetch type of PatientEntity and AlarmEventEntity cannot be both EAGER, which will cause infinity loop.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient") private Set<AlarmEventEntity>               events;

    /**
     * 
     */
    public PatientEntity()
    {

    }

    /**
     * @param patientId
     *            identifier
     * @param firstName
     *            first name
     * @param midInit
     *            middle initial
     * @param lastName
     *            last name
     * @param email
     *            email
     * @param gender
     *            gender
     * @param birthDate
     *            birthday
     */
    public PatientEntity(long patientId, String firstName, String midInit, String lastName, String email,
            String gender, Date birthDate)
    {
        super();
        this.patientId = patientId;
        this.firstName = firstName;
        this.midInit = midInit;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
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
     * @return the title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return first name
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * @param firstName
     *            parameter
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return string
     */
    public String getMidInit()
    {
        return this.midInit;
    }

    /**
     * @param midInit
     *            input parameter
     */
    public void setMidInit(String midInit)
    {
        this.midInit = midInit;
    }

    @SuppressWarnings("javadoc")
    public String getLastName()
    {
        return this.lastName;
    }

    @SuppressWarnings("javadoc")
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @SuppressWarnings("javadoc")
    public String getEmail()
    {
        return this.email;
    }

    @SuppressWarnings("javadoc")
    public void setEmail(String email)
    {
        this.email = email;
    }

    @SuppressWarnings("javadoc")
    public String getGender()
    {
        return this.gender;
    }

    @SuppressWarnings("javadoc")
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    @SuppressWarnings("javadoc")
    public Date getBirthDate()
    {
        return this.birthDate;
    }

    @SuppressWarnings("javadoc")
    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof PatientEntity);
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

    /**
     * @return patient
     */
    public Patient toPatient()
    {
        Patient patient = new Patient();
        patient.setBirthDate(this.birthDate);
        patient.setEmail(this.email);
        patient.setFirstName(this.firstName);
        patient.setGender(this.gender);
        patient.setLastName(this.lastName);
        patient.setMidInit(this.midInit);
        patient.setPatientId(this.patientId);
        patient.setTitle(this.title);
        return patient;
    }

    /**
     * @return set of alarm event entity
     */
    public Set<AlarmEventEntity> getEvents()
    {
        return this.events;
    }

    /**
     * @param events set of alarm entityies
     */
    public void setEvents(Set<AlarmEventEntity> events)
    {
        this.events = events;
    }
}

