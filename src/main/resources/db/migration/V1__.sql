CREATE TABLE speedyteller.sample_entity (
    id uuid NOT NULL,
    title varchar(30),
    description varchar(255),
    CONSTRAINT sample_entity_pkey PRIMARY KEY (id)
);