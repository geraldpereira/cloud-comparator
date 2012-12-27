DELETE FROM simple_provider;
DELETE FROM provider_compute_size;
DELETE FROM provider;

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ('elastichosts','eu_west_4','SIMPLE',0.155,0.155);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('elastichosts','eu_west_4',8,20,1862,0.0186,0.0248,0.09);
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ('elastichosts','us_center_2','SIMPLE',0.15,0.15);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('elastichosts','us_center_2',8,20,1862,0.018,0.025,0.10);
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ('elastichosts','us_west_4','SIMPLE',0.15,0.15);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('elastichosts','us_west_4',8,20,1862,0.018,0.025,0.10);
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ('elastichosts','us_north_1','SIMPLE',0.15,0.15);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('elastichosts','us_north_1',8,20,1862,0.018,0.025,0.10);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'cloudsigma','eu_west_1','SIMPLE',0,0.0585);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('cloudsigma','eu_west_1',32,20,8192,0.0225,0.0293,0.1800);
INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'cloudsigma','us_west_3','SIMPLE',0,0.05);  
INSERT INTO simple_provider(id,location,max_ram,max_cpu,max_storage,cpu_cost,ram_cost,storage_cost) VALUES ('cloudsigma','us_west_3',32,20,8192,0.0220,0.025,0.14);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'rackspace','eu_west_2','SIZED',0,0.19);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','1',0.25,10,0.016);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','2',0.5,20,0.031);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','3',1,40,0.063);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','4',2,80,0.125);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','5',4,160,0.251);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','6',8,320,0.501);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','7',15.5,620,1.003);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','eu_west_2','8',30,1200,1.88);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'rackspace','us_center_1','SIZED',0,0.18);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','1',0.25,10,0.015);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','2',0.5,20,0.03);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','3',1,40,0.06);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','4',2,80,0.12);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','5',4,160,0.24);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','6',8,320,0.48);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','7',15.5,620,0.96);
INSERT INTO provider_compute_size(id,location,size,ram,storage,cost) VALUES ('rackspace','us_center_1','8',30,1200,1.8);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_east_1','SIZED',0,0.120);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.small',1,1.7,160,0.085);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.large',4,7.5,850,0.34);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m1.xlarge',8,15,1690,0.68 );
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_east_1','t1.micro',0.6,0.02);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.xlarge',13,17.1,420,0.50);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.2xlarge',13,34.2,850,1.00);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','m2.4xlarge',26,68.4,1690,2.00);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','c1.medium',5,1.7,350,0.17);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','c1.xlarge',20,7,1690,0.68);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cc1.4xlarge',23,33.5,1690,1.30);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cc2.8xlarge',62,88,3370,2.40);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_east_1','cg1.4xlarge',22,33.5,1690,2.10);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_west_1','SIZED',0,0.120);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.small',1,1.7,160,0.085);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.large',4,7.5,850,0.34);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m1.xlarge',8,15,1690,0.68);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_west_1','t1.micro',0.6,0.02);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.xlarge',13,17.1,420,0.50);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.2xlarge',13,34.2,850,1.00);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','m2.4xlarge',26,68.4,1690,2.00);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','c1.medium',5,1.7,350,0.17);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_1','c1.xlarge',20,7,1690,0.68);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','us_west_2','SIZED',0,0.120);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.small',1,1.7,160,0.095);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.large',4,7.5,850,0.38);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m1.xlarge',8,15,1690,0.76);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','us_west_2','t1.micro',0.6,0.025);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.xlarge',13,17.1,420,0.57);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.2xlarge',13,34.2,850,1.14);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','m2.4xlarge',26,68.4,1690,2.28);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','c1.medium',5,1.7,350,0.19);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','us_west_2','c1.xlarge',20,7,1690,0.76);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','eu_west_3','SIZED',0,0.120);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.small',1,1.7,160,0.095);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.large',4,7.5,850,0.38);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m1.xlarge',8,15,1690,0.76);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','eu_west_3','t1.micro',0.6,0.025);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.xlarge',13,17.1,420,0.57);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.2xlarge',13,34.2,850,1.14);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','m2.4xlarge',26,68.4,1690,0.19);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','eu_west_3','c1.medium',5,1.7,350,0.76);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','ap_southeast_1','SIZED',0,0.190);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.small',1,1.7,160,0.095);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.large',4,7.5,850,0.38);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m1.xlarge',8,15,1690,0.76);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','ap_southeast_1','t1.micro',0.6,0.025);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.xlarge',13,17.1,420,0.57);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.2xlarge',13,34.2,850,1.14);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','m2.4xlarge',26,68.4,1690,2.28);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','c1.medium',5,1.7,350,0.19);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_southeast_1','c1.xlarge',20,7,1690,0.76);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','ap_northeast_1','SIZED',0,0.201);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.small',1,1.7,160,0.10);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.large',4,7.5,850,0.40);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m1.xlarge',8,15,1690,0.80);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','ap_northeast_1','t1.micro',0.6,0.027);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.xlarge',13,17.1,420,0.60);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.2xlarge',13,34.2,850,1.20);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','m2.4xlarge',26,68.4,1690,2.39);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','c1.medium',5,1.7,350,0.20);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','ap_northeast_1','c1.xlarge',20,7,1690,0.80);

INSERT INTO provider(id,location,compute_type,bandwidth_in_cost,bandwidth_out_cost) VALUES ( 'aws_ec2','sa_east_1','SIZED',0,0.25);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m1.small',1,1.7,160,0.115);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m1.large',4,7.5,850,0.46);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m1.xlarge',8,15,1690,0.92);
INSERT INTO provider_compute_size(id,location,size,ram,cost) VALUES ('aws_ec2','sa_east_1','t1.micro',0.6,0.027);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m2.xlarge',13,17.1,420,0.68);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m2.2xlarge',13,34.2,850,1.36);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','m2.4xlarge',26,68.4,1690,2.72);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','c1.medium',5,1.7,350,0.23);
INSERT INTO provider_compute_size(id,location,size,cpu,ram,storage,cost) VALUES ('aws_ec2','sa_east_1','c1.xlarge',20,7,1690,0.92);