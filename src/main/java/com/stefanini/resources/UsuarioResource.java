package com.stefanini.resources;

import com.stefanini.dto.AtualizacaoUsuarioDTO;
import com.stefanini.dto.CadastroUsuarioDTO;
import com.stefanini.services.UsuarioService;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @POST
    public Response cadastrarUsuario(@Valid CadastroUsuarioDTO cadastroUsuarioDTO) {
        return Response.status(Response.Status.CREATED).entity(usuarioService.CadastrarUsuario(cadastroUsuarioDTO)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarDadosUsuario(@PathParam("id") Long id, @Valid AtualizacaoUsuarioDTO dadosUsuarioDTO) {
        return Response.status(Response.Status.OK).entity(usuarioService.atualizarUsuario(id, dadosUsuarioDTO)).build();
    }

    @DELETE
    @Path("/remover/{id}")
    public Response removerUsuario(@PathParam("id") Long id) {
        try {
            usuarioService.removerUsuario(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id informado não existe").build();
        }
    }

    @GET
    @Path("/aniversariantes/{mes}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarAniversariantesDoMes(@PathParam("mes") Integer mes) {
        return Response.status(Response.Status.OK).entity(usuarioService.buscarAniversariantesDoMes(mes)).build();
    }

    @GET
    @Path("/provedores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarProvesdores() {
        return Response.status(Response.Status.OK).entity(usuarioService.listarProvedores()).build();
    }

    @GET
    @Path("/inicial")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuariosPorInicial(@QueryParam("inicial") String inicial) {
        return Response.status(Response.Status.OK).entity(usuarioService.listarUsuariosPorInicial(inicial)).build();
    }
}
