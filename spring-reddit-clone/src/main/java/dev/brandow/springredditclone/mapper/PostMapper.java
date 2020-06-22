package dev.brandow.springredditclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.brandow.springredditclone.dto.PostRequest;
import dev.brandow.springredditclone.dto.PostResponse;
import dev.brandow.springredditclone.model.Post;
import dev.brandow.springredditclone.model.Subreddit;
import dev.brandow.springredditclone.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {
	
	@Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
	@Mapping(target = "description", source = "postRequest.description")
	Post map(PostRequest postRequest, Subreddit subreddit, User user);
	
	@Mapping(target = "id", source = "postId")
	@Mapping(target = "subredditName", source = "subreddit.name")
	@Mapping(target = "userName", source = "user.username")
	PostResponse mapToDto(Post post);
	

}
