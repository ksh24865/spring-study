package com.example.springbootcommunityweb;

import com.example.springbootcommunityweb.domain.Board;
import com.example.springbootcommunityweb.domain.User;
import com.example.springbootcommunityweb.domain.enums.BoardType;
import com.example.springbootcommunityweb.repository.BoardRepository;
import com.example.springbootcommunityweb.repository.UserRepository;
import com.example.springbootcommunityweb.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootCommunityWebApplication implements WebMvcConfigurer {

	public static void main(String[] args) {


		URL url = new URL(targetUrl);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection(); con.setRequestMethod("POST"); // HTTP POST 메소드 설정
		con.setRequestProperty("User-Agent", USER_AGENT); con.setDoOutput(true); // POST 파라미터 전달을 위한 설정
		// Send post request DataOutputStream wr = new DataOutputStream(con.getOutputStream()); wr.writeBytes(parameters); wr.flush(); wr.close(); int responseCode = con.getResponseCode(); BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); String inputLine; StringBuffer response = new StringBuffer(); while ((inputLine = in.readLine()) != null) { response.append(inputLine); } in.close(); // print result System.out.println("HTTP 응답 코드 : " + responseCode); System.out.println("HTTP body : " + response.toString()); }

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
		};
	}

}
