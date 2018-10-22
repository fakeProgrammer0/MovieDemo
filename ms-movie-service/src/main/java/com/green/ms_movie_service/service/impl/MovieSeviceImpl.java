package com.green.ms_movie_service.service.impl;

import com.green.ms_movie_service.entity.Movie;
import com.green.ms_movie_service.entity.ResponseMsg;
import com.green.ms_movie_service.mapper.MovieMapper;
import com.green.ms_movie_service.request.UserFeignClient;
import com.green.ms_movie_service.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/5
 * @description:
 */
@Service
public class MovieSeviceImpl implements MovieService
{
    @Autowired
    private MovieMapper movieMapper;
    
    @Autowired
    private UserFeignClient userFeignClient;
//    @Autowired
//    private UserMapper userMapper;
    
    private Integer getUserId(String username)
    {
        ResponseMsg responseMsg = userFeignClient.findIdByName(username);
        if (responseMsg.getMsg().equals("ok"))
            return (Integer) responseMsg.getData();
        return null;
    }
    
    @Override
    public List<Map<String, Object>> getMoviesInfo(int rankBase, int size)
    {
        if (rankBase <= 0 || size <= 0)
            return null;
        
        try
        {
            List<Movie> movies = movieMapper.findMoviesByRank(rankBase, size);
            List<Map<String, Object>> result = new LinkedList<>();
            for (Movie movie : movies)
                result.add(movie.getInfo());
            return result;
        } catch (Exception ex)
        {
            // TODO: 改进为Logger
            System.out.println(ex);
        }
        return null;
    }
    
    @Override
    public Movie getMovieDescription(String title)
    {
        //TODO: 防止SQL注入
        return movieMapper.findMovieByTitle(title);
    }
    
    @Override
    public int getMoivesCnt()
    {
        return movieMapper.findMoivesCnt();
    }
    
    @Override
    public List<String> getShortComments(String title)
    {
        int movie_id = movieMapper.findMovieIdByTitle(title);
        return movieMapper.getShortComments(movie_id);
    }
    
    @Override
    public boolean addCollectedMovie(String username, String title)
    {
        Integer user_id = getUserId(username);
        Integer movie_id = movieMapper.findMovieIdByTitle(title);
        if (user_id == null || user_id <= 0 || movie_id == null || movie_id <= 0)
            return false;
        return movieMapper.insertCollectedMovie(user_id, movie_id) != 0;
    }
    
    @Override
    public boolean removeCollectedMovie(String username, String title)
    {
        Integer user_id = getUserId(username);
        Integer movie_id = movieMapper.findMovieIdByTitle(title);
        if (user_id == null || user_id <= 0 || movie_id == null || movie_id <= 0)
            return false;
        return movieMapper.deleteCollectedMovie(user_id, movie_id) != 0;
    }
    
    @Override
    public List<Movie> getCollectedMovies(String username)
    {
        Integer user_id = getUserId(username);
        if (user_id == null)
            return null;
        return movieMapper.getCollectedMovies(user_id);
    }
    
    @Override
    public boolean checkMovieCollected(String username, String title)
    {
        try
        {
            Integer user_id = getUserId(username);
            Integer movie_id = movieMapper.findMovieIdByTitle(title);
            if (user_id == null || user_id <= 0 || movie_id == null || movie_id <= 0)
                return false;
            return movieMapper.getCollectedMovieCnt(user_id, movie_id) == 1;
        } catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }
    }
}
