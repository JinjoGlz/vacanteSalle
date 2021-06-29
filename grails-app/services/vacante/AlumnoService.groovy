package vacante

import grails.gorm.services.Service

@Service(Alumno)
interface AlumnoService {

    Alumno get(Serializable id)

    List<Alumno> list(Map args)

    Long count()

    void delete(Serializable id)

    Alumno save(Alumno alumno)

}