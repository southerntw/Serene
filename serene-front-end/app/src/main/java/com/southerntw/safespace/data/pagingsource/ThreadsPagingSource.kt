package com.southerntw.safespace.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.southerntw.safespace.data.api.NewsData
import com.southerntw.safespace.data.api.SafespaceApiService
import com.southerntw.safespace.data.api.ThreadsDetail

class ThreadsPagingSource(
    private val safespaceApiService: SafespaceApiService,
) : PagingSource<Int, ThreadsDetail>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ThreadsDetail>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ThreadsDetail> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = safespaceApiService.getThreads(
                page = position,
            )

            LoadResult.Page(
                data = responseData.threadData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.threadData.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
