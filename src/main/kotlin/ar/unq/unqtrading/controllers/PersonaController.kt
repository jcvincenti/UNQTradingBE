package ar.unq.unqtrading.controllers

import ar.unq.unqtrading.entities.Accion
import ar.unq.unqtrading.entities.Persona
import ar.unq.unqtrading.services.PersonaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/usuario")
class PersonaController {

    @Autowired lateinit var personaService: PersonaService

    @PostMapping(value = ["/save"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun save(@RequestBody persona: Persona) : Persona{
        return personaService.save(persona)
    }

    @PostMapping(value = ["/buy"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    fun comprarOrden(@RequestParam ordenId: Int, @RequestParam usuarioId: Int) : Accion{
        return personaService.buy(ordenId, usuarioId)
    }

    @GetMapping(value = ["/find"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun findUsuario(@RequestParam usuarioId: Int) : Persona = personaService.findById(usuarioId)

    @GetMapping(value = ["/acciones"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun findAcciones(@RequestParam usuarioId: Int) : List<Accion> = personaService.findAcciones(usuarioId)

    @PostMapping(value = ["/login"])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun login(@RequestParam dni: Long, @RequestParam username: String, @RequestParam password: String) = personaService.login(dni, username, password)

    @PostMapping(value = ["/cargarSaldo"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun cargarSaldo(@RequestParam dni: Long, @RequestParam saldo: Int) = personaService.cargarSaldo(dni, saldo)

    @GetMapping(value = ["/obtenerSaldo"])
    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    fun findSaldo(@RequestParam usuarioId: Int) : Int = personaService.findSaldo(usuarioId)
}
