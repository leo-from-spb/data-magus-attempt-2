package lb.dmagus.model.core

import lb.dmagus.model.concept.SubjectArea
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ModelTest
{


    @Test
    fun create()
    {
        val model = Model()
        assertThat(model.elementCount).isZero()
    }


    @Test
    fun add_element()
    {
        val model = Model()
        val subjectArea = SubjectArea(model)
        assertThat(model.elementCount).isEqualTo(1)
    }




}
