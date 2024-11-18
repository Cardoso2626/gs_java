package br.com.fiap.resource;





import br.com.fiap.bo.Business;
import br.com.fiap.dao.HabitoDao;
import br.com.fiap.model.Habito;
import br.com.fiap.util.HabitoTo;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("habitos")
public class HabitoResource {
    private HabitoDao habitoDao;

    public HabitoResource(){
        this.habitoDao = new HabitoDao();
    }

    //Método HTTP POST
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastra(HabitoTo h){
        Business bo = new Business();
        try{
            bo.cadastraHabito(h.toHabito());
            return Response.status(201).entity(h).build();
        } catch (Exception e) {
            return Response.status(401).entity(e.getMessage()).build();
        }
    }


    //Método HTTP GET (LISTA)
    @GET
    public List<Habito> listar() throws Exception {
        return habitoDao.recupera();
    }

    //Método HTTP GET
    @GET
    @Path("{id}")
    public Habito recuperaUsuario(@PathParam("id") Long id) throws Exception {
        return habitoDao.recuperaHabitoPorId(id);
    }


    //Método HTTP DELETE
    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") Long id) throws Exception {
        habitoDao.deleta(id);
    }

    //Método HTTP PUT
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(@Valid HabitoTo h, @PathParam("id") Long id) {
        try {
            Habito habitoExistente = habitoDao.recuperaHabitoPorId(id);

            if (habitoExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Habito com ID " + id + " não encontrado.")
                        .build();
            }


            Habito habitoAtualizado = h.toHabito();
            habitoAtualizado.setId(id);

            habitoDao.altera(habitoAtualizado);

            return Response.ok(h).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar o habito: " + e.getMessage())
                    .build();
        }
    }
}
