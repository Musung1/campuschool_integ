# Dockerfile

# MySQL 이미지를 기반으로 이미지 생성
FROM mysql
COPY init.sql .

# MySQL 설정
ENV MYSQL_ROOT_PASSWORD=1234567890
ENV MYSQL_DATABASE=mydb


# 포트 설정 (기본 MySQL 포트는 3306)
EXPOSE 3306