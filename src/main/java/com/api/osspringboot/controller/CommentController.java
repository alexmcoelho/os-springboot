package com.api.osspringboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.osspringboot.domain.exception.EntityNotFoundException;
import com.api.osspringboot.domain.model.OrderService;
import com.api.osspringboot.domain.service.OrderServiceService;
import com.api.osspringboot.model.Comment;
import com.api.osspringboot.model.CommentInput;
import com.api.osspringboot.model.CommentModel;
import com.api.osspringboot.repository.OrderServiceRepository;

@RestController
@RequestMapping("/orders-service/{orderServiceId}/comments")
public class CommentController {
	
	@Autowired
	private OrderServiceService orderServiceService;
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentModel> list(@PathVariable Long orderServiceId) {
		OrderService orderService = orderServiceRepository.findById(orderServiceId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
		return toCollectionModel(orderService.getComments());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentModel add(@PathVariable Long orderServiceId, 
			@Valid @RequestBody CommentInput commentInput) {
		Comment comment = orderServiceService.saveComment(orderServiceId, commentInput.getDescription());
		return toModel(comment);
	}
	
	private CommentModel toModel(Comment comment) {
		return modelMapper.map(comment, CommentModel.class);
	}
	
	private List<CommentModel> toCollectionModel(List<Comment> list) {
		return list.stream()
				.map(obj -> toModel(obj))
				.collect(Collectors.toList());
	}
}
