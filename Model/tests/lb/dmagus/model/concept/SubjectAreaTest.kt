package lb.dmagus.model.concept

import lb.dmagus.model.core.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class SubjectAreaTest
{

    @Test
    fun prefix_basic()
    {
        val a = SubjectArea(Model())
        assertThat(a.prefix).isNull()

        a.prefix = "pref"
        assertThat(a.prefix).isEqualTo("pref")
    }

}