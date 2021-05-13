package com.example.springbootcommunityrest.domain;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardPicture {
    @Id
    @GeneratedValue
    private Integer ID;
    @NotNull
    private Integer boardIdx;

    // 원본 파일이름 과 서버에 저장된 파일 경로 를 분리한 이유?
    // 동일한 이름을 가진 파일이 업로드가 된다면 오류가 생긴다.
    // 이를 해결하기 위함
    @NotEmpty
    private String original_file_name;
    @NotEmpty
    private String stored_file_path;

    private long file_size;
}