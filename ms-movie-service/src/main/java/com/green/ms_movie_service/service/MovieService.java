package com.green.ms_movie_service.service;

import com.green.ms_movie_service.entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/5
 * @description:
 */
public interface MovieService
{
    List<Map<String, Object>> getMoviesInfo(int rankBase, int size);
    
    Movie getMovieDescription(String title);
    
    int getMoivesCnt();
    
    List<String> getShortComments(String title);
    
    boolean addCollectedMovie(String username, String title);
    
    boolean removeCollectedMovie(String username, String title);
    
    List<Movie> getCollectedMovies(String username);
    
    boolean checkMovieCollected(String username, String title);
}
