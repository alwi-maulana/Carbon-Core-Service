
# 🌱 Carbon Core Activity Service

Carbon Activity Service is the core service responsible for managing user carbon-related activities and emission metadata.




## Responsibilities

- Manage carbon activities (create, read, filter by date/type/status)
- Manage emission factors (activity type, unit, factor kg)
- Persist carbon emission results received from calculation service
- Provide daily summary and activity-based aggregation APIs
- Act as the source of truth (database owner)


## Key Features

- RESTful API
- PostgreSQL as primary database
- Flexible filtering (date range, activity type, calculation status)
- Clean separation between activity data and calculation results


# Role in Architecture

This service acts as the core domain service and data provider in the carbon tracking system.


## Tech Stack

**Database:** Postgres

**Server:** Java, SpringBoot, Rest API

**Implementation:** Spring IoC, Java Stream, Native SQL Query, Microservice

## API Collections are store on this file
You can import it to your local postman
"CARBON TRACKER (Carbon Activity Service).postman_collection.json"

## Database Schema and Data Sample
The schema and data query are store here, you can import it to your local environment

```bash
 src/main/resources 
```


## Deployment on localhost:8080

To deploy this project run 

```bash
  mvn spring-boot:run   
```


## Additional Info

This Service is linked to the 2nd service (Carbon-Emission-Calculation-Service)

so run both service to simulate the process

