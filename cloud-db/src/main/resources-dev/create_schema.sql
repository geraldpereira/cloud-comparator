-- hsqldb
DROP TABLE IF EXISTS contact;

DROP TABLE IF EXISTS simple_provider;
DROP TABLE IF EXISTS provider_compute_size;
DROP TABLE IF EXISTS provider;

CREATE TABLE provider (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  compute_type varchar(50) NOT NULL,
  bandwidth_in_cost double NOT NULL,
  bandwidth_out_cost double NOT NULL,  
  CONSTRAINT pk1 PRIMARY KEY (id,location)
);

CREATE TABLE simple_provider (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  max_ram double NOT NULL,
  max_cpu double NOT NULL,
  max_storage double NOT NULL,
  ram_cost double NOT NULL,
  cpu_cost double NOT NULL,
  storage_cost double NOT NULL,
  CONSTRAINT pk2 PRIMARY KEY (id,location),
  CONSTRAINT fk2 FOREIGN KEY(id,location) REFERENCES provider (id,location)
);

CREATE TABLE provider_compute_size (
  id varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  size varchar(50) NOT NULL,
  ram double NOT NULL,
  cpu double NULL,
  storage double NULL,
  cost double NOT NULL,
  CONSTRAINT pk3 PRIMARY KEY (id,location,size),
  CONSTRAINT fk3 FOREIGN KEY(id,location) REFERENCES provider (id,location)
);

-- CloudSigma
-- EU - WEST - Suisse (Zurich)
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'cloudsigma','eu_west_1','SIMPLE',0,0.0585);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('cloudsigma','eu_west_1',32,20,2048,0.0225,0.0293,0.1800);
-- USA - WEST - Las Vegas 
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'cloudsigma','us_west_3','SIMPLE',0,0.05);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('cloudsigma','us_west_3',32,20,2048,0.0220,0.025,0.14);

-- Rackspace
-- EU - WEST - England (Slough)
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'rackspace','eu_west_2','SIZED',0,0.19);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','1',0.25,10,0.0158);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','2',0.5,20,0.0316);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','3',1,40,0.0633);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','4',2,80,0.1265);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','5',4,160,0.253);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','6',8,320,0.5378);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','7',15.5,620,1.0125);

-- US - Chicago, Dallas, San Antonio
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'rackspace','us_center_1','SIZED',0,0.18);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','1',0.25,10,0.015);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','2',0.5,20,0.03);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','3',1,40,0.06);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','4',2,80,0.12);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','5',4,160,0.24);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','6',8,320,0.48);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','7',15.5,620,0.96);

-- AWS 
-- US - EAST - Virginia
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_east_1','SIZED',0,0.120);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.small',1,1.7,160,0.085);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.large',4,7.5,850,0.34);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.xlarge',8,15,1690,0.68 );
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_east_1','t1.micro',0.6,0.02);
-- High-Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.xlarge',13,17.1,420,0.50);
-- High-Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.2xlarge',13,34.2,850,1.00);
-- High-Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.4xlarge',26,68.4,1690,2.00);
-- High-CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','c1.medium',5,1.7,350,0.17);
-- High-CPU Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','c1.xlarge',20,7,1690,0.68);
-- Cluster Compute Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cc1.4xlarge',23,33.5,1690,1.30);
-- Cluster Compute Eight Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cc2.8xlarge',62,88,3370,2.40);
-- Cluster GPU Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cg1.4xlarge',22,33.5,1690,2.10);

-- US - WEST - Oregon
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_west_1','SIZED',0,0.120);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.small',1,1.7,160,0.085);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.large',4,7.5,850,0.34);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.xlarge',8,15,1690,0.68);
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_west_1','t1.micro',0.6,0.02);
-- High-Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.xlarge',13,17.1,420,0.50);
-- High-Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.2xlarge',13,34.2,850,1.00);
-- High-Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.4xlarge',26,68.4,1690,2.00);
-- High-CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','c1.medium',5,1.7,350,0.17);
-- High-CPU Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','c1.xlarge',20,7,1690,0.68);

-- US - WEST - Northern California
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_west_2','SIZED',0,0.120);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.small',1,1.7,160,0.095);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.large',4,7.5,850,0.38);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.xlarge',8,15,1690,0.76);
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_west_2','t1.micro',0.6,0.025);
-- High-Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.xlarge',13,17.1,420,0.57);
-- High-Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.2xlarge',13,34.2,850,1.14);
-- High-Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.4xlarge',26,68.4,1690,2.28);
-- High-CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','c1.medium',5,1.7,350,0.19);
-- High-CPU Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','c1.xlarge',20,7,1690,0.76);

-- EU - WEST - Ireland
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','eu_west_3','SIZED',0,0.120);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.small',1,1.7,160,0.095);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.large',4,7.5,850,0.38);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.xlarge',8,15,1690,0.76);
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','eu_west_3','t1.micro',0.6,0.025);
-- High-Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.xlarge',13,17.1,420,0.57);
-- High-Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.2xlarge',13,34.2,850,1.14);
-- High-Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.4xlarge',26,68.4,1690,0.19);
-- High-CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','c1.medium',5,1.7,350,0.76);

-- ASIA PACIFIC - Singapore
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','ap_southeast_1','SIZED',0,0.190);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.small',1,1.7,160,0.095);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.large',4,7.5,850,0.38);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.xlarge',8,15,1690,0.76);
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','ap_southeast_1','t1.micro',0.6,0.025);
-- High-Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.xlarge',13,17.1,420,0.57);
-- High-Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.2xlarge',13,34.2,850,1.14);
-- High-Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.4xlarge',26,68.4,1690,2.28);
-- High-CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','c1.medium',5,1.7,350,0.19);
-- High-CPU Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','c1.xlarge',20,7,1690,0.76);

-- ASIA PACIFIC - Tokyo
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','ap_northeast_1','SIZED',0,0.201);
-- Small instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.small',1,1.7,160,0.10);
-- Large instance 
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.large',4,7.5,850,0.40);
-- Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.xlarge',8,15,1690,0.80);
-- Micro Instance
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','ap_northeast_1','t1.micro',0.6,0.027);
-- High_Memory Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.xlarge',13,17.1,420,0.60);
-- High_Memory Double Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.2xlarge',13,34.2,850,1.20);
-- High_Memory Quadruple Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.4xlarge',26,68.4,1690,2.39);
-- High_CPU Medium Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','c1.medium',5,1.7,350,0.20);
-- High_CPU Extra Large Instance
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','c1.xlarge',20,7,1690,0.80);
-- max transfer 10 TB