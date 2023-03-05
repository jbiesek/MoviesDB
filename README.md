# MoviesDB
MoviesDB is a Java Application. It is a backend for web application similar to IMDb or Filmweb. It uses Hibernate for CRUD operations in MySQL database and Spring Boot to create REST API.

![moviesDB](https://user-images.githubusercontent.com/56349346/212474763-8615aa40-7e59-45fa-b065-51a1b6bf08e5.png)

## Actor related REST Endpoints
### Get all actors
**GET** localhost:8080/actors
### Get actor by id
**GET** localhost:8080/actor/{actor_id}
### Add new actor
**POST** localhost:8080/actor \
**Body:** {\
"name": "Robert",\
"surname": "De Niro",\
"birth_date": "1942-09-17",\
"country": "USA"\
}
### Edit actor by id
**PUT** localhost:8080/actor/{actor_id}\
**Body:** {\
"id": {actor_id},\
"name": "Robert",\
"surname": "De Niro",\
"birth_date": "1942-09-17",\
"country": "United States of America"\
}
### Delete actor by id
**DELETE** localhost:8080/actor/{actor_id}
### Get actor by review id
**GET** localhost:8080/actor/byReview/{review_id}
### Get actors by user id
**GET** localhost:8080/actors/byUser/{user_id}
### Get actors by movie id
**GET** localhost:8080/actors/byMovie/{movie_id}
### Get actors by movie id with role
**GET** localhost:8080/actors/byMovieWithRole/{movie_id}
### Get actor image
**GET** localhost:8080/actor/image/{actor_id}