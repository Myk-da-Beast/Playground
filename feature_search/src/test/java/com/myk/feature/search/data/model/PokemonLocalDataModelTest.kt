package com.myk.feature.search.data.model

import com.myk.feature.search.data.DataFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class PokemonLocalDataModelTest {

    @Test
    fun `local data model maps to Pokemon`() {
        // given
        val cut = DataFixtures.getPokemonLocalDataModel()

        // when
        val domainModel = cut.toDomainModel

        // then
        domainModel.name shouldBeEqualTo cut.name
        domainModel.imageUrl shouldBeEqualTo cut.imageUrl
    }
}
