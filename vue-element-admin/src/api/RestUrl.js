
export const GET = "get";
export const POST = "post";
export const PUT = "put";
export const DELETE = "delete";

//本机调试
export const baseUrl = "http://localhost:8080";

// user
export const userLogin = "/user/login";

// movie
export const getMoviesInfo =  "/movie/moviesInfo";
export const getMovieDesc =   "/movie/desc";
export const moviesCnt =  "/movie/moviesCount";
export const shortComments =  "/movie/shortComments";

// collected movie
export const collectedMovie =  "/movie/collection";
export const getCollectedMoves =  "/movie/collection/collections";

// //microservice
// export const userService = "/microservice-user-service";
// export const movieService = "/microservice-movie-service";
//
// // user
// export const userLogin = userService + "/user/login";
//
// // movie
// export const getMoviesInfo = movieService + "/movie/moviesInfo";
// export const getMovieDesc = movieService +  "/movie/desc";
// export const moviesCnt = movieService + "/movie/moviesCount";
// export const shortComments = movieService + "/movie/shortComments";
//
// // collected movie
// export const collectedMovie = movieService + "/movie/collection";
// export const getCollectedMoves = movieService + "/movie/collection/collections";
