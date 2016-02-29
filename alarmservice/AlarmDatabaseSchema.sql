DROP TABLE PATIENT;
DROP TABLE alarm_event;
DROP TABLE HOSPITAL;

CREATE TABLE patient (
  patient_id INTEGER NOT NULL,
  title VARCHAR(20),
  first_name VARCHAR(20),
  mid_init VARCHAR(8),
  last_name VARCHAR(20),
  email VARCHAR(40),
  gender VARCHAR(10),
  birth_date DATE,
  PRIMARY KEY(patient_id)
  );
  
CREATE TABLE alarm_event (
  event_id INTEGER NOT NULL,
  patient_id INTEGER NOT NULL,
  alarm VARCHAR(15) NOT NULL,
  priority INTEGER NOT NULL,
  alarm_classification VARCHAR(15) NOT NULL,
  PRIMARY KEY(event_id)
 );
 
CREATE TABLE HOSPITAL(
  hostpital_id INTEGER NOT NULL,
  name VARCHAR(40),
  address VARCHAR(80),
  phone VARCHAR(20),
  email VARCHAR(40),
  PRIMARY KEY(hostpital_id)
  );
  
INSERT INTO PATIENT (patient_id, TITLE, FIRST_NAME, LAST_NAME, EMAIL) VALUES (1, 'Software Engineer', 'Mike', 'Waldman', 'mike.waldman@ge.com'); 
INSERT INTO HOSPITAL (hostpital_id, name, address, phone) VALUES (1, 'John Muir Medical Group', '100, 2305 Camino Ramon, San Ramon, CA 94583', '925-275-3888');

INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (1, 1, 'ARTIFACT', 'TECHNICAL', 1); 
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (2, 1, 'PVC', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (3, 1, 'COUPLET', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (4, 1, 'PVC', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (5, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (6, 1, 'COUPLET', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (7, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (8, 1, 'SPO2 MOTION DET', 'TECHNICAL', 0);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (9, 1, 'SPO2 MOTION DET', 'TECHNICAL', 0);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (10, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (11, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (12, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (13, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (14, 1, 'PVC', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (15, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (16, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (17, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (18, 1, 'PVC', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (19, 1, 'CHNGE BATTERY', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (20, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (21, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (22, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (23, 1, 'PVC', 'ARRHYTHMIA', 4);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (24, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (25, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (26, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (27, 1, 'SPO2 MOTION DET', 'TECHNICAL', 0);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (28, 1, 'SPO2 MOTION DET', 'TECHNICAL', 0);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (29, 1, 'SPO2 PROBE', 'TECHNICAL', 2);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (30, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (31, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (32, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (33, 1, 'ARTIFACT', 'TECHNICAL', 1);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (34, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (35, 1, 'SPO2 LO', 'PARAMETER', 6);
INSERT INTO alarm_event (event_id, patient_id, alarm, alarm_classification, priority) VALUES (36, 1, 'SPO2 LO', 'PARAMETER', 6);