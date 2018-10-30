package org.chaitanya.test.rest.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.chaitanya.test.rest.models.Message;
import org.chaitanya.test.rest.resources.beans.MessageFilterBean;
import org.chaitanya.test.rest.services.MessageService;

@Path("/messages")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService msgService = new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		
		if(filterBean.getYear() > 0) {
			
			return msgService.getAllMessagesByYear(filterBean.getYear());
		}
		if(filterBean.getStartPoint() >= 0 && filterBean.getSize() > 0) {
			
			return msgService.getAllMessagesByPagination(filterBean.getStartPoint(), filterBean.getSize());
		}
		return msgService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long Id, @Context UriInfo uriInfo) {

		Message message = msgService.getMessage(Id);
		
		String uri1 = getUriForSelf(message, uriInfo);
		String uri2 = getUriForProfile(message, uriInfo);
		String uri3 = getUriForComment(message, uriInfo);
		message.addLink(uri1, "self");
		message.addLink(uri2, "self");
		message.addLink(uri3, "self");
		return message;
	}

	private String getUriForComment(Message message, UriInfo uriInfo) {
		
		String uri = uriInfo.getBaseUriBuilder() //gets base uri http://localhost:8080/rest/webapi
							.path(MessageResource.class) //gets resource class level uri /messages(path is overloaded method)
							.path(MessageResource.class, "accessCommentsStubs") //gets resource method uri from path of given class and given method(path is overloaded method)
							.path(CommentResource.class) //gets comment class level uri("/" here)
							.resolveTemplate("messageId", message.getId()) //converts {messageId} to required message id
							.build()
							.toString();
		return uri;
	}

	private String getUriForProfile(Message message, UriInfo uriInfo) {
		
		String uri = uriInfo.getBaseUriBuilder()  //gets base uri http://localhost:8080/rest/webapi
							.path(ProfileResource.class)  //gets resource class level uri /profiles(path is overloaded method)
							.path(message.getAuthor())  //gets profileName at resource method Path
							.build()  //builds Uri from UriBuilder
							.toString();   //converts Uri to String
		return uri;
	}

	private String getUriForSelf(Message message, UriInfo uriInfo) {
		String uri = uriInfo.getBaseUriBuilder()   //gets base uri http://localhost:8080/rest/webapi
							.path(MessageResource.class)    //gets resource class level uri /messages(path is overloaded method)
							.path(String.valueOf(message.getId())) //gets messageId at resource method Path
							.build()  //builds Uri from UriBuilder
							.toString(); //converts Uri to String
		return uri;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = msgService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return msgService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		msgService.removeMessage(id);
	}
		
	@Path("/{messageId}/comments")
	public CommentResource accessCommentsStubs() {
		return new CommentResource();
	}
}