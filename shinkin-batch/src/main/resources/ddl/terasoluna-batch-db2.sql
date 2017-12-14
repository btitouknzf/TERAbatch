CREATE TABLE batch_job_request (
    job_seq_id bigint NOT NULL
    GENERATED ALWAYS
      AS IDENTITY
      (START WITH 1
       INCREMENT BY 1
       MINVALUE 1
       NO MAXVALUE
       NO CYCLE
       NO CACHE
       ORDER),
    job_name varchar(100) NOT NULL,
    job_parameter varchar(200),
    job_execution_id bigint,
    polling_status varchar(10) NOT NULL,
    create_date timestamp NOT NULL,
    update_date timestamp,
    PRIMARY KEY(job_seq_id)
);