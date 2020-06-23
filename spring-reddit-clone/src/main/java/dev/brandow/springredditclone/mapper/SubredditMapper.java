package dev.brandow.springredditclone.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.brandow.springredditclone.dto.SubredditDto;
import dev.brandow.springredditclone.model.Post;
import dev.brandow.springredditclone.model.Subreddit;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

	@Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
	SubredditDto mapSubredditToDto(Subreddit subreddit);
	
	default Integer mapPosts(List<Post> numberOfPosts) {
		return numberOfPosts.size();
	}
	
	@InheritInverseConfiguration
	@Mapping(target = "posts", ignore = true)
	Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}
