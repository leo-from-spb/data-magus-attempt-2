package lb.dmagus.model.concept

import lb.dmagus.model.core.Model


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AttributeTest
{

    @Test
    fun attribute_domain_basic()
    {
        val sa = SubjectArea(Model())
        val domain = sa.domains.newDomain()
        val entity = sa.entities.newEntity()
        val attribute = entity.attributes.newAttribute()

        attribute.domain = domain

        assertThat(attribute.domain).isSameAs(domain)

        attribute.domain = null

        assertThat(attribute.domain).isNull()
    }

}