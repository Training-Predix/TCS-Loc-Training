package com.ge.predix.solsvc.training.alarmservice.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jean Lau
 * 
 */

@XmlRootElement
public class Patient
        implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private long              patientId;
    private String            title;
    private String            firstName;
    private String            midInit;
    private String            lastName;
    private String            email;
    private String            gender;
    private Date              birthDate;

    /**
     * 
     */
    public Patient()
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
    public Patient(long patientId, String firstName, String midInit, String lastName, String email, String gender,
            Date birthDate)
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
}
