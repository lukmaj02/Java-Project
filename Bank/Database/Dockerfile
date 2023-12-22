FROM mysql

MAINTAINER me

ENV MYSQL_ROOT_PASSWORD=root \
    MYSQL_DATABASE=bankomat
ADD baza.sql /docker-entrypoint-initdb.d

EXPOSE 3306