package com.zuan.studyAws.service.posts;

import com.zuan.studyAws.domain.posts.Posts;
import com.zuan.studyAws.domain.posts.PostsRepository;
import com.zuan.studyAws.web.dto.PostsResponseDto;
import com.zuan.studyAws.web.dto.PostsSaveRequestDto;
import com.zuan.studyAws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이없습니다. id="+id));
        return new PostsResponseDto(entity);
    }
}
