package com.example.footballfixtures.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.footballdetails.dataclass.PlayersItems
import com.example.footballdetails.dataclass.Playersdata
import com.example.footballfixtures.network.PlayerApi


private const val TAG = "MoviePagingSource"

class PlayerPagingSource(
    private val playerApi: PlayerApi,
    private val countryId: String,
    private val pagingId:Int

    ) :
    PagingSource<Int, PlayersItems>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlayersItems> {
        return try {
            val position = params.key ?: 1
            var response: Playersdata? =null
            when(pagingId) {
                0->  response = playerApi.getPlayerById(countryId, position)
                1->  response = playerApi.getCoachById(countryId, position)
                2->  response = playerApi.getRefereeById(countryId, position)
            }
            if (response!=null && response!!.data != null) {
                return LoadResult.Page(
                    data = response.data!!,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.pagination.count) null else (position + 1)
                )
            } else {

                LoadResult.Error(throw Exception("No Response"))
            }
        } catch (e: Exception) {
            Log.i(TAG, "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PlayersItems>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}