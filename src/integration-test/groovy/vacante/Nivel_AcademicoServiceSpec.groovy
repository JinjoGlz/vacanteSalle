package vacante

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class Nivel_AcademicoServiceSpec extends Specification {

    Nivel_AcademicoService nivel_AcademicoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Nivel_Academico(...).save(flush: true, failOnError: true)
        //new Nivel_Academico(...).save(flush: true, failOnError: true)
        //Nivel_Academico nivel_Academico = new Nivel_Academico(...).save(flush: true, failOnError: true)
        //new Nivel_Academico(...).save(flush: true, failOnError: true)
        //new Nivel_Academico(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //nivel_Academico.id
    }

    void "test get"() {
        setupData()

        expect:
        nivel_AcademicoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Nivel_Academico> nivel_AcademicoList = nivel_AcademicoService.list(max: 2, offset: 2)

        then:
        nivel_AcademicoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        nivel_AcademicoService.count() == 5
    }

    void "test delete"() {
        Long nivel_AcademicoId = setupData()

        expect:
        nivel_AcademicoService.count() == 5

        when:
        nivel_AcademicoService.delete(nivel_AcademicoId)
        sessionFactory.currentSession.flush()

        then:
        nivel_AcademicoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Nivel_Academico nivel_Academico = new Nivel_Academico()
        nivel_AcademicoService.save(nivel_Academico)

        then:
        nivel_Academico.id != null
    }
}
