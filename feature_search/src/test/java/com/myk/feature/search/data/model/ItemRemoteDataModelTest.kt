package com.myk.feature.search.data.model

import com.myk.feature.search.data.DataFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class ItemRemoteDataModelTest {

    @Test
    fun `remote data model maps to ItemLocalDataModel`() {
        // given
        val cut = DataFixtures.getItemRemoteDataModel()

        // when
        val domainModel = cut.toLocalDataModel

        // then
        // NOTE: url on remote model does not have an equivalent in the domain. Also image url on
        // the local model does not have a counter part on the remote.
        domainModel.name shouldBeEqualTo cut.name
    }
}
