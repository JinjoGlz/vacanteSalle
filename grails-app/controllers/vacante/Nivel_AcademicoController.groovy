package vacante

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class Nivel_AcademicoController {

    Nivel_AcademicoService nivel_AcademicoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond nivel_AcademicoService.list(params), model:[nivel_AcademicoCount: nivel_AcademicoService.count()]
    }

    def show(Long id) {
        respond nivel_AcademicoService.get(id)
    }

    def create() {
        respond new Nivel_Academico(params)
    }

    def save(Nivel_Academico nivel_Academico) {
        if (nivel_Academico == null) {
            notFound()
            return
        }

        try {
            nivel_AcademicoService.save(nivel_Academico)
        } catch (ValidationException e) {
            respond nivel_Academico.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nivel_Academico.label', default: 'Nivel_Academico'), nivel_Academico.id])
                redirect nivel_Academico
            }
            '*' { respond nivel_Academico, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond nivel_AcademicoService.get(id)
    }

    def update(Nivel_Academico nivel_Academico) {
        if (nivel_Academico == null) {
            notFound()
            return
        }

        try {
            nivel_AcademicoService.save(nivel_Academico)
        } catch (ValidationException e) {
            respond nivel_Academico.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nivel_Academico.label', default: 'Nivel_Academico'), nivel_Academico.id])
                redirect nivel_Academico
            }
            '*'{ respond nivel_Academico, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        nivel_AcademicoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nivel_Academico.label', default: 'Nivel_Academico'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivel_Academico.label', default: 'Nivel_Academico'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
