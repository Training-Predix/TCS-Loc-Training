package com.ge.predix.solsvc.training.alarmservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ge.predix.solsvc.training.alarmservice.entity.PatientEntity;

/**
 * This class models the spring-data repository for alarmevent entity. Apart form the standard operations supported by
 * CRUD Repository, this class also supports customized named queries ,pagination, sorting and type safe queries using query-dsl.
 * 
 * @author 212350258
 */
@Repository
public interface IPatientEntityRepository extends PagingAndSortingRepository<PatientEntity, Long>
{
	/**
     * Reference to query to find by first name
     */
    String FIND_BY_FIRST_NAME  = "SELECT patient from PatientEntity patient  WHERE patient.firstName = ?1"; //$NON-NLS-1$
    
	/**
     * Persist the provided patient
     * 
     * @param patientRef
     *            Reference to the patient object to be persisted.
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    PatientEntity save(PatientEntity patientRef);
    
    /**
     * Find by name
     * 
     * @param name
     *            Reference to the patient's first name
     * @return
     *         list of Patient object
     */
    @Query(FIND_BY_FIRST_NAME)
	List<PatientEntity> getPatientByName(String name);
    
    @Override
	List<PatientEntity> findAll();
}