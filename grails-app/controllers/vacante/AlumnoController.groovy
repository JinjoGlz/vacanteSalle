package vacante

import org.apache.commons.lang3.RandomStringUtils;
import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AlumnoController {

    AlumnoService alumnoService
    Nivel_AcademicoService nivel_AcademicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond alumnoService.list(params), model:[alumnoCount: alumnoService.count()]
    }

    def show(Long id) {
        respond alumnoService.get(id)
    }

    def create() {
        def licenciaturasList=nivel_AcademicoService.findByNivel('LICENCIATURA') as JSON
        def maestriasList=nivel_AcademicoService.findByNivel('MAESTRIA') as JSON
        def doctoradosList=nivel_AcademicoService.findByNivel('DOCTORADO') as JSON
        respond  ([licenciaturasList: licenciaturasList, maestriasList : maestriasList, doctoradosList : doctoradosList, alumno: new Alumno(params)])
    }


    def save(Alumno alumno) {
        if (alumno == null) {
            notFound()
            return
        }

        try {
            alumno.contraseña=RandomStringUtils.randomAlphanumeric(8);
            alumnoService.save(alumno)
        } catch (ValidationException e) {
            def licenciaturasList=nivel_AcademicoService.findByNivel('LICENCIATURA') as JSON
            def maestriasList=nivel_AcademicoService.findByNivel('MAESTRIA') as JSON
            def doctoradosList=nivel_AcademicoService.findByNivel('DOCTORADO') as JSON
            respond  alumno.errors, view:'create', model:[licenciaturasList: licenciaturasList, maestriasList : maestriasList, doctoradosList : doctoradosList]
            return
        }
        sendMail {
        to alumno.correo
        from "cavante1jj@outlook.com"
        subject "Registro completado"
        html view: "/emails/notification", model: [alumno: alumno]
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'alumno.label', default: 'Alumno'), alumno.id])
                redirect alumno
            }
            '*' { respond alumno, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond alumnoService.get(id)
    }

    def update(Alumno alumno) {
        if (alumno == null) {
            notFound()
            return
        }

        try {
            alumnoService.save(alumno)
        } catch (ValidationException e) {
            respond alumno.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alumno.label', default: 'Alumno'), alumno.id])
                redirect alumno
            }
            '*'{ respond alumno, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        alumnoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'alumno.label', default: 'Alumno'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'alumno.label', default: 'Alumno'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
