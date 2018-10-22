package com.green.ms_movie_service.mapper;

import com.green.ms_movie_service.entity.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/5
 * @description:
 */
@Mapper
@Component(value = "movieMapper")
public interface MovieMapper
{
    @Select("select * from movie where `rank` between #{rankBase} and #{rankBase} + #{size}")
    List<Movie> findMoviesByRank(@Param("rankBase") int rankBase, @Param("size") int size);
    
    @Select("select * from movie where `title` = #{title}")
    Movie findMovieByTitle(@Param("title") String title);
    
    @Select("select count(distinct `title`) from movie")
    int findMoivesCnt();
    
    @Select("select min(`id`) from `movie` where `title` = #{title}")
    Integer findMovieIdByTitle(@Param("title") String title);
    
    @Select("select `content` from shortComment where `movie_id` = #{movie_id}")
    List<String> getShortComments(@Param("movie_id") int movie_id);
    
    @Insert("insert into `collected_movie` (`user_id`, `movie_id`) values (#{user_id}, #{movie_id})")
    int insertCollectedMovie(@Param("user_id") int user_id, @Param("movie_id") int movie_id);
    
    @Delete("delete from `collected_movie` where `user_id` = #{user_id} and `movie_id` = #{movie_id}")
    int deleteCollectedMovie(@Param("user_id") int user_id, @Param("movie_id") int movie_id);
    
    @Select("select * from `movie` where `id` in (select `movie_id` from `collected_movie` where `user_id` = #{user_id})")
    List<Movie> getCollectedMovies(@Param("user_id") int user_id);
    
    @Select("select count(*) from `collected_movie` where `user_id` = #{user_id} and `movie_id` = #{movie_id};")
    int getCollectedMovieCnt(@Param("user_id") int user_id, @Param("movie_id") int movie_id);
}
