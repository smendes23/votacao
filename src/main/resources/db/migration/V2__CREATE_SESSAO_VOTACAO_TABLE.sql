-- V1__Create_SessaoVotacao_Table.sql

CREATE TABLE SESSAO_VOTACAO (
                                ID_SESSAO SERIAL PRIMARY KEY,
                                ID_PAUTA BIGINT NOT NULL,
                                STATUS VARCHAR(255),
                                DTH_INICIO TIMESTAMP,
                                DTH_FIM TIMESTAMP
);

CREATE SEQUENCE sessao_seq START WITH 1 INCREMENT BY 1;