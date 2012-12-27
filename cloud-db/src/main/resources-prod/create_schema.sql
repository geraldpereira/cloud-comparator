-- postgresql

CREATE USER ccuser WITH ENCRYPTED PASSWORD 'butshewasold';
CREATE DATABASE cloudcomparator WITH OWNER = ccUser ENCODING = 'UTF8';

DROP TABLE IF EXISTS simple_provider;
DROP TABLE IF EXISTS provider_compute_size;
DROP TABLE IF EXISTS provider;

CREATE TABLE provider (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  compute_type varchar(50) NOT NULL,  
  bandwidth_in_cost numeric NOT NULL,
  bandwidth_out_cost numeric NOT NULL,  
  CONSTRAINT pk_provider PRIMARY KEY (id,location)
);

-- 730 heures dans un mois 
CREATE TABLE simple_provider (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  max_ram numeric NOT NULL, -- in GB
  max_cpu numeric NOT NULL, -- in GHz
  max_storage numeric NOT NULL, -- in GB
  ram_cost numeric NOT NULL, -- per GB per hour  
  cpu_cost numeric NOT NULL, -- per GHz per hour
  storage_cost numeric NOT NULL, -- per GB per month
  CONSTRAINT pk_simple_provider PRIMARY KEY (id,location),
  CONSTRAINT fk_simple_provider FOREIGN KEY (id,location) REFERENCES provider (id,location)
);

CREATE TABLE provider_compute_size (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  size varchar(50) NOT NULL,
  ram numeric NOT NULL,
  cpu numeric NULL,
  storage numeric NULL,
  cost numeric NOT NULL,
  CONSTRAINT pk_compute_size PRIMARY KEY (id,location,size),
 CONSTRAINT fk_compute_size FOREIGN KEY (id,location) REFERENCES provider (id,location)
);

ALTER TABLE provider OWNER TO ccuser;
ALTER TABLE simple_provider OWNER TO ccuser;
ALTER TABLE provider_compute_size OWNER TO ccuser;
