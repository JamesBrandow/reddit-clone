package dev.brandow.springredditclone.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.brandow.springredditclone.dto.SubredditDto;
import dev.brandow.springredditclone.exceptions.SpringRedditException;
import dev.brandow.springredditclone.mapper.SubredditMapper;
import dev.brandow.springredditclone.model.Subreddit;
import dev.brandow.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
	
	private final SubredditRepository subredditRepository;
	private final SubredditMapper subredditMapper;

	@Transactional
	public SubredditDto save(SubredditDto subredditDto) {
		Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
		subredditDto.setId(save.getId());
		return subredditDto;
	}
	
	@Transactional(readOnly = true)
	public List<SubredditDto> getAll() {
		return subredditRepository.findAll()
				.stream()
				.map(subredditMapper::mapSubredditToDto)
				.collect(toList());
	}
	
	public SubredditDto getSubreddit(Long id) {
		Subreddit subreddit = subredditRepository.findById(id)
				.orElseThrow(() -> new SpringRedditException("No subreddit found with that id"));
		return subredditMapper.mapSubredditToDto(subreddit);
	}
	
//	private SubredditDto mapToDto(Subreddit subreddit) {
//        return SubredditDto.builder().name(subreddit.getName())
//                .id(subreddit.getId())
//                .numberOfPosts(subreddit.getPosts().size())
//                .build();
//    }
//	
//	private Subreddit mapSubredditDto(SubredditDto subredditDto) {
//		return Subreddit.builder().name(subredditDto.getName())
//				.Description(subredditDto.getDesctription())
//				.build();
//	}
	
	
}
