CREATE TABLE PAUTA (
                       ID_PAUTA BIGSERIAL PRIMARY KEY,
                       TITULO VARCHAR(255) NOT NULL,
                       STATUS VARCHAR(20) NOT NULL
);

CREATE SEQUENCE pauta_seq START WITH 1 INCREMENT BY 1;