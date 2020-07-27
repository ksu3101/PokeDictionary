package com.swkang.model.domain.pokesearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.swkang.model.base.helpers.MessageHelper
import com.swkang.model.base.helpers.ResourceHelper
import com.swkang.model.domain.*
import com.swkang.model.domain.pokesearch.datas.PokemonCoordinate
import com.swkang.model.domain.pokesearch.datas.PokemonName
import com.swkang.model.domain.pokesearch.repo.PokeSearchRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class PokeSearchViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repo: PokeSearchRepository = mock()
    private val resourceHelper: ResourceHelper = mock()
    private val messageHelper: MessageHelper = mock()
    private val navigationHelper: PokemonNavigationHelper = mock()
    private lateinit var vm: PokeSearchViewModel

    @Before
    fun setUp() {
        given(repo.requestPokemonNames(anyNullableString())) willReturn Single.just(
            listOf(
                PokemonName(
                    1,
                    "이름",
                    "name"
                ),
                PokemonName(
                    2,
                    "2이름",
                    "2name"
                )
            )
        )
        given(repo.requestPokemonLocations(anyNullableLong())) willReturn Single.just(
            listOf(
                PokemonCoordinate(1, 12.0, 32.0)
            )
        )

        vm = PokeSearchViewModel(repo, resourceHelper, messageHelper, navigationHelper)
    }

    @Test
    fun createVm_GivenNoneError_ThenupdateViews() {
        // given - when -> createViewModel

        // then..
        vm.isLoading.value shouldBe false
        vm.pokemons.value shouldNotBe null
        vm.pokemons.value!!.size shouldBe 2
        messageHelper.hasNotCalled().createReTryActionDialog(anyString(), anyString())
    }

    @Test
    fun onQueryStringChanged_GivenSomeQueryString_ThenUpdateQueryLiveData() {
        val queryStr = "pikachu"

        vm.onQueryStringChanged(queryStr)

        vm.query.value shouldBe queryStr
    }

}