package com.mak.mvvmkotlindemo.ui.book

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mak.mvvmkotlindemo.R
import com.mak.mvvmkotlindemo.common.activity.BaseSearchActivity
import com.mak.mvvmkotlindemo.common.models.CommonModel
import com.mak.mvvmkotlindemo.data.network.commonmodels.ImageLinks
import com.mak.mvvmkotlindemo.data.network.commonmodels.Item
import com.mak.mvvmkotlindemo.data.network.commonmodels.VolumeInfo
import com.mak.mvvmkotlindemo.data.network.responses.GoogleBooksVolumesResponse
import com.mak.mvvmkotlindemo.utils.CollectionUtils
import com.mak.mvvmkotlindemo.utils.Constants
import com.mak.mvvmkotlindemo.utils.Coroutines
import com.mak.mvvmkotlindemo.utils.Utils
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class BooksActivity : BaseSearchActivity(), KodeinAware,
    BaseSearchActivity.ActionMenuClickListener {

    override val kodein by kodein()
    private val factory: BooksViewModelFactory by instance()
    private lateinit var viewModel: BooksViewModel
    private val mBooks: MutableList<Item> = mutableListOf()

    override fun setActionBarTitle(): String {
        return getString(R.string.title_books)
    }

    override fun setActionMenuClickListener(): ActionMenuClickListener {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(BooksViewModel::class.java)

        setObservers()
    }

    override fun onResume() {
        super.onResume()
        if (Utils.isNetworkAvailable(this)) {
            Coroutines.main {
                val response = viewModel.getBookVolumes(Constants.QUERY_WORD)
                setupData(response)
            }
        } else {
            showToast(getString(R.string.error_internet_not_working), Toast.LENGTH_SHORT)
        }
    }

    private fun setObservers() {
        viewModel.getBookResponse().observe(this) { googleBooksVolumesResponseModel ->
            setupData(googleBooksVolumesResponseModel)
        }
    }

    private fun setupData(googleBooksVolumesResponseModel: GoogleBooksVolumesResponse?) {
        if (googleBooksVolumesResponseModel != null) {
            val books: List<Item>? = googleBooksVolumesResponseModel.items
            if (!CollectionUtils.isNullOrEmpty(books)) {
                mBooks.clear()
                mBooks.addAll(books!!)
                if (mBooks.isNotEmpty()) {
                    val commonModels: MutableList<CommonModel> = java.util.ArrayList()
                    for (book in mBooks) {
                        val commonModel = CommonModel()
                        commonModel.id = book.id
                        commonModel.subTitle = book.etag
                        val volumeInfo: VolumeInfo? = book.volumeInfo
                        val title: String? = volumeInfo?.title
                        commonModel.title = title
                        val imageLinks: ImageLinks? = volumeInfo?.imageLinks
                        commonModel.imageLinks = imageLinks
                        commonModels.add(commonModel)
                    }
                    if (!CollectionUtils.isNullOrEmpty(commonModels)) {
                        addItems(commonModels)
                        setTotal(R.plurals.books_count, commonModels.size)
                    }
                }
            }
        }
    }

    override fun onItemClick(position: Int, model: CommonModel?) {

    }

    override fun onActionMenuClickListener(itemId: Int) {

    }
}