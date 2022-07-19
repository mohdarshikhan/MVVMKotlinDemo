package com.mak.mvvmkotlindemo.ui.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mak.mvvmkotlindemo.data.network.responses.GoogleBooksVolumesResponse
import com.mak.mvvmkotlindemo.data.repositories.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksViewModel(private val repository: BookRepository) : ViewModel()  {

    private val mBooksResponseMutableLiveData: MutableLiveData<GoogleBooksVolumesResponse> =
        MutableLiveData<GoogleBooksVolumesResponse>()

    fun getBookResponse(): MutableLiveData<GoogleBooksVolumesResponse> {
        return mBooksResponseMutableLiveData
    }

    suspend fun getBookVolumes(query: String) = withContext(Dispatchers.IO) {
        repository.getBookVolumes(query)
    }
}