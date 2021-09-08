DATABASE_JDBC_URL=jdbc:postgresql://localhost:5432/postgres
DATABASE_USER_NAME=postgres
DATABASE_PASSWORD=postgres
DATABASE_DRIVER=org.postgresql.Driver
POOL=10

run-project:
	make start-db

start-db:
	docker run --name register -e POSTGRES_PASSWORD=${DATABASE_PASSWORD} -e POSTGRES_USER=${DATABASE_USER_NAME} -p 5432:5432 -d postgres
