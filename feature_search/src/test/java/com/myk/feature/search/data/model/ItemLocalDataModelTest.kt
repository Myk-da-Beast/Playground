package com.myk.feature.search.data.model

import com.myk.feature.search.data.DataFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class ItemLocalDataModelTest {

    @Test
    fun `local data model maps to Item`() {
        // given
        val cut = DataFixtures.getItemLocalDataModel()

        // when
        val domainModel = cut.toDomainModel

        // then
        domainModel.name shouldBeEqualTo cut.name
        domainModel.imageUrl shouldBeEqualTo cut.imageUrl
    }
}
