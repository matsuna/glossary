package glossary

class CategController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [categInstanceList: Categ.list(params), categInstanceTotal: Categ.count()]
    }

    def create = {
        def categInstance = new Categ()
        categInstance.properties = params
        return [categInstance: categInstance]
    }

    def save = {
        def categInstance = new Categ(params)
        if (categInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'categ.label', default: 'Categ'), categInstance.id])}"
            redirect(action: "show", id: categInstance.id)
        }
        else {
            render(view: "create", model: [categInstance: categInstance])
        }
    }

    def show = {
        def categInstance = Categ.get(params.id)
        if (!categInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
            redirect(action: "list")
        }
        else {
            [categInstance: categInstance]
        }
    }

    def edit = {
        def categInstance = Categ.get(params.id)
        if (!categInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [categInstance: categInstance]
        }
    }

    def update = {
        def categInstance = Categ.get(params.id)
        if (categInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (categInstance.version > version) {
                    
                    categInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'categ.label', default: 'Categ')] as Object[], "Another user has updated this Categ while you were editing")
                    render(view: "edit", model: [categInstance: categInstance])
                    return
                }
            }
            categInstance.properties = params
            if (!categInstance.hasErrors() && categInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'categ.label', default: 'Categ'), categInstance.id])}"
                redirect(action: "show", id: categInstance.id)
            }
            else {
                render(view: "edit", model: [categInstance: categInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def categInstance = Categ.get(params.id)
        if (categInstance) {
            try {
                categInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categ.label', default: 'Categ'), params.id])}"
            redirect(action: "list")
        }
    }
}
