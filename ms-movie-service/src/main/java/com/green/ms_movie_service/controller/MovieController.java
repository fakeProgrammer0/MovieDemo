package com.green.ms_movie_service.controller;

import com.green.ms_movie_service.entity.Movie;
import com.green.ms_movie_service.entity.ResponseMsg;
import com.green.ms_movie_service.service.MovieService;
import com.green.ms_movie_service.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/5
 * @description:
 */

//@CrossOrigin(origins = "http://localhost:9527", maxAge = 3600)
@RestController
//@RequestMapping("/movie")
@RequestMapping("/")
public class MovieController
{
    @Autowired
    private MovieService movieService;
    
    @RequestMapping(value = "/moviesInfo", method = RequestMethod.GET)
    public ResponseMsg getMoviesInfo(@RequestParam("rankBase") int rankBase, @RequestParam("size") int size)
    {
        Object data = movieService.getMoviesInfo(rankBase, size);
        ResponseMsg responseMsg;
        if (data != null)
        {
            responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.errorResponse();
            responseMsg.setMsg("error request params");
        }
        responseMsg.setData(data);
        return responseMsg;
    }
    
    @GetMapping(value = "/desc")
    public ResponseMsg getMovieDescription(@RequestParam("title") String title)
    {
        title = StrUtil.trimQuotes(title);
        Movie movie = movieService.getMovieDescription(title);
        ResponseMsg responseMsg;
        if (movie != null)
        {
            responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.notFoundResponse();
        }
        responseMsg.setData(movie);
        return responseMsg;
    }
    
    @GetMapping(value = "/moviesCount")
    public ResponseMsg getMoivesCnt()
    {
        ResponseMsg responseMsg = ResponseMsg.okResponse();
        responseMsg.setData(movieService.getMoivesCnt());
        return responseMsg;
    }
    
    @GetMapping(value = "/shortComments")
    public ResponseMsg getShortComments(@RequestParam("title") String title)
    {
        ResponseMsg responseMsg;
        Object data = movieService.getShortComments(title);
        if(data == null)
        {
            responseMsg = ResponseMsg.notFoundResponse();
        }else
        {
            responseMsg = ResponseMsg.okResponse();
        }
        responseMsg.setData(data);
        return responseMsg;
    }
    
    @PutMapping(value = "/collection")
    public ResponseMsg addCollectedMovie(@RequestBody Map<String, String> requestMap)
    {
        ResponseMsg responseMsg;
        if (movieService.addCollectedMovie(requestMap.get("username"), requestMap.get("title")))
        {
           responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.errorResponse();
        }
        return responseMsg;
    }
    
    @DeleteMapping(value = "/collection")
    public ResponseMsg removeCollectedMovie(@RequestBody Map<String, String> requestMap)
    {
        ResponseMsg responseMsg;
        if (movieService.removeCollectedMovie(requestMap.get("username"), requestMap.get("title")))
        {
            responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.errorResponse();
        }
        return responseMsg;
    }
    
    @GetMapping(value = "/collection/collections")
    public ResponseMsg getCollectedMovie(@RequestParam("username") String username)
    {
        ResponseMsg responseMsg;
        List<Movie> collectedMovies = movieService.getCollectedMovies(username);
        if (collectedMovies != null)
        {
            responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.notFoundResponse();
        }
        responseMsg.setData(collectedMovies);
        return responseMsg;
    }
    
    @GetMapping(value = "/collection")
    public ResponseMsg checkCollected(@RequestParam(value = "username") String username, @RequestParam(value = "title") String title)
    {
        ResponseMsg responseMsg;
        if (movieService.checkMovieCollected(username, title))
        {
            responseMsg = ResponseMsg.okResponse();
        } else
        {
            responseMsg = ResponseMsg.notFoundResponse();
        }
        return responseMsg;
    }
}
