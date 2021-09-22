# Simple Event Organize
Simple Event Organize App (REST Api), for my learning spring framwork
## Installation
Make sure your computer have installed maven. inside of the directory run
```bash
$ mvn clean install
$ mvn spring-boot:run
```

| Method | Endpoint                         |
|--------|----------------------------------|
| GET    | api/v1/organizers                |
| GET    | api/v1/organizers/{organizerId}  |
| POST   | api/v1/organizers                |
| DELETE | api/v1/organizers/{organizerId}  |
| GET    | api/v1/applicants                |
| GET    | api/v1/applicants/{applicantId}  |
| POST   | api/v1/applicants                |
| DELETE | api/v1/applicants                |
| GET    | api/v1/events                    |
| GET    | api/v1/events/{eventId}          |
| POST   | api/v1/events                    |
| DELETE | api/v1/events/{eventId}          |
| GET    | api/v1/enrolment/event/{eventId} |
| POST   | api/v1/enrolment                 |