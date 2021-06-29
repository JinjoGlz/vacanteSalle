<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'alumno.label', default: 'Alumno')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
    </head>
    <body>
        <a href="#create-alumno" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-alumno" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.alumno}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.alumno}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.alumno}" method="POST">
                <fieldset class="form">
                    <f:field bean="alumno" property="nombre"/>
                    <f:field bean="alumno" property="correo"/>
                    <f:field bean="alumno" property="semestre"/>
                    <f:field bean="alumno" property="matricula"/>
                    <div class="fieldcontain required">
                        <label for="nivel_academico">
                            Nivel Academico
                            <span class="required-indicator">*</span>
                        </label>
                        <select name="nivel_academico" required id="nivel" v-on:change="cambiar()" v-model="nivel">
                            <option value="licenciatura">Licenciatura</option>
                            <option value="maestria">Maestria</option>
                            <option value="doctorado">Doctorado</option>
                        </select>
                    </div>
                    <div class="fieldcontain required">
                        <label for="especialidad">
                            
                            <span class="required-indicator">*</span>
                        </label>
                        <select name="nivelAcademico.id" required id="especialidad" v-model="selected">
                            <option v-for="option in datos" v-bind:value="option.id">
                                {{ option.nombre }}
                            </option>
                        </select>
                    </div>
                    
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="Registrar" class="save" value="${message(code: 'Registrar', default: 'Registrar')}" />
                </fieldset>
            </g:form>
        </div>
        <script>
         const app= new Vue({
             el: '#create-alumno',
             data:{
                 licenciaturas: ${raw(licenciaturasList as String)},
                 maestrias: ${raw(maestriasList as String)},
                 doctorados: ${raw(doctoradosList as String)},
                 datos: ${raw(licenciaturasList as String)},
                 selected:null,
                 nivel:null
             },
             methods:{
                 cambiar(){
                     switch (this.nivel) {
                         case "licenciatura":
                             this.datos=this.licenciaturas
                             break;
                         case "maestria":
                             this.datos=this.maestrias
                             break;
                         case "doctorado":
                             this.datos=this.doctorados
                             break;

                         default:
                             break;
                     }
                 }
             }
         })
        </script>
    </body>
</html>
