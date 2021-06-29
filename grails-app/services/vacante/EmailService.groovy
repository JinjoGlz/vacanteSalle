package vacante

import groovy.transform.CompileStatic

@CompileStatic
interface EmailService {
    void send(Email email)
}