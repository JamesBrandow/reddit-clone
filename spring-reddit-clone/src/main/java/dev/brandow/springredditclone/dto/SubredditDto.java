package dev.brandow.springredditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubredditDto {

	private Long id;
	private String name;
	private String desctription;
	private Integer numberOfPosts;
}
