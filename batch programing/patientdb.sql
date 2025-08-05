CREATE TABLE patient_details (
    patient_id      SERIAL PRIMARY KEY,
    first_name      VARCHAR(100)    NOT NULL,
    last_name       VARCHAR(100)    NOT NULL,
    gender          VARCHAR(10)     NOT NULL
                                   CHECK (gender IN ('MALE','FEMALE','OTHER')),
    date_of_birth   DATE            NULL,
    email           VARCHAR(255)    UNIQUE NOT NULL,
    phone           VARCHAR(20)     NULL,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE patient_address (
    address_id      SERIAL PRIMARY KEY,
    patient_id      INT             NOT NULL
                                   REFERENCES patient_details(patient_id)
                                     ON DELETE CASCADE,
    street          VARCHAR(255)    NULL,
    city            VARCHAR(100)    NULL,
    state           VARCHAR(100)    NULL,
    zip             VARCHAR(20)     NULL,
    latitude        NUMERIC(9,6)    NULL,
    longitude       NUMERIC(9,6)    NULL,
    created_at      TIMESTAMPTZ     NOT NULL DEFAULT NOW()
);

CREATE TABLE patient_availability (
    availability_id SERIAL PRIMARY KEY,
    patient_id      INT             NOT NULL
                                   REFERENCES patient_details(patient_id)
                                     ON DELETE CASCADE,
    availability_date DATE          NOT NULL,
    slot_type       VARCHAR(20)     NOT NULL
                                   CHECK (slot_type IN ('MORNING','AFTERNOON','EVENING'))
);