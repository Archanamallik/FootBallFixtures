package com.example.footballfixtures.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.footballdetails.dataclass.PlayersItems
import com.example.footballdetails.dataclass.TeamsItems
import com.example.footballfixtures.dataclasses.CountryItems
import com.example.footballfixtures.dataclasses.Countrydata
import com.example.footballfixtures.dataclasses.FixtureItems
import com.example.footballfixtures.dataclasses.Leaguesdata
import com.example.footballfixtures.repository.FixtureRepository
import com.example.footballfixtures.utils.DENMARK
import com.example.footballfixtures.utils.NetworkResult
import com.example.footballfixtures.utils.SCOTLAND

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import javax.inject.Inject

@HiltViewModel
class FixtureViewModel @Inject constructor(
    private val fixtureRepository: FixtureRepository,
    private val savedStateHandle: SavedStateHandle

) : ViewModel(){
    private val _fixtureResponse: MutableStateFlow<PagingData<FixtureItems>> =
        MutableStateFlow(PagingData.empty())
    var fixtureResponse = _fixtureResponse.asStateFlow()

    private val mutableLeagueList = MutableLiveData<NetworkResult<Leaguesdata>>()
    val leagueLiveData: MutableLiveData<NetworkResult<Leaguesdata>> = mutableLeagueList
    private val mutableLeague= MutableLiveData<Leaguesdata>()
    val leagueLive: MutableLiveData<Leaguesdata> = mutableLeague

    private val mutableLeagueById = MutableLiveData<Leaguesdata?>()
    val leagueById: MutableLiveData<Leaguesdata?> = mutableLeagueById

    val teams: StateFlow<List<TeamsItems>>
        get() = fixtureRepository.teams



    fun getFixtureList1(): Flow<PagingData<FixtureItems>> = fixtureRepository.getFixture().cachedIn(viewModelScope)
    suspend fun getFixtureList(): Flow<PagingData<FixtureItems>> {

        val data= fixtureRepository.getFixture()

       data.cachedIn(viewModelScope).collect{
           _fixtureResponse.value = it
        }
        return fixtureResponse
    }

    //leagues
    fun getLeagueList() {
        viewModelScope.launch {
            val leagueList = withContext(Dispatchers.IO) {
                fixtureRepository.getLeagues()
            }
            mutableLeagueList.value = leagueList
        }
    }
    //leagues
    fun getLeagues() {
        viewModelScope.launch {
            val leagueList = withContext(Dispatchers.IO) {
                fixtureRepository.getLeaguesList()
            }
            leagueLive.value = leagueList
        }
    }
    //leaguesBy Id
    fun getLeagueById() {
        viewModelScope.launch {
            mutableLeagueById.value  = withContext(Dispatchers.IO) {
                savedStateHandle.get<String>("countryId")
                    ?.let { fixtureRepository.getLeaguesById(it) }
            }
           // mutableLeagueList.value = leagueList
        }
    }

    fun getCountry(): Flow<PagingData<CountryItems>> = fixtureRepository.getCountry().map {
            pagingData->pagingData.filter { it.id == DENMARK || it.id == SCOTLAND }
    }.cachedIn(viewModelScope)

    fun getPlayers(): Flow<PagingData<PlayersItems>>? = savedStateHandle.
    get<String>("countryId")?.let {
        fixtureRepository
        .getPlayers(it).cachedIn(viewModelScope)
    }
    fun getCoaches(): Flow<PagingData<PlayersItems>>? = savedStateHandle.
    get<String>("countryId")?.let {
        fixtureRepository
            .getCoaches(it).cachedIn(viewModelScope)
    }
    fun getReferees(): Flow<PagingData<PlayersItems>>? = savedStateHandle.
    get<String>("countryId")?.let {
        fixtureRepository
            .getReferees(it).cachedIn(viewModelScope)
    }

fun getTeams() {
    viewModelScope.launch {
        val countryId = savedStateHandle.get<String>("countryId")
        if (countryId != null) {
            fixtureRepository.getTeams(countryId)
        }
    }
}
    fun getTeamsById(countryId:Int) {
        viewModelScope.launch {
            fixtureRepository.getTeams(""+countryId)
        }
    }
}