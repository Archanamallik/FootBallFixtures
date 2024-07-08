package com.example.footballfixtures.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.footballfixtures.dataclasses.CountryItems
import com.example.footballfixtures.dataclasses.FixtureItems
import com.example.footballfixtures.network.CountryApi
import com.example.footballfixtures.network.FixtureApis
private const val TAG = "PagingSource"
class CountryPagingSource(
    private val countryApi: CountryApi,
   // private val remoteDataSource: IRemoteDataSource,

    /*    private val withGenres: String*/
) :
    PagingSource<Int, CountryItems>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CountryItems> {
        return try {
            val position = params.key ?: 1
            val response = countryApi.getCountry( position)

            if (response.data != null) {
                return LoadResult.Page(
                    data = response.data,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.pagination.count) null else (position + 1)
                )
            } else {
                Log.i(TAG, "load: No Response")
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CountryItems>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}