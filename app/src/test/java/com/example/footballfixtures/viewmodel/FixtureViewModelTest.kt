package com.example.footballfixtures.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.repository.FixtureRepository
import com.example.footballfixtures.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FixtureViewModelTest {
    private val testDispather=StandardTestDispatcher()
    @Mock
    lateinit var fixtureRepository: FixtureRepository

    @get:Rule
    val instantTaxtexecutor= InstantTaskExecutorRule()

    @Before
    fun setUp(){
      MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispather)
    }
    @Test
    fun  test_getLeagueList()= runTest{
        Mockito.`when`(fixtureRepository.getLeagues()).thenReturn(NetworkResult.Success(Leaguesdata())

        )
          val sut=FixtureViewModel(fixtureRepository, savedStateHandle = SavedStateHandle())
        sut.getLeagueList()
        testDispather.scheduler.advanceUntilIdle()
        val result=sut.leagueLiveData.value
        if (result != null) {
            Assert.assertEquals(0,result.data!!.data)
        }
    }
    @Test
    fun  test_getLeagueList_Error()= runTest{
        Mockito.`when`(fixtureRepository.getLeagues()).thenReturn(NetworkResult.Error("Something went wrong")

        )
        val sut=FixtureViewModel(fixtureRepository, savedStateHandle = SavedStateHandle())
        sut.getLeagueList()
        testDispather.scheduler.advanceUntilIdle()
        val result=sut.leagueLiveData.value
        if (result != null) {
            Assert.assertEquals(true,result is NetworkResult.Error)
            Assert.assertEquals("Something went wrong",result.message)
        }
    }
    @Test
    fun  test_getLeagueList_Size()= runTest{
        Mockito.`when`(fixtureRepository.getLeaguesList()).thenReturn(Leaguesdata()

        )
        val sut=FixtureViewModel(fixtureRepository, savedStateHandle = SavedStateHandle())
        sut.getLeagues()
        testDispather.scheduler.advanceUntilIdle()
        val result=sut.leagueLive.value
        if (result != null) {
            Assert.assertEquals(4,result.data.size)

        }
    }
    @After
    fun tearDown(){
     Dispatchers.resetMain()
    }
}