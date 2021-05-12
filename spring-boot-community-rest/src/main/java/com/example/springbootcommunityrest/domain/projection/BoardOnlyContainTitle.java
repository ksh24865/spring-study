package com.example.springbootcommunityrest.domain.projection;

import com.example.springbootcommunityrest.domain.Board;
import org.springframework.data.rest.core.config.Projection;

// Board듸 제목만 표시하는 프로젝션
@Projection(name = "getOnlyTitle", types = {Board.class})
public interface BoardOnlyContainTitle {
    String getTitle();
}
