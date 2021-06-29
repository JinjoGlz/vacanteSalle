package vacante

import grails.gorm.services.Service

@Service(Nivel_Academico)
interface Nivel_AcademicoService {

    Nivel_Academico get(Serializable id)

    List<Nivel_Academico> list(Map args)

    Long count()

    void delete(Serializable id)

    Nivel_Academico save(Nivel_Academico nivel_Academico)

    List<Nivel_Academico> findByNivel(String nivel)

}