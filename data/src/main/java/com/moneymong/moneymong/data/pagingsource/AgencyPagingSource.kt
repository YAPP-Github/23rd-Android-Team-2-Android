package com.moneymong.moneymong.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.moneymong.moneymong.data.datasource.agency.AgencyRemoteDataSource
import com.moneymong.moneymong.network.response.agency.AgencyGetResponse

class AgencyPagingSource(
    private val dataSource: AgencyRemoteDataSource,
) : PagingSource<Int, AgencyGetResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AgencyGetResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AgencyGetResponse> {
        val page = params.key ?: START_PAGE
        val loadSize = params.loadSize
        return dataSource.getAgencies(page = page, size = loadSize).fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.agencies,
                    prevKey = null,
                    nextKey = if (it.agencies.size < loadSize) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

    private companion object {
        const val START_PAGE = 0
    }
}