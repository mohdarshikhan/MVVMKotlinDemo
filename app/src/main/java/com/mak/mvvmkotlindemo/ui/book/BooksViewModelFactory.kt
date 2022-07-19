package com.mak.mvvmkotlindemo.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mak.mvvmkotlindemo.data.repositories.BookRepository

class BooksViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BooksViewModel(repository) as T
    }
}