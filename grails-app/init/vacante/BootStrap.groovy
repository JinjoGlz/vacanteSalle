package vacante

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {
    Nivel_AcademicoService nivelService
    def init = { servletContext ->
        new Nivel_Academico(nivel:'LICENCIATURA', nombre: 'Enfermería').save()
        new Nivel_Academico(nivel:'LICENCIATURA', nombre: 'Software').save()
        new Nivel_Academico(nivel:'LICENCIATURA', nombre: 'Arquitectura').save()

        new Nivel_Academico(nivel:'MAESTRIA', nombre: 'Fiscal').save()
        new Nivel_Academico(nivel:'MAESTRIA', nombre: 'Educación').save()
        
        new Nivel_Academico(nivel:'DOCTORADO', nombre: 'Comunicación').save()
        new Nivel_Academico(nivel:'DOCTORADO', nombre: 'Gastronomía').save()
        
    }
    def destroy = {
    }
}
