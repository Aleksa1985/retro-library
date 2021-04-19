

#Project

This project represents some basic library where you can put books into system and to read books from system. 

#Technologies used

    java 14 
    Dropwizard
    Postgres
    DOCKER

#Execution using Docker compose:

server application in the root of the project run : docker compose up
console client app can be run directly from IDEA or building and executing jar.    

#Simple CURLS for triggering api calls

    FIND ALL BOOKS: (return all books from the database)

curl -X GET http://localhost:9000/books

    FIND BOOK BY ISBN: (return only one beer from database)

curl -X GET http://localhost:9000/books?isbn=<some>

    SAVE BOOKS: (save one or multi books)

curl -X POST http://localhost:9000/books

```
curl --location --request POST 'http://localhost:9000/books' \
--header 'Content-Type: application/json' \
--data-raw '[
{
"authors": [
{

                "firstName": "srdjan",
                "lastName": "veljkovic"
            },
            {
                
                "firstName": "mitar",
                "lastName": "mitic"
            },
            {
                "firstName": "dragan",
                "lastName": "jonic"
            }
        ],
        "pageNumber": 0,
        "genre": "crimi",
        "isbn": "1234-00021",
        "title": "something0011"
    }
]
```
#NOTE: 
    Everything is dockerized
    You will need to have docker-compose installed to try this features
    for server app run docker-compose up in project root
    for client app run docker-compose up inside folder retro-library-client
    
#Technical features

    Implemented exception handling
    Implemented initial db migration
    basic functionalities for client end server
    composed to single docker compose run.
    
#Improvements (for next steps)

    Tests are missing
    Logging is missing
    add some security and user maintein service

