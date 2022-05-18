CREATE USER user_name IDENTIFIED BY password_of_user;

GRANT CONNECT,RESOURCE,DBA TO user_name;

GRANT CREATE SESSION,GRANT ANY PRIVILEGE TO user_name;

GRANT UNLIMITED TABLESPACE TO user_name;