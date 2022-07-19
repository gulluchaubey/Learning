package com.example.learning.pager3Demo

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.learning.pager3Demo.data.moviedetails.MovieDetails
import com.example.learning.pager3Demo.remote.MovieInterface
import com.example.learning.pager3Demo.ui.details.MovieDetailsRepository
import com.example.learning.pager3Demo.ui.movie.MoviePaging
import com.example.learning.pager3Demo.utils.Events
import com.example.learning.pager3Demo.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieInterface: MovieInterface,private val repository: MovieDetailsRepository) : ViewModel() {

    private val query = MutableLiveData<String>("")
    val list = query.switchMap {query->
        Pager(PagingConfig(pageSize = 10)){
            MoviePaging(query,movieInterface)
        }.liveData.cachedIn(viewModelScope)
    }
    fun setQuery(s:String){
        query.postValue(s)
    }
    private val _movieDetails = MutableLiveData<Events<com.example.learning.pager3Demo.utils.Result<MovieDetails>>>()
    val movieDetails:LiveData<Events<com.example.learning.pager3Demo.utils.Result<MovieDetails>>> = _movieDetails

    fun getMovieDetails(imdbId:String) = viewModelScope.launch {
        _movieDetails.postValue(Events(com.example.learning.pager3Demo.utils.Result(Status.LOADING,null,null)))
        _movieDetails.postValue(Events(repository.getMovieDetails(imdbId)))
    }
}