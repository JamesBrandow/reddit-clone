package dev.brandow.springredditclone.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.brandow.springredditclone.dto.PostRequest;
import dev.brandow.springredditclone.dto.PostResponse;
import dev.brandow.springredditclone.exceptions.PostNotFoundException;
import dev.brandow.springredditclone.exceptions.SubredditNotFoundException;
import dev.brandow.springredditclone.mapper.PostMapper;
import dev.brandow.springredditclone.model.Post;
import dev.brandow.springredditclone.model.Subreddit;
import dev.brandow.springredditclone.model.User;
import dev.brandow.springredditclone.repository.PostRepository;
import dev.brandow.springredditclone.repository.SubredditRepository;
import dev.brandow.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

	private final SubredditRepository subredditRepository;
	private final AuthService authService;
	private final PostMapper postMapper;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	public void save(PostRequest postRequest) {
		
		Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
				.orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
		User currentUser = authService.getCurrentUser();
		
		postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
	}
	
	@Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }
	
	@Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
	
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
