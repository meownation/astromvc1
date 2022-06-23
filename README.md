Astrology daily REST service exposes endpoints for daily Horoscope articles.

Astrology article spinner uses Postgres database for persistence, and redis for caching.
Flyway provides initial paragraphs used for generating astrology articles and db schema management.
Docker compose spins up the environment with the following containers:
#### 1. Java spring app
#### 2. Postgres
#### 3. Redis
#### 4. Pg admin

https://github.com/meownation/astroreport/tree/master
contains REST API client java application
