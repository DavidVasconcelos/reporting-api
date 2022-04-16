CREATE TABLE public.sample_entity (
    id uuid NOT NULL,
    title varchar(30),
    description varchar(255),
    CONSTRAINT sample_entity_pkey PRIMARY KEY (id)
);
CREATE TABLE public.sample_entity2 (
    id uuid NOT NULL,
    title varchar(30),
    description varchar(255),
    CONSTRAINT sample_entity_pkey2 PRIMARY KEY (id)
);
CREATE TABLE public.sample_entity3 (
    id BIGSERIAL NOT NULL,
    title varchar(30),
    description varchar(255),
    CONSTRAINT sample_entity_pkey3 PRIMARY KEY (id)
);
