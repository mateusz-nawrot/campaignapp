package com.nawrot.mateusz.campaignapp.list.adapter

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View


class GridItemDecoration(private val itemOffset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent?.layoutManager is GridLayoutManager) {
            val layoutPosition = parent.getChildLayoutPosition(view)
            val columns = (parent.layoutManager as GridLayoutManager).spanCount
            if (columns > 1) {
                if (layoutPosition % 2 == 0) {
                    outRect?.set(0, itemOffset, itemOffset / 2, 0)
                } else {
                    outRect?.set(itemOffset / 2, itemOffset, 0, 0)
                }
            } else {
                outRect?.set(0, itemOffset, 0, 0)
            }
        } else {
            outRect?.set(0, itemOffset, 0, 0)
        }
    }
}