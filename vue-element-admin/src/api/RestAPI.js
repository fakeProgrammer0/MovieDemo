import axios from 'axios'
import * as RestUrl from './RestUrl'

export const userLogin = params => {
  return axios({
    method: 'post',
    url: RestUrl.baseUrl + RestUrl.userLogin,
    data: params
  })
}

export const getMoivesInfo = params => {
  return axios({
      method: `get`,
      url: RestUrl.baseUrl + RestUrl.getMoviesInfo,
      params: params
    });
}

export const getMovieDesc = params => {
  return axios({
    method: 'get',
    url: RestUrl.baseUrl + RestUrl.getMovieDesc,
    params: params
  });
}

export const getMoviesCnt = params => {
  return axios({
    method: 'get',
    url: RestUrl.baseUrl + RestUrl.moviesCnt
  });
}

export const getShortComments = params => {
  return axios({
    method: 'get',
    url: RestUrl.baseUrl + RestUrl.shortComments,
    params: params
  });
}

export const addCollectedMovie = params => {
  return axios({
    method: 'put',
    url: RestUrl.baseUrl + RestUrl.collectedMovie,
    data: params
    });
}

export const removeCollectedMovie = params => {
  return axios({
    method: "delete",
    url: RestUrl.baseUrl + RestUrl.collectedMovie,
    data: params
  });
}

export const getCollectedMovies = params =>{
  return axios({
    method: 'get',
    url: RestUrl.baseUrl + RestUrl.getCollectedMoves,
    params: params
  })
}

export const checkMovieCollected = params =>{
  return axios({
    method: 'get',
    url: RestUrl.baseUrl + RestUrl.collectedMovie,
    params: params
  })
}









