package lb.dmagus.model.concept

import lb.dmagus.model.core.Model


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AttributeTest
{

    @Test
    fun attribute_domain_basic()
    {
        // prepare model
        val sa = SubjectArea(Model())
        val domain = sa.domains.newDomain()
        val entity = sa.entities.newEntity()
        val attribute = entity.attributes.newAttribute()

        // attach
        attribute.domain = domain

        // verify attach
        assertThat(attribute.domain).isSameAs(domain)

        // detach
        attribute.domain = null

        // verify detach
        assertThat(attribute.domain).isNull()
    }


    @Test
    fun drop_arc_detach_domain()
    {
        // prepare model
        val sa = SubjectArea(Model())
        val domain = sa.domains.newDomain()
        val entity = sa.entities.newEntity()
        val attribute = entity.attributes.newAttribute()
        attribute.domain = domain

        // verify the model
        assertThat(attribute.domain).isSameAs(domain)

        // drop the arc
        val arc = attribute.arcs.first { it.source === attribute && it.target === domain }
        arc.drop()

        // verify
        assertThat(attribute.domain).isNull()
    }


    @Test
    fun drop_domain_detach_domain()
    {
        // prepare model
        val sa = SubjectArea(Model())
        val domain = sa.domains.newDomain()
        val entity = sa.entities.newEntity()
        val attribute = entity.attributes.newAttribute()
        attribute.domain = domain

        // verify the model
        assertThat(attribute.domain).isSameAs(domain)

        // drop the domain
        domain.drop()

        // verify
        assertThat(attribute.domain).isNull()
    }

}