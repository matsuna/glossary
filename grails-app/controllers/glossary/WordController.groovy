package glossary

class WordController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [wordInstanceList: Word.list(params), wordInstanceTotal: Word.count()]
    }

    def create = {
        def wordInstance = new Word()
        wordInstance.properties = params
        return [wordInstance: wordInstance]
    }

    def save = {
        def wordInstance = new Word(params)
        if (wordInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'word.label', default: 'Word'), wordInstance.id])}"
            redirect(action: "show", id: wordInstance.id)
        }
        else {
            render(view: "create", model: [wordInstance: wordInstance])
        }
    }

    def show = {
        def wordInstance = Word.get(params.id)
        if (!wordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
            redirect(action: "list")
        }
        else {
            [wordInstance: wordInstance]
        }
    }

    def edit = {
        def wordInstance = Word.get(params.id)
        if (!wordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [wordInstance: wordInstance]
        }
    }

    def update = {
        def wordInstance = Word.get(params.id)
        if (wordInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (wordInstance.version > version) {
                    
                    wordInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'word.label', default: 'Word')] as Object[], "Another user has updated this Word while you were editing")
                    render(view: "edit", model: [wordInstance: wordInstance])
                    return
                }
            }
            wordInstance.properties = params
            if (!wordInstance.hasErrors() && wordInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'word.label', default: 'Word'), wordInstance.id])}"
                redirect(action: "show", id: wordInstance.id)
            }
            else {
                render(view: "edit", model: [wordInstance: wordInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def wordInstance = Word.get(params.id)
        if (wordInstance) {
            try {
                wordInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])}"
            redirect(action: "list")
        }
    }
}
