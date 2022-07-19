package com.example.learning.pager3Demo.ui.details

import com.example.learning.pager3Demo.data.moviedetails.MovieDetails
import com.example.learning.pager3Demo.remote.MovieInterface
import com.example.learning.pager3Demo.utils.Constants
import com.example.learning.pager3Demo.utils.Result
import com.example.learning.pager3Demo.utils.Status

class MovieDetailsRepository(private val movieInterface:MovieInterface) {
    suspend fun getMovieDetails(imdbId:String):Result<MovieDetails>{
        return try {
            val response = movieInterface.getMovieDetails(imdbId,Constants.API_KEY)
            if(response.isSuccessful){
                Result(Status.SUCCESS,response.body(),null)
            }else{
                Result(Status.ERROR,null,null)
            }
        }catch (e:Exception){
            Result(Status.ERROR,null,null)
        }
    }
}