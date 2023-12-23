FROM mysql

MAINTAINER me

ENV MYSQL_ROOT_PASSWORD=root \
    MYSQL_DATABASE=bankomat

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_general_ci", "--skip-character-set-client-handshake"]
ADD baza.sql /docker-entrypoint-initdb.d
EXPOSE 3306