package com.craftingsoftware.androidshow.ui.home

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by constantin.cheptea
 * on 22/05/2018.
 */
class HomeViewModelTest {

    @Test
    fun incrementCount() {
        val homeViewModel = HomeViewModel()

        assertEquals(5, homeViewModel.sum(2, 3))
    }
}