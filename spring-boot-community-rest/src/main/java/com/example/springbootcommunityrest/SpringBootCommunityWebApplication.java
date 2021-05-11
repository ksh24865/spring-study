package com.example.springbootcommunityrest;

import com.example.springbootcommunityrest.domain.Board;
import com.example.springbootcommunityrest.domain.User;
import com.example.springbootcommunityrest.domain.enums.BoardType;
import com.example.springbootcommunityrest.repository.BoardRepository;
import com.example.springbootcommunityrest.repository.UserRepository;
import com.example.springbootcommunityrest.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootCommunityWebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCommunityWebApplication.class, args);
	}


	@Autowired
	//UserArgumentResolver 클래스 사용 위해 WebMvcConfigurerAdapter 상속 후 오버라이드
	private UserArgumentResolver userArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
		argumentResolvers.add(userArgumentResolver);
	}

	@Bean //빈으로 생성된 메서드에 파라미터로 의존성 주입 가능
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
		return (args) -> {
			User user = userRepository.save(User.builder()
			.name("havi")
			.password("test")
			.email("havi@gmail.com")
			.createdDate(LocalDateTime.now())
			.build());

			IntStream.rangeClosed(1,200).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글"+index)
							.subTitle("순서"+index)
							.content("콘텐츠")
							.boardType(BoardType.free)
							.createdDate(LocalDateTime.now())
							.updatedDate(LocalDateTime.now())
							.user(user)
							.build())
					);
			boardRepository.save(Board.builder()
					.title("게시글"+201)
					.subTitle("순서"+201)
					.content("콘텐츠")
					.boardType(BoardType.notice)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(user)
					.build());
		};
	}

}