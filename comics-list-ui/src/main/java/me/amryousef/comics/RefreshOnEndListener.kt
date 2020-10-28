package me.amryousef.comics

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RefreshOnEndListener(
    private val onRefreshRequested: () -> Unit
) : RecyclerView.OnScrollListener() {

    private companion object {
        const val VISIBLE_ITEMS_THRESHOLD = 5
    }

    private val visibleItems = mutableListOf<ComicItemData>()
    fun updateItems(items: List<ComicItemData>) {
        visibleItems.clear()
        visibleItems.addAll(items)
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val lastVisibleItemPosition =
            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        Log.d("AmrVisible", lastVisibleItemPosition.toString())
        if (
            (
                (recyclerView.layoutManager?.itemCount ?: 0) <=
                    lastVisibleItemPosition + VISIBLE_ITEMS_THRESHOLD
                ) &&
            visibleItems.filterIsInstance<ComicItemData.LoadingPlaceholder>().isEmpty()
        ) {
            onRefreshRequested()
        }
    }
}
