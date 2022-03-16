package com.example.home.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Result
import com.example.domain.model.ComicModel
import com.example.domain.usecase.GetComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getComicsUseCase: GetComicsUseCase,
) : ViewModel() {

    var comicDetailsState: MutableStateFlow<Result<List<ComicModel>>> =
        MutableStateFlow(Result.Empty)
        private set

    init {
        getComicList()
    }

    private fun getComicList() {
        viewModelScope.launch {
            comicDetailsState.value = Result.Loading
            val comicListResult = getComicsUseCase.getComicList()
            comicDetailsState.value = comicListResult
        }
    }
}
