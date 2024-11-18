package br.com.fiap.resource;


import br.com.fiap.bo.Business;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;
import br.com.fiap.util.UsuarioTo;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path("usuarios")
public class UsuarioResource {

    private UsuarioDao usuarioDao;

    public UsuarioResource(){
        this.usuarioDao = new UsuarioDao();
    }

    //Método HTTP POST
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastra(UsuarioTo u){
        UsuarioDao banco = new UsuarioDao();
        try{
            banco.insert(u.toUsuario());
            return Response.status(201).entity(u).build();
        } catch (Exception e) {
            return Response.status(406).entity(e.getMessage()).build();
        }
    }

    //Método HTTP GET (LISTA)
    @GET
    public List<Usuario> listar() throws Exception {
        return usuarioDao.recupera();
    }


    //Método HTTP GET
    @GET
    @Path("{id}")
    public Usuario recuperaUsuario(@PathParam("id") Long id) throws Exception {
        return usuarioDao.recuperaUsuarioPorId(id);
    }


    //Método HTTP DELETE
    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") Long id) throws Exception {
        usuarioDao.deleta(id);
    }

    //Método HTTP PUT
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid UsuarioTo u, @PathParam("id") Long id) {
        try {
            Usuario usuarioExistente = usuarioDao.recuperaUsuarioPorId(id);

            if (usuarioExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuario com ID " + id + " não encontrado.")
                        .build();
            }


            Usuario usuarioAtualizado = u.toUsuario();
            usuarioAtualizado.setId(id);

            usuarioDao.altera(usuarioAtualizado);

            return Response.ok(u).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o usuario: " + e.getMessage())
                    .build();
        }
    }
}
