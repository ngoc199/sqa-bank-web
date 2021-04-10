INSERT INTO banking.customers (id,city,country,details,district,sub_district,zip_code,created_at,first_name,last_name,middle_name,phone_number,updated_at,user_login_id) VALUES
	 ('1','hanoi','vietnam',NULL,'hanoi','hanoi','10000',NULL,'Ngoc','Nguyen','Duy Minh',NULL,NULL,1);

INSERT INTO banking.user_logins (created_at,is_locked,password,`role`,updated_at,username) VALUES
	 (NULL,0,'$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','CUSTOMER',NULL,'customer'),
	 (NULL,0,'$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_CUSTOMER_CARE',NULL,'employee1'),
	 (NULL,0,'$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_ACCOUNTANT',NULL,'employee2'),
	 (NULL,0,'$2a$12$/EJpPN446K2JUvrBJgqe/OHla.SiMlW65RkrYnhDgx0fs7qV0XtY2','EMPLOYEE_MANAGER',NULL,'employee3');

INSERT INTO banking.employees (id,city,country,details,district,sub_district,zip_code,created_at,first_name,last_name,middle_name,phone_number,updated_at,user_login_id) VALUES
	 ('2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nghia',NULL,NULL,NULL,NULL,2),
	 ('3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nam',NULL,NULL,NULL,NULL,3),
	 ('4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nguyen',NULL,NULL,NULL,NULL,4);