package lb.dmagus.model.concept

import lb.dmagus.model.core.Model
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class SubjectAreaTest
{

    @Test
    fun children_1()
    {
        val a = SubjectArea(Model())
        val d1 = a.domains.newDomain()
        val d2 = a.domains.newDomain()
        val e1 = a.entities.newEntity()
        val e2 = a.entities.newEntity()

        val list = a.childNodes.toArrayList()
        assertThat(list).contains(d1,d2,e1,e2)
    }


    @Test
    fun drop_nested_elements_recursively()
    {
        val area = SubjectArea(Model())
        val entity = area.entities.newEntity()
        val attr = entity.attributes.newAttribute()

        area.drop()

        assertThat(entity.dropped).isTrue()
        assertThat(attr.dropped).isTrue()
    }

}