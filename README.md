# athena-airlines
A 3 tier flight booking system web app that uses Spring Boot, JPA/Hibernate, Thymeleaf &amp; PostgreSQL.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
### Prerequisites/Requirements
* Mac OS X 64 bit
* Windows 64 bit
* Linux 64 bit
<p>Download Eclipse Java EE or Spring Tool Suite 4.
Apache Tomcat 7.0 or higher. </p>

### Installing
Clone the current repository
```
git clone https://github.com/ajmengistu/online-bookstore.git
```
Open the project in Eclipse & run the program using Spring framework the default localhost server on a web browser
```
localhost:8080/
```
Connecting to PostgreSQL database system
```
sudo service postgresql [restart, start, stop]
```
Open PostgreSQL command line
```
sudo su postgres
```
Connect to local database using psql
```
psql
=# \c [database_name]
=# \l
=# \dt
=# \i athena_airlines.sql
```
Remote connection
```
psql -h aws-endpoint -p 5432 -U root dbname
```

## Deployment
### Amazon Web Services
Deployed Java Applications on AWS Elastic Beanstalk
```
https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/create_deploy_Java.html
```
### Heroku
Deploying Java Apps on Heroku

```
https://devcenter.heroku.com/articles/deploying-java
```
## Authors
* Aderajew Mengistu

## License
This project is licensed under MIT License

## Acknowledgments
* (Kaggle)[https://www.kaggle.com/datasets] for providing the list of books
* (Stack Overflow)[https://www.stackoverflow.com]

## Resources
### Kaggle Dataset
* https://www.kaggle.com/zygmunt/goodbooks-10k
* Data Source Used: `books.csv`
* The dataset `books.csv` contains a list of 10,000 books with various attributes including number of ratings, average ratings, publication year, and image urls. 
