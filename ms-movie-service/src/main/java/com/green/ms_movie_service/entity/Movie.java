package com.green.ms_movie_service.entity;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: fakeProgrammer
 * @createdDate: 2018/10/5
 * @description:
 */

@Repository
public class Movie
{
    private int rank;
    private String title;
    private int score;
    private String country;
    private int year;
    private String category;
    private int votes;
    private String douban_url;
    private String pic_url;
    private String quote;
    private String directors;
    private String stars;
    private String releaseDate;
    private String running_time;
    private String description;
    
    public int getRank()
    {
        return rank;
    }
    
    public void setRank(int rank)
    {
        this.rank = rank;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setScore(int score)
    {
        this.score = score;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setCountry(String country)
    {
        this.country = country;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public void setYear(int year)
    {
        this.year = year;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public int getVotes()
    {
        return votes;
    }
    
    public void setVotes(int votes)
    {
        this.votes = votes;
    }
    
    public String getDouban_url()
    {
        return douban_url;
    }
    
    public void setDouban_url(String douban_url)
    {
        this.douban_url = douban_url;
    }
    
    public String getPic_url()
    {
        return pic_url;
    }
    
    public void setPic_url(String pic_url)
    {
        this.pic_url = pic_url;
    }
    
    public String getQuote()
    {
        return quote;
    }
    
    public void setQuote(String quote)
    {
        this.quote = quote;
    }
    
    public String getDirectors()
    {
        return directors;
    }
    
    public void setDirectors(String directors)
    {
        this.directors = directors;
    }
    
    public String getStars()
    {
        return stars;
    }
    
    public void setStars(String stars)
    {
        this.stars = stars;
    }
    
    public String getReleaseDate()
    {
        return releaseDate;
    }
    
    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }
    
    public String getRunning_time()
    {
        return running_time;
    }
    
    public void setRunning_time(String running_time)
    {
        this.running_time = running_time;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public Map<String, Object> getInfo()
    {
        Map<String, Object>info = new HashMap<>();
        info.put("rank", rank);
        info.put("title", title);
        info.put("category", category);
        info.put("score", score);
        info.put("country", country);
        info.put("year", year);
        return info;
    }
}
