package com.example.comicapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comicapp.data.ComicRepository
import com.example.comicapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val comicRepository: ComicRepository,
) : ViewModel() {

    var comicDetailsState: MutableStateFlow<UiState<List<ComicModel>>> =
        MutableStateFlow(UiState.Empty)
        private set

    init {
        getComicDetails()
    }

    private fun getComicDetails() {
        viewModelScope.launch {
            comicDetailsState.value = UiState.Loading
            val comicListResult = comicRepository.getComicList()
            comicDetailsState.value = comicListResult
        }
    }
}
